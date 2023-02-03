package com.epam.taxi_service.models.services.service_Іmplementation;

import com.epam.taxi_service.Exception.*;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.models.dao.OrderDAO;
import com.epam.taxi_service.models.entities.Order;
import com.epam.taxi_service.models.services.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.epam.taxi_service.utils.Convertor.*;
import static com.epam.taxi_service.utils.Validator.*;
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderDAO orderDAO;


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
        validateAddressForTaxi(orderDTO.getAddressOfDeparture());
        validateAddressForTaxi(orderDTO.getAddressOfDestination());
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
        OrderDTO orderDTO;
        long orderId = getId(idString);
        try {
            Order order = orderDAO.getById(orderId).orElseThrow(NoSuchOrderException::new);
            orderDTO = convertOrderToDTO(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAll() throws ServiceException {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try {
            List<Order> reports = orderDAO.getAll();
            reports.forEach(order -> orderDTOS.add(convertOrderToDTO(order)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderDTOS;
    }

    @Override
    public void update(OrderDTO dto) throws ServiceException {
        validateAddressForTaxi(dto.getAddressOfDeparture());
        validateAddressForTaxi(dto.getAddressOfDestination());
        Order order = convertDTOToOrder(dto);
        try {
            orderDAO.update(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(String idString) throws ServiceException {
        long orderId = getId(idString);
        try {
            orderDAO.delete(orderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
