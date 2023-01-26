package com.epam.taxi_service.controllers.actions.implementation.base;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controllers.actions.Action;
import com.epam.taxi_service.controllers.context.AppContext;
import com.epam.taxi_service.models.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateOrderAction implements Action {

    private OrderService orderService;

    public CreateOrderAction(AppContext appContext) {
        orderService = appContext.getOrderService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }

}
