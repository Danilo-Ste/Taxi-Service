package Taxi_Service.controller.actions.implementation.base;


import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.implementation.base.SignOutAction;
import com.epam.taxi_service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static com.epam.taxi_service.controller.actions.implementation.Page.SIGN_IN_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.LOCALE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.LOGGED_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class SignOutActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() throws ServiceException {
        String en = "en";
        MyRequest myRequest = new MyRequest(request);
        myRequest.getSession().setAttribute(LOGGED_USER, UserDTO.builder().build());
        myRequest.getSession().setAttribute(LOCALE, en);
        assertEquals(SIGN_IN_PAGE, new SignOutAction().execute(myRequest, response));
        assertEquals(en, myRequest.getSession().getAttribute(LOCALE));
    }
}