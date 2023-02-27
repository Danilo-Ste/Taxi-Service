package Taxi_Service.controller.actions.implementation.base;


import com.epam.taxi_service.controller.actions.implementation.base.DefaultAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static com.epam.taxi_service.controller.actions.implementation.Page.INDEX_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class DefaultActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() {
        assertEquals(INDEX_PAGE, new DefaultAction().execute(request, response));
    }
}