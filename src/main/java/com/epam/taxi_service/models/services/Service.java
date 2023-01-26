package com.epam.taxi_service.models.services;

import com.epam.taxi_service.Exception.ServiceException;

import java.util.List;

public interface Service <T>{
    T getById(String idString) throws ServiceException;

    List<T> getAll() throws ServiceException;


    void update(T dto) throws ServiceException;


    void delete(String idString) throws ServiceException;
}
