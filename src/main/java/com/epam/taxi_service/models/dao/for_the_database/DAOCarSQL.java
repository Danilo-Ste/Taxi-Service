package com.epam.taxi_service.models.dao.for_the_database;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.dao.CarDAO;
import com.epam.taxi_service.models.entities.Car;
import com.epam.taxi_service.models.entities.Category;
import com.epam.taxi_service.models.entities.State;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLCarQueries.*;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.*;

public class DAOCarSQL implements CarDAO {
    private final DataSource dataSource;

    public DAOCarSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setCarState(long carId, State state) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SET_STATE)) {
            int k = 0;
            preparedStatement.setInt(++k, state.getValue());
            preparedStatement.setLong(++k, carId);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Car> getById(long id) throws DAOException {
        Car car = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)) {
            int k = 0;
            preparedStatement.setLong(++k, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    car = createCar(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return  Optional.ofNullable(car);
    }

    private Car createCar(ResultSet resultSet) throws SQLException{
        return Car.builder()
                .id(resultSet.getLong(ID))
                .address(resultSet.getString(ADDRESS))
                .categoryId(resultSet.getInt(CATEGORY_ID))
                .capacity(resultSet.getInt(CAPACITY))
                .IdState(resultSet.getInt(STATE_ID))
                .build();
    }
    @Override
    public List<Car> getSorted(String query) throws DAOException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_SORTED_CAR, query))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cars.add(createCar(resultSet));
                }
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public void setCategory(long carId, Category category) {

    }

    @Override
    public void setAddress(Car car) throws DAOException {
        /**
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SET_ROLE)) {
            int k = 0;
            preparedStatement.setInt(++k, role.getValue());
            preparedStatement.setString(++k, userEmail);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new DAOException(e);
        }
         */
    }

    @Override
    public void add(Car car) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR)) {
            setStatementFieldsForAddMethod(car, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private void setStatementFieldsForAddMethod(Car car, PreparedStatement preparedStatement) throws SQLException {
        int k = 0;
        preparedStatement.setString(++k, car.getAddress());
        preparedStatement.setLong(++k, car.getCategoryId());
        preparedStatement.setLong(++k,car.getCapacity());
        preparedStatement.setLong(++k, car.getIdState());
    }
    @Override
    public List<Car> getAll() throws DAOException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CARS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cars.add(createCar(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cars;
    }

    @Override
    public void update(Car car) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)) {
            setStatementFieldsForUpdateMethod(car, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    private static void setStatementFieldsForUpdateMethod(Car car, PreparedStatement preparedStatement)
            throws SQLException {
        int k = 0;
        preparedStatement.setString(++k, car.getAddress());
    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)) {
            int k = 0;
            preparedStatement.setLong(++k, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
