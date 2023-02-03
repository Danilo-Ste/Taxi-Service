package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectPasswordException;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.event.Level;
import org.slf4j.*;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SIGN_IN_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.PROFILE_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Page.SIGN_IN_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class SignInAction implements Action {

    private static final Logger logger = LoggerFactory.getLogger(SignInAction.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if(isPostMethod(request)){
            return executePost(request);
        }
        return executeGet(request);
    }

    public SignInAction(AppContext appContext){
        userService = appContext.getUserService();
    }

    private String executeGet(HttpServletRequest request) {
        System.out.println("executeGet");
        transferStringFromSessionToRequest(request, EMAIL);
        transferStringFromSessionToRequest(request, MESSAGE);
        transferStringFromSessionToRequest(request, ERROR);
        return SIGN_IN_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        System.out.println("executePost");
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        try {
            UserDTO user = userService.signIn(email, password);
            setLoggedUser(request, user);
            logger.atLevel(Level.INFO).log(String.format("%s entered web app", user.getEmail()));
            return PROFILE_PAGE;
        } catch (NoSuchUserException | IncorrectPasswordException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            request.getSession().setAttribute(EMAIL, email);
        }
        return getActionToRedirect(SIGN_IN_ACTION);
    }

    private static void setLoggedUser(HttpServletRequest request, UserDTO user) {
        System.out.println("setLoggedUser");
        request.getSession().setAttribute(LOGGED_USER, user);
        request.getSession().setAttribute(ROLE, user.getRole());
    }

}
