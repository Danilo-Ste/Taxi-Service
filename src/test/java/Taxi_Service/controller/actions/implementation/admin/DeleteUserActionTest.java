package Taxi_Service.controller.actions.implementation.admin;

import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.Exception.NoSuchUserException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.implementation.admin.DeleteUserAction;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static Taxi_Service.controller.actions.util.Util.*;
import static com.epam.taxi_service.controller.actions.Util.getActionToRedirect;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.DELETE_USER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Page.SEARCH_USER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.*;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


class DeleteUserActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final AppContext appContext = mock(AppContext.class);
    private final UserService userService = mock(UserService.class);

    @Test
    void testExecutePost() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        when(request.getMethod()).thenReturn(POST);
        when(request.getParameter(USER_ID)).thenReturn(ONE);
        when(appContext.getUserService()).thenReturn(userService);
        doNothing().when(userService).delete(ONE);
        String path = new DeleteUserAction(appContext).execute(myRequest, response);

        assertEquals(getActionToRedirect(DELETE_USER_ACTION), path);
        assertEquals(SUCCEED_DELETE, myRequest.getSession().getAttribute(MESSAGE));
    }

    @Test
    void testExecutePostNull() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        when(request.getMethod()).thenReturn(POST);
        when(request.getParameter(USER_ID)).thenReturn(null);
        when(appContext.getUserService()).thenReturn(userService);
        doThrow(NoSuchUserException.class).when(userService).delete(null);
        String path = new DeleteUserAction(appContext).execute(myRequest, response);

        assertEquals(getActionToRedirect(DELETE_USER_ACTION), path);
        assertNull(myRequest.getSession().getAttribute(MESSAGE));
    }

    @Test
    void testExecuteGet() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        when(request.getMethod()).thenReturn(GET);
        myRequest.getSession().setAttribute(MESSAGE, SUCCEED_DELETE);

        assertEquals(SEARCH_USER_PAGE, new DeleteUserAction(appContext).execute(myRequest, response));
        assertEquals(SUCCEED_DELETE, myRequest.getAttribute(MESSAGE));
        assertNull(myRequest.getSession().getAttribute(MESSAGE));
    }
}