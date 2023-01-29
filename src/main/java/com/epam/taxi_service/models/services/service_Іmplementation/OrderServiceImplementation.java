package com.epam.taxi_service.models.services.service_Іmplementation;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.Exception.DuplicateEmailException;
import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.models.dao.CarDAO;
import com.epam.taxi_service.models.dao.OrderDAO;
import com.epam.taxi_service.models.entities.Car;
import com.epam.taxi_service.models.entities.Order;
import com.epam.taxi_service.models.services.OrderService;

import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi_service.utils.Convertor.*;
import static com.epam.taxi_service.utils.Validator.validateAddress;

public class OrderServiceImplementation implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImplementation(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    public void add(OrderDTO orderDTO) throws ServiceException {
        validateOrder(orderDTO);
        Order order = convertDTOToOrder(orderDTO);
        try {
            orderDAO.add(order);
        } catch (DAOException e) {
            checkExceptionType(e);
        }
    }

    private void validateOrder(OrderDTO orderDTO) throws IncorrectFormatException {
        validateAddress(orderDTO.getAddressOfDeparture());
        validateAddress(orderDTO.getAddressOfDestination());
    }

    private void checkExceptionType(DAOException e) throws ServiceException {
        if (e.getMessage().contains("Duplicate")) {
            throw new DuplicateEmailException();
        } else {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDTO> getSortedOrders(String query) throws ServiceException {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        try {
            List<Order> orders = orderDAO.getSorted(query);
            orders.forEach(order -> orderDTOs.add(convertOrderToDTO(order)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderDTOs;
    }


    @Override
    public OrderDTO getById(String idString) throws ServiceException {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public void update(OrderDTO dto) throws ServiceException {

    }

    @Override
    public void delete(String idString) throws ServiceException {

    }
}
