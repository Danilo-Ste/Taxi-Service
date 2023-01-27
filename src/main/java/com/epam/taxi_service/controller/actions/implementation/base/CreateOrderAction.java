package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.models.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.getActionToRedirect;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SEARCH_ORDER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class CreateOrderAction implements Action {

    private OrderService orderService;

    public CreateOrderAction(AppContext appContext) {
        orderService = appContext.getOrderService();
    }


    //need create getActionToRedirect for user and car-------------


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderDTO order = getOrderDTO(request);
        try {
            orderService.add(order);
        } catch (IncorrectFormatException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(SEARCH_ORDER_ACTION, ID, String.valueOf(order.getCar_id()));
    }

    private OrderDTO getOrderDTO(HttpServletRequest request){
    return OrderDTO.builder()
            .id(Long.parseLong(request.getParameter(ID)))
            .addressOfDeparture(request.getParameter(ADDRESS_OF_DEPARTURE))
            .addressOfDestination(request.getParameter(ADDRESS_OF_DESTINATION))
            .user_id(Long.parseLong(request.getParameter(USER_ID)))
            .car_id(Long.parseLong(request.getParameter(CAR_ID)))
            .build();
    }
}
