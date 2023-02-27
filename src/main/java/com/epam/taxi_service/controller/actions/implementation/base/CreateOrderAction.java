package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.State;
import com.epam.taxi_service.models.services.CarServices;
import com.epam.taxi_service.models.services.OrderService;
import com.epam.taxi_service.utils.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.*;
import static com.epam.taxi_service.controller.actions.implementation.Page.CREATE_ORDER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.ADDRESS;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.CAPACITY;
import static com.epam.taxi_service.utils.QueryBuilderUtil.orderQueryBuilder;
import static com.epam.taxi_service.utils.QueryBuilderUtil.userQueryBuilder;

public class CreateOrderAction implements Action {

    private OrderService orderService;
    private CarServices carServices;

    public CreateOrderAction(AppContext appContext) {
        orderService = appContext.getOrderService();
        carServices = appContext.getCarService();
    }

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
        QueryBuilder queryBuilder = getQueryBuilder(request);
        List<CarDTO> carDTOs = carServices.getSortedCars(queryBuilder.getQuery());
        try {
            if (!carDTOs.get(0).equals(null)){
                OrderDTO order = getOrderDTO(request,carDTOs.get(0).getId());
                orderService.add(order);
            }else {
                return getActionToRedirect(UNABLE_TO_CREATE_ORDER,
                        NUMBER_OF_PEOPLE,request.getParameter(NUMBER_OF_PEOPLE),
                        CATEGORY_ID,request.getParameter(CATEGORY_ID),
                        ADDRESS_OF_DEPARTURE,request.getParameter(ADDRESS_OF_DEPARTURE),
                        ADDRESS_OF_DESTINATION,request.getParameter(ADDRESS_OF_DESTINATION));
            }

        } catch (IncorrectFormatException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(CREATE_ORDER_ACTION);
    }

    private QueryBuilder getQueryBuilder(HttpServletRequest request) {
        return orderQueryBuilder()
                .setCapacityFilter(request.getParameter(NUMBER_OF_PEOPLE))
                .setCategory_IdFilter(request.getParameter(CATEGORY_ID))
                .setStateFilter(String.valueOf(State.AVAILABLE.getValue()));
    }

    private OrderDTO getOrderDTO(HttpServletRequest request, long car_id){
        UserDTO user = (UserDTO) request.getSession().getAttribute(LOGGED_USER);
    return OrderDTO.builder()
            .addressOfDeparture(request.getParameter(ADDRESS_OF_DEPARTURE))
            .addressOfDestination(request.getParameter(ADDRESS_OF_DESTINATION))
            .user_id(Long.parseLong(String.valueOf(user.getId())))
            .car_id(car_id)
            .build();
    }
}
