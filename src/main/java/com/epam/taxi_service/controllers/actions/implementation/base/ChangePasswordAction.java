package com.epam.taxi_service.controllers.actions.implementation.base;

import com.epam.taxi_service.Exception.*;
import com.epam.taxi_service.controllers.actions.Action;
import com.epam.taxi_service.controllers.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controllers.actions.Util.*;
import static com.epam.taxi_service.controllers.actions.implementation.ActionNames.CHANGE_PASSWORD_ACTION;
import static com.epam.taxi_service.controllers.actions.implementation.Page.CHANGE_PASSWORD_PAGE;
import static com.epam.taxi_service.controllers.actions.implementation.ParameterValues.SUCCEED_UPDATE;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;

public class ChangePasswordAction implements Action {
    private final UserService userService;


    public ChangePasswordAction(AppContext appContext) {
        userService = appContext.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, MESSAGE);
        transferStringFromSessionToRequest(request, ERROR);
        return CHANGE_PASSWORD_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        try {
            userServiceChangePassword(request);
            request.getSession().setAttribute(MESSAGE, SUCCEED_UPDATE);
        } catch (IncorrectFormatException | IncorrectPasswordException | NoSuchUserException |
                 PasswordMatchingException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(CHANGE_PASSWORD_ACTION);
    }

    private void userServiceChangePassword(HttpServletRequest request) throws ServiceException {
        long id = ((UserDTO) request.getSession().getAttribute(LOGGED_USER)).getId();
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        userService.changePassword(id, oldPassword, password, confirmPassword);
    }

}
