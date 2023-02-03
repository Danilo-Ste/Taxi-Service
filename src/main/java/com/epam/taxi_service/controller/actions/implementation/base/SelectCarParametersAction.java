package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.dto.CarDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.Page.CREATE_ORDER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.ERROR;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.ORDER;

public class SelectCarParametersAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
     //   return isPostMethod(request) ? executePost(request) : executeGet(request);
        return "";
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, ERROR);
        transferOrderDTOFromSessionToRequest(request, ORDER);
        return CREATE_ORDER_PAGE;
    }

    /**
    private String executePost(HttpServletRequest request) throws ServiceException {
        EventDTO event = getEventDTO(request);
        try {
            eventService.addEvent(event);
            long eventId = eventService.getByTitle(event.getTitle()).getId();
            return getActionToRedirect(SEARCH_EVENT_ACTION, EVENT_ID, String.valueOf(eventId));
        } catch (IncorrectFormatException | DuplicateTitleException e) {
            request.getSession().setAttribute(EVENT, event);
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(CREATE_EVENT_ACTION);
    }


    private CarDTO getCarDTO(HttpServletRequest request) {
        return CarDTO.builder()
                .title(request.getParameter(TITLE))
                .date(request.getParameter(DATE))
                .location(request.getParameter(LOCATION))
                .description(request.getParameter(DESCRIPTION))
                .build();
    }
     */
}
