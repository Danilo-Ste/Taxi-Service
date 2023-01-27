package com.epam.taxi_service.models.services;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.OrderDTO;

import java.util.List;

public interface OrderService extends Service<OrderDTO>{
    void add(OrderDTO orderDTO) throws ServiceException;

    List<OrderDTO> getSortedOrders(String query) throws ServiceException;

    List<OrderDTO>  getSorted(String query) throws ServiceException;
}
