package com.epam.taxi_service.models.dao.for_the_database;

import com.epam.taxi_service.models.dao.*;

import javax.sql.DataSource;

public class DAOFactorySQL extends DAOFactory {
    private CarDAO carDAO;
    private UserDAO userDAO;

    private OrderDAO orderDAO;
    private final DataSource dataSource;

    public DAOFactorySQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new DAOUserSQL(dataSource);
        }
        return userDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            orderDAO = new DAOOrderSQL(dataSource);
        }
        return orderDAO;
    }

    public CarDAO getCarDAO() {
        if (carDAO == null) {
            carDAO = new DAOCarSQL(dataSource);
        }
        return carDAO;
    }


}
