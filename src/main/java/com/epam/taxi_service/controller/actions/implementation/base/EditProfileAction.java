package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.DuplicateEmailException;
import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.EDIT_PROFILE_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.EDIT_PROFILE_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.SUCCEED_UPDATE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class EditProfileAction implements Action {
    private final UserService userService;

    /**
     * @param appContext contains UserService instance to use in action
     */
    public EditProfileAction(AppContext appContext) {
        userService = appContext.getUserService();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        System.out.println("execute");
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }


    private String executeGet(HttpServletRequest request) {
        System.out.println("executeGet EditProfile");
        transferStringFromSessionToRequest(request, MESSAGE);
        transferStringFromSessionToRequest(request, ERROR);
        transferUserDTOFromSessionToRequest(request);
        return EDIT_PROFILE_PAGE;
    }


    private String executePost(HttpServletRequest request) throws ServiceException {
        System.out.println("executePost EditProfile");
        UserDTO sessionUser = (UserDTO) request.getSession().getAttribute(LOGGED_USER);
        UserDTO user = getUserDTO(request, sessionUser);
        try {
            userService.update(user);
            request.getSession().setAttribute(MESSAGE, SUCCEED_UPDATE);
            updateSessionUser(sessionUser, user);
        } catch (IncorrectFormatException | DuplicateEmailException e) {
            request.getSession().setAttribute(USER, user);
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(EDIT_PROFILE_ACTION);
    }

    private UserDTO getUserDTO(HttpServletRequest request, UserDTO currentUser) {
        System.out.println("getUserDTO EditProfile");
        return UserDTO.builder()
                .id(currentUser.getId())
                .email(request.getParameter(EMAIL))
                .name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME))
                .build();
    }

    private void updateSessionUser(UserDTO currentUser, UserDTO user) {
        System.out.println("updateSessionUser EditProfile");
        currentUser.setEmail(user.getEmail());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
    }
}
