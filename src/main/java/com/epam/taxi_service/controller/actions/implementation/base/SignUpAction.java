package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.*;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.isPostMethod;
import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SIGN_UP_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.SIGN_IN_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Page.SIGN_UP_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.SUCCEED_REGISTER;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class SignUpAction implements Action {

    private final UserService userService;
    //private final EmailSender emailSender;
    //private final Captcha captcha;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if(isPostMethod(request)){
            return executePost(request);
        }
        return executeGet(request);
    }

    public SignUpAction(AppContext appContext) {
        userService = appContext.getUserService();
        //emailSender = appContext.getEmailSender();
        //captcha = appContext.getCaptcha();
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, MESSAGE);
        transferStringFromSessionToRequest(request, ERROR);
        transferUserDTOFromSessionToRequest(request);
        return getPath(request);
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        String path = SIGN_IN_PAGE;
        UserDTO user = getUserDTO(request);
        request.getSession().setAttribute(USER, user);
        try {
            userService.add(user, request.getParameter(PASSWORD), request.getParameter(CONFIRM_PASSWORD));
            request.getSession().setAttribute(MESSAGE, SUCCEED_REGISTER);
            //sendEmail(user, getURL(request));
        } catch (IncorrectFormatException | PasswordMatchingException | DuplicateEmailException  e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            path = SIGN_UP_PAGE;
        }
        request.getSession().setAttribute(CURRENT_PATH, path);
        return getActionToRedirect(SIGN_UP_ACTION);
    }

    //send email-----

    private UserDTO getUserDTO(HttpServletRequest request) {
        return UserDTO.builder()
                .email(request.getParameter(EMAIL))
                .name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME))
                .build();
    }
}
