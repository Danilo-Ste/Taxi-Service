package Taxi_Service.controller.actions.implementation.base;

import com.epam.taxi_service.controller.actions.implementation.base.ErrorAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static com.epam.taxi_service.controller.actions.implementation.Page.ERROR_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ErrorActionTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testExecute() {
        assertEquals(ERROR_PAGE, new ErrorAction().execute(request, response));
    }
}