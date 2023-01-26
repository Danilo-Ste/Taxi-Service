package com.epam.taxi_service.models.dao;

import com.epam.taxi_service.Exception.DAOException;

import java.util.List;

public interface EntityDAO<T> {

    void add(T t) throws DAOException;


    //Optional<T> getById(long id) throws DAOException;


    List<T> getAll() throws DAOException;


    void update(T t) throws DAOException;


    void delete(long id) throws DAOException;
}
