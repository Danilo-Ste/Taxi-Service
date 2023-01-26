package com.epam.taxi_service.models.dao;

import com.epam.taxi_service.models.dao.for_the_database.DAOFactorySQL;

import javax.sql.DataSource;

public abstract class DAOFactory {
    private static DAOFactory instance;
    protected DAOFactory() {}

    public static synchronized DAOFactory getInstance(DataSource dataSource) {
       if (instance == null) {
            instance = new DAOFactorySQL(dataSource);
        }
        return instance;
    }

    public abstract CarDAO getCarDAO();
    public abstract UserDAO getUserDAO();

    public abstract OrderDAO getOrderDAO();
}
