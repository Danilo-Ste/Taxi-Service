package Taxi_Service.controller.actions.implementation.admin;


import Taxi_Service.controller.actions.util.MyRequest;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.implementation.admin.ViewUsersAction;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.models.services.UserService;
import com.epam.taxi_service.utils.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static Taxi_Service.controller.actions.util.Util.getUserDTOs;
import static com.epam.taxi_service.controller.actions.implementation.Page.*;
import static com.epam.taxi_service.controller.actions.implementation.ParameterValues.DESCENDING_ORDER;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.utils.QueryBuilderUtil.userQueryBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ViewUsersActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final AppContext appContext = mock(AppContext.class);
    private final UserService userService = mock(UserService.class);
    private final String SPEAKER = "3";
    private final String ZERO = "0";
    private final String FIVE = "5";

    @Test
    void testExecute() throws ServiceException {
        setRequest();
        MyRequest myRequest = new MyRequest(request);
        when(appContext.getUserService()).thenReturn(userService);
        when(userService.getSortedUsers(getQueryBuilder().getQuery())).thenReturn(getUserDTOs());
        when(userService.getNumberOfRecords(getQueryBuilder().getRecordQuery())).thenReturn(10);

        assertEquals(VIEW_USERS_PAGE, new ViewUsersAction(appContext).execute(myRequest, response));
        assertEquals(getUserDTOs(), myRequest.getAttribute(USERS));
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(2, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(2, myRequest.getAttribute(END));
    }

    private void setRequest() {
        when(request.getParameter(ROLE)).thenReturn(SPEAKER);
        when(request.getParameter(SORT)).thenReturn(NAME);
        when(request.getParameter(ORDER)).thenReturn(DESCENDING_ORDER);
        when(request.getParameter(OFFSET)).thenReturn(ZERO);
        when(request.getParameter(RECORDS)).thenReturn(FIVE);
    }

    private QueryBuilder getQueryBuilder() {
        return userQueryBuilder()
                .setRoleFilter(SPEAKER)
                .setSortField(NAME)
                .setOrder(DESCENDING_ORDER)
                .setLimits(ZERO, FIVE);
    }
}