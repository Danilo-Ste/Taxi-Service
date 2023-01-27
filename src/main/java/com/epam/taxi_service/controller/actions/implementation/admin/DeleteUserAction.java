package com.epam.taxi_service.controller.actions.implementation.admin;

import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.*;
import org.slf4j.*;

import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.DELETE_USER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.SEARCH_USER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.SUCCEED_DELETE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class DeleteUserAction implements Action {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserAction.class);
    private final UserService userService;

    /**
     * @param appContext contains UserService instance to use in action
     */
    public DeleteUserAction(AppContext appContext) {
        userService = appContext.getUserService();
    }

    /**
     * Checks method and calls required implementation
     *
     * @param request  to get method, session and set all required attributes
     * @return path to redirect or forward by front-controller
     * @throws ServiceException to call error page in front-controller
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    /**
     * Called from doGet method in front-controller. Obtains required path and transfer attributes from session
     * to request
     *
     * @param request to get message attribute from session and put it in request
     * @return search user page after trying delete user
     */
    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, MESSAGE);
        return SEARCH_USER_PAGE;
    }

    /**
     * Called from doPost method in front-controller. Tries to delete user from database.
     * Logs error in case if not able
     *
     * @param request to get users id and set message in case of successful deleting
     * @return path to redirect to executeGet method through front-controller
     */
    private String executePost(HttpServletRequest request) throws ServiceException {
        try {
            userService.delete(request.getParameter(USER_ID));
            request.getSession().setAttribute(MESSAGE, SUCCEED_DELETE);
        } catch (NoSuchUserException e) {
            logger.error("Couldn't delete user - no such user");
        }
        return getActionToRedirect(DELETE_USER_ACTION);
    }
}
