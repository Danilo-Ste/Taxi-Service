package com.epam.taxi_service.models.dao;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.entities.*;

import java.util.List;

public interface CarDAO extends EntityDAO<Car>{

    void setCarState(long carId, State state) throws DAOException;
    Car getById(long id) throws DAOException;
    List<Car> getSorted(String query) throws DAOException;

    void setCategory(long carId, Category category)throws DAOException;
     void setAddress(Car car) throws DAOException;

}
