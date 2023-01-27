package com.epam.taxi_service.controller.actions.implementation.admin;

import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.entities.Role;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.Util.getActionToRedirect;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SEARCH_USER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class SetRoleAction implements Action {

    private final UserService userService;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        int roleId = Role.valueOf(request.getParameter(ROLE)).getValue();
        userService.setRole(email, roleId);
        request.setAttribute(USER, userService.getByEmail(email));
        return getActionToRedirect(SEARCH_USER_ACTION, EMAIL, email);
    }
    public SetRoleAction(AppContext appContext) {
        userService = appContext.getUserService();
    }
}
