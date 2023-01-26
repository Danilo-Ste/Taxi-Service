package com.epam.taxi_service.models.services;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.UserDTO;

import java.util.List;

public interface CarServices extends Service<CarDTO>{
    void add(CarDTO carDTO, String address, String category) throws ServiceException;

    List<CarDTO> getSortedCars(String query) throws ServiceException;

    void changeAddress(long CarId, String newAddress) throws ServiceException;

    void setState(int id, int roleId) throws ServiceException;
}
