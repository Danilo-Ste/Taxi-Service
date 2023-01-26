package com.epam.taxi_service.models.services.service_Іmplementation;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.models.dao.CarDAO;
import com.epam.taxi_service.models.dao.OrderDAO;
import com.epam.taxi_service.models.services.OrderService;

import java.util.List;

public class OrderServiceImplementation implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImplementation(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void add(OrderDTO orderDTO, String addressOfDeparture, String addressOfDestination) throws ServiceException {

    }

    @Override
    public List<OrderDTO> getSortedOrders(String query) throws ServiceException {
        return null;
    }

    @Override
    public List<OrderDTO> getSorted(String query) throws ServiceException {
        return null;
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
