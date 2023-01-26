package com.epam.taxi_service.models.services;

import com.epam.taxi_service.models.dao.DAOFactory;
import com.epam.taxi_service.models.services.service_Іmplementation.CarServiceImplementation;
import com.epam.taxi_service.models.services.service_Іmplementation.OrderServiceImplementation;
import com.epam.taxi_service.models.services.service_Іmplementation.UserServiceІmplementation;
import lombok.Getter;

@Getter
public class ServiceFactory {

    private final OrderService orderService;
    private final UserService userService;
    private final CarServices carService;

    private ServiceFactory(DAOFactory daoFactory) {
        orderService = new OrderServiceImplementation(daoFactory.getOrderDAO());
        userService = new UserServiceІmplementation(daoFactory.getUserDAO());
        carService = new CarServiceImplementation(daoFactory.getCarDAO());
    }
    public static ServiceFactory getInstance(DAOFactory daoFactory) {
        return new  ServiceFactory(daoFactory);
    }
}
