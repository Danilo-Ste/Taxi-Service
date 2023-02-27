package Taxi_Service.controller.actions.implementation.admin;


import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.implementation.admin.SetRoleAction;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.Role;
import com.epam.taxi_service.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static Taxi_Service.Constants.*;
import static com.epam.taxi_service.controller.actions.Util.getActionToRedirect;
import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SEARCH_USER_ACTION;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SetRoleActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final AppContext appContext = mock(AppContext.class);
    private final UserService userService = mock(UserService.class);

    @Test
    void testExecute() throws ServiceException {
        String email = EMAIL_VALUE;
        String role = Role.ADMIN.name();
        MyRequest myRequest = new MyRequest(request);
        when(request.getParameter(EMAIL)).thenReturn(email);
        when(request.getParameter(ROLE)).thenReturn(role);
        when(appContext.getUserService()).thenReturn(userService);
        doNothing().when(userService).setRole(email, 1);
        UserDTO user = UserDTO.builder().role(role).build();
        when(userService.getByEmail(email)).thenReturn(user);

        String path = new SetRoleAction(appContext).execute(myRequest, response);
        assertEquals(getActionToRedirect(SEARCH_USER_ACTION, EMAIL, email), path);
        assertEquals(user, myRequest.getAttribute(USER));
    }
}