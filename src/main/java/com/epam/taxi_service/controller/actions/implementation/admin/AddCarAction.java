package com.epam.taxi_service.controller.actions.implementation.admin;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.models.services.CarServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.*;
import static com.epam.taxi_service.controller.actions.implementation.Page.ADD_CAR_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.ADDRESS;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.CAPACITY;


public class AddCarAction implements Action {
    private final CarServices carService;

    public AddCarAction(AppContext appContext) {
        carService = appContext.getCarService();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if(isPostMethod(request)){
            return executePost(request);
        }
        return executeGet(request);
    }
    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, ERROR);
        transferCarDTOFromSessionToRequest(request, CAR);
        return ADD_CAR_PAGE;
    }


    private String executePost(HttpServletRequest request) throws ServiceException {
        CarDTO car = getCarDTO(request);
        try {
            carService.add(car);
            return getActionToRedirect(SEARCH_USER_ACTION);
        } catch (IncorrectFormatException e) {
            request.getSession().setAttribute(CAR, car);
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(DEFAULT_ACTION);
    }
    private CarDTO getCarDTO(HttpServletRequest request){
        return CarDTO.builder()
                .capacity(Integer.parseInt(request.getParameter(CAPACITY)))
                .categoryId(Integer.parseInt(request.getParameter(CATEGORY_ID)))
                .IdState(Integer.parseInt(request.getParameter(ID_STATE)))
                .address(request.getParameter(ADDRESS))
                .build();
    }
}
