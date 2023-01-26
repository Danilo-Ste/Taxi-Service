package com.epam.taxi_service.controllers.actions.implementation.base;

import com.epam.taxi_service.Exception.DuplicateEmailException;
import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controllers.actions.Action;
import com.epam.taxi_service.controllers.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controllers.actions.Util.*;
import static com.epam.taxi_service.controllers.actions.implementation.ActionNames.EDIT_PROFILE_ACTION;
import static com.epam.taxi_service.controllers.actions.implementation.Page.EDIT_PROFILE_PAGE;
import static com.epam.taxi_service.controllers.actions.implementation.ParameterValues.SUCCEED_UPDATE;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;

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
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }


    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, MESSAGE);
        transferStringFromSessionToRequest(request, ERROR);
        transferUserDTOFromSessionToRequest(request);
        return EDIT_PROFILE_PAGE;
    }


    private String executePost(HttpServletRequest request) throws ServiceException {
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
        return UserDTO.builder()
                .id(currentUser.getId())
                .email(request.getParameter(EMAIL))
                .name(request.getParameter(NAME))
                .surname(request.getParameter(SURNAME))
                .build();
    }

    private void updateSessionUser(UserDTO currentUser, UserDTO user) {
        currentUser.setEmail(user.getEmail());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
    }
}
