package Taxi_Service.controller.actions;

import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static Taxi_Service.Constants.*;
import static com.epam.taxi_service.controller.actions.Util.*;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.DELETE_USER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.*;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ActionUtilTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    void testIsPostMethodForPost() {
        when(request.getMethod()).thenReturn("POST");
        assertTrue(isPostMethod(request));
    }

    @Test
    void testIsPostMethodForGet() {
        when(request.getMethod()).thenReturn("GET");
        assertFalse(isPostMethod(request));
    }

    @Test
    void testTransferUserDTOFromSessionToRequest() {
        MyRequest myRequest = new MyRequest(request);
        myRequest.getSession().setAttribute(USER, getTestUserDTO());
        transferUserDTOFromSessionToRequest(myRequest);
        assertEquals(getTestUserDTO(), myRequest.getAttribute(USER));
        assertNull(myRequest.getSession().getAttribute(USER));
    }

    @Test
    void testTransferUserDTOFromSessionToRequestNoUser() {
        MyRequest myRequest = new MyRequest(request);
        transferUserDTOFromSessionToRequest(myRequest);
        assertNull(myRequest.getAttribute(USER));
        assertNull(myRequest.getSession().getAttribute(USER));
    }


    @Test
    void testTransferStringFromSessionToRequest() {
        MyRequest myRequest = new MyRequest(request);
        myRequest.getSession().setAttribute(MESSAGE, SUCCEED_UPDATE);
        transferStringFromSessionToRequest(myRequest, MESSAGE);
        assertEquals(SUCCEED_UPDATE, myRequest.getAttribute(MESSAGE));
        assertNull(myRequest.getSession().getAttribute(MESSAGE));
    }

    @Test
    void testTransferStringFromSessionToRequestNullString() {
        MyRequest myRequest = new MyRequest(request);
        transferStringFromSessionToRequest(myRequest, MESSAGE);
        assertNull(myRequest.getAttribute(MESSAGE));
        assertNull(myRequest.getSession().getAttribute(MESSAGE));
    }

    @Test
    void testGetActionToRedirectNoParameters() {
        String result = "controller?action=delete-user";
        assertEquals(result, getActionToRedirect(DELETE_USER_ACTION));
    }

    @Test
    void testGetActionToRedirectWithParameter() {
        String result = "controller?action=delete-user&user_id=1";
        assertEquals(result, getActionToRedirect(DELETE_USER_ACTION, USER_ID, "1"));
    }

    @Test
    void testGetActionToRedirectWithParameters() {
        String result = "controller?action=delete-user&user_id=1&todo=set";
        assertEquals(result, getActionToRedirect(DELETE_USER_ACTION, USER_ID, "1", TODO, SET));
    }

    @Test
    void testGetURL() {
        String result = "http://localhost:8080/conferences";
        when(request.getServletPath()).thenReturn("/controller");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/conferences/controller"));
        assertEquals(result, getURL(request));
    }

    private UserDTO getTestUserDTO() {
        return UserDTO.builder()
                .id(ID_VALUE)
                .email(EMAIL_VALUE)
                .name(NAME_VALUE)
                .surname(SURNAME_VALUE)
                .role(ROLE_VISITOR)
                .build();
    }

}