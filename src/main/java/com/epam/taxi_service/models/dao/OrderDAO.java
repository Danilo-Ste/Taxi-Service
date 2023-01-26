package com.epam.taxi_service.models.dao;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.entities.Order;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface OrderDAO extends EntityDAO<Order>{
    Optional<Order> getById(long id) throws DAOException;
    List<Order> getSorted(String query) throws DAOException;
}
