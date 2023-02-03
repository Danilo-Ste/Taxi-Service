package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.models.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.CREATE_ORDER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SEARCH_ORDER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.CREATE_ORDER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class CreateOrderAction implements Action {

    private OrderService orderService;

    public CreateOrderAction(AppContext appContext) {
        orderService = appContext.getOrderService();
    }


    //need create getActionToRedirect for user and car-------------


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, ERROR);
        transferOrderDTOFromSessionToRequest(request, ORDER);
        return CREATE_ORDER_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        OrderDTO order = getOrderDTO(request);
        try {
            orderService.add(order);
            //long eventId = eventService.getByTitle(event.getTitle()).getId();
           // return getActionToRedirect(SEARCH_ORDER_ACTION, ID, String.valueOf(eventId));
        } catch (IncorrectFormatException e) {
            request.getSession().setAttribute(ORDER, order);
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(CREATE_ORDER_ACTION);
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
