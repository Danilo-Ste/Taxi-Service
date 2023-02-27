package com.epam.taxi_service.controller.actions.implementation.admin;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.implementation.Page.SEARCH_USER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Page.USER_BY_EMAIL_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class SearchUserAction implements Action {

    private final UserService userService;
    public SearchUserAction(AppContext appContext) {
        userService = appContext.getUserService();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String path = USER_BY_EMAIL_PAGE;
        try {
            request.setAttribute(EMAIL,email);
            request.setAttribute(NAME,(userService.getByEmail(email)).getName());
            request.setAttribute(SURNAME,(userService.getByEmail(email)).getSurname());
            request.setAttribute(ROLE,(userService.getByEmail(email)).getRole());
        } catch (NoSuchUserException | IncorrectFormatException e) {
            request.setAttribute(ERROR, e.getMessage());
            path = SEARCH_USER_PAGE;
        }
        return path;
    }

}
