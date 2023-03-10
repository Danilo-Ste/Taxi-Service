package com.epam.taxi_service.models.services.service_Іmplementation;

import com.epam.taxi_service.Exception.*;
import com.epam.taxi_service.dto.CarDTO;

import com.epam.taxi_service.models.dao.CarDAO;
import com.epam.taxi_service.models.entities.Car;
import com.epam.taxi_service.models.entities.State;
import com.epam.taxi_service.models.services.CarServices;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi_service.utils.Convertor.*;
import static com.epam.taxi_service.utils.Validator.*;

@RequiredArgsConstructor
public class CarServiceImplementation implements CarServices {
    private final CarDAO carDAO;

    @Override
    public void add(CarDTO carDTO) throws ServiceException {
        //validateCar(carDTO);
        Car car = convertDTOToCar(carDTO);
        try {
            carDAO.add(car);
        } catch (DAOException e) {
            checkExceptionType(e);
        }
    }
    private void validateCar(CarDTO carDTO) throws IncorrectFormatException {
        validateAddressForTaxi(carDTO.getAddress());
    }
    @Override
    public List<CarDTO> getSortedCars(String query) throws ServiceException {
        List<CarDTO> carDTOs = new ArrayList<>();
        try {
            List<Car> cars = carDAO.getSorted(query);
            cars.forEach(car -> carDTOs.add(convertCarToDTO(car)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return carDTOs;
    }

    @Override
    public void changeAddress(String carId, String newAddress) throws ServiceException {
        try {
            validateAddressForTaxi(newAddress);
            getById(carId).setAddress(newAddress);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setState(int carId, int stateId) throws ServiceException {
        try {
            State state = State.getState(stateId);
            carDAO.setCarState(carId, state);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CarDTO getById(String idString) throws ServiceException {
        CarDTO carDTO;
        long carId = getId(idString);
        try {
            Car car = carDAO.getById(carId).orElseThrow(NoSuchCarException::new);
            carDTO = convertCarToDTO(car);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return carDTO;
    }

    @Override
    public List<CarDTO> getAll() throws ServiceException {
        List<CarDTO> carDTOs = new ArrayList<>();
        try {
            List<Car> cars = carDAO.getAll();
            cars.forEach(car -> carDTOs.add(convertCarToDTO(car)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return carDTOs;
    }

    @Override
    public void update(CarDTO dto) throws ServiceException {
        validateCar(dto);
        Car car = convertDTOToCar(dto);
        try {
            carDAO.update(car);
        } catch (DAOException e) {
            checkExceptionType(e);
        }
    }

    private void checkExceptionType(DAOException e) throws ServiceException {
        if (e.getMessage().contains("Duplicate")) {
            throw new DuplicateEmailException();
        } else {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(String idString) throws ServiceException {
        long carId = getId(idString);
        try {
            carDAO.delete(carId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
