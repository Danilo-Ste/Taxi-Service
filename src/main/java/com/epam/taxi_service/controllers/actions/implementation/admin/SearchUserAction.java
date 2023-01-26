package com.epam.taxi_service.controllers.actions.implementation.admin;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controllers.actions.Action;
import com.epam.taxi_service.controllers.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controllers.actions.implementation.Page.SEARCH_USER_PAGE;
import static com.epam.taxi_service.controllers.actions.implementation.Page.USER_BY_EMAIL_PAGE;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;

public class SearchUserAction implements Action {

    private final UserService userService;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String path = USER_BY_EMAIL_PAGE;
        try {
            request.setAttribute(USER, userService.getByEmail(request.getParameter(EMAIL)));
        } catch (NoSuchUserException | IncorrectFormatException e) {
            request.setAttribute(ERROR, e.getMessage());
            path = SEARCH_USER_PAGE;
        }
        return path;
    }
    public SearchUserAction(AppContext appContext) {
        userService = appContext.getUserService();
    }
}
