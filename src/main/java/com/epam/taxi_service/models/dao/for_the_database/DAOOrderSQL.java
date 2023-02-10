package com.epam.taxi_service.models.dao.for_the_database;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.dao.OrderDAO;
import com.epam.taxi_service.models.entities.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.*;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLOrderQueries.*;

public class DAOOrderSQL implements OrderDAO {

    private final DataSource dataSource;

    public DAOOrderSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) throws DAOException {
        System.out.println(ADD_ORDER);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            System.out.println(ADD_ORDER);
            int k = 0;
            setStatementFieldsForAddMethod(order, preparedStatement, k);
            System.out.println(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private int setStatementFieldsForAddMethod(Order order, PreparedStatement preparedStatement, int k) throws SQLException {
        System.out.println(preparedStatement);
        preparedStatement.setString(++k, order.getAddressOfDeparture());
        System.out.println(order.getAddressOfDeparture());
        preparedStatement.setString(++k, order.getAddressOfDestination());
        System.out.println(order.getAddressOfDestination());
        preparedStatement.setLong(++k, order.getUser_id());
        System.out.println(order.getUser_id());
        preparedStatement.setLong(++k, order.getCar_id());
        System.out.println( order.getCar_id());
        return k;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    Order createOrder(ResultSet resultSet) throws SQLException{
        return Order.builder()
                .Id(resultSet.getLong(ID))
                .addressOfDeparture(resultSet.getString(ADDRESS_OF_DEPARTURE))
                .addressOfDestination(resultSet.getString(ADDRESS_OF_DESTINATION))
                .user_id(resultSet.getLong(USER_ID))
                .car_id(resultSet.getLong(CAR_ID))
                .build();
    }

    @Override
    public void update(Order order) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_ORDER)) {
            int k = 0;
            k = setStatementFieldsForUpdateMethod(order, preparedStatement, k);
            preparedStatement.setLong(++k, order.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private int setStatementFieldsForUpdateMethod(Order order, PreparedStatement preparedStatement, int k) throws SQLException {
        preparedStatement.setString(++k, order.getAddressOfDeparture());
        preparedStatement.setString(++k, order.getAddressOfDestination());
        preparedStatement.setLong(++k, order.getCar_id());
        return k;
    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {
            setId(id, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private void setId(long id, PreparedStatement preparedStatement) throws SQLException {
        int k = 0;
        preparedStatement.setLong(++k, id);
    }

    @Override
    public Optional<Order> getById(long id) throws DAOException {
        Order order = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {
            setId(id, preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order = createOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> getSorted(String query) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_SORTED, query))) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(createOrder(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }
}
