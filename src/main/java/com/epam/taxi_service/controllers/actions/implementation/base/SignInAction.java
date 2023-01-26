package com.epam.taxi_service.controllers.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectPasswordException;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controllers.actions.Action;
import com.epam.taxi_service.controllers.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controllers.actions.Util.*;
import static com.epam.taxi_service.controllers.actions.implementation.ActionNames.SIGN_IN_ACTION;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;

public class SignInAction implements Action {

    private final UserService userService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if(isPostMethod(request)){
            return executePost(request);
        }
        return executeGet(request);
    }

    SignInAction(AppContext appContext){
        userService = appContext.getUserService();
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, EMAIL);
        transferStringFromSessionToRequest(request, ERROR);
        return "";
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        try {
            UserDTO user = userService.signIn(email, password);
            setLoggedUser(request, user);
            return "PROFILE_PAGE";
        } catch (NoSuchUserException | IncorrectPasswordException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            request.getSession().setAttribute(EMAIL, email);
        }
        return getActionToRedirect(SIGN_IN_ACTION);
    }

    private static void setLoggedUser(HttpServletRequest request, UserDTO user) {
        request.getSession().setAttribute(LOGGED_USER, user);
        request.getSession().setAttribute(ROLE, user.getRole());
    }

}
