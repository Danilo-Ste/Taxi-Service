package Taxi_Service.controller.actions.implementation.base;

import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.Exception.DuplicateEmailException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.implementation.base.EditProfileAction;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import static Taxi_Service.controller.actions.util.Util.*;
import static Taxi_Service.controller.actions.util.Util.getUserDTO;
import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.DUPLICATE_EMAIL;
import static com.epam.taxi_service.Exception.message_for_exceptions.MessageForExceptions.ENTER_CORRECT_NAME;
import static com.epam.taxi_service.controller.actions.Util.getActionToRedirect;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.*;
import static com.epam.taxi_service.controller.actions.implementation.Page.EDIT_PROFILE_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.*;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class EditProfileActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final AppContext appContext = mock(AppContext.class);
    private final UserService userService = mock(UserService.class);

    @Test
    void testExecutePost() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        UserDTO user = getUserDTO();
        myRequest.getSession().setAttribute(LOGGED_USER, user);
        setPostRequest();
        when(appContext.getUserService()).thenReturn(userService);
        doNothing().when(userService).update(isA(UserDTO.class));
        String path = new EditProfileAction(appContext).execute(myRequest, response);

        assertEquals(getActionToRedirect(EDIT_PROFILE_ACTION), path);
        assertEquals(SUCCEED_UPDATE, myRequest.getSession().getAttribute(MESSAGE));
        assertEquals(NEW_EMAIL, user.getEmail());
        assertEquals(NEW_NAME, user.getName());
        assertEquals(NEW_SURNAME, user.getSurname());
    }

    @Test
    void testExecuteBadPost() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        UserDTO user = getUserDTO();
        myRequest.getSession().setAttribute(LOGGED_USER, user);
        setPostRequest();
        when(appContext.getUserService()).thenReturn(userService);
        doThrow(new DuplicateEmailException()).when(userService).update(isA(UserDTO.class));
        String path = new EditProfileAction(appContext).execute(myRequest, response);

        assertEquals(getActionToRedirect(EDIT_PROFILE_ACTION), path);
        assertEquals(DUPLICATE_EMAIL, myRequest.getSession().getAttribute(ERROR));
        assertEquals(getNewUserDTO(), myRequest.getSession().getAttribute(USER));
    }

    @Test
    void testExecuteGet() throws ServiceException {
        MyRequest myRequest = new MyRequest(request);
        setGetRequest(myRequest);
        String path = new EditProfileAction(appContext).execute(myRequest, response);

        assertEquals(EDIT_PROFILE_PAGE, path);
        assertEquals(SUCCEED_UPDATE, myRequest.getAttribute(MESSAGE));
        assertEquals(ENTER_CORRECT_NAME, myRequest.getAttribute(ERROR));
        assertEquals(getNewUserDTO(), myRequest.getAttribute(USER));
        assertNull(myRequest.getSession().getAttribute(MESSAGE));
        assertNull(myRequest.getSession().getAttribute(ERROR));
        assertNull(myRequest.getSession().getAttribute(USER));
    }

    private static UserDTO getNewUserDTO() {
        return UserDTO.builder().id(1).email(NEW_EMAIL).name(NEW_NAME).surname(NEW_SURNAME).build();
    }

    void setPostRequest() {
        when(request.getMethod()).thenReturn(POST);
        when(request.getParameter(EMAIL)).thenReturn(NEW_EMAIL);
        when(request.getParameter(NAME)).thenReturn(NEW_NAME);
        when(request.getParameter(SURNAME)).thenReturn(NEW_SURNAME);
    }

    void setGetRequest(MyRequest myRequest) {
        when(request.getMethod()).thenReturn(GET);
        HttpSession session = myRequest.getSession();
        session.setAttribute(MESSAGE, SUCCEED_UPDATE);
        session.setAttribute(ERROR, ENTER_CORRECT_NAME);
        session.setAttribute(USER, getNewUserDTO());
    }
}