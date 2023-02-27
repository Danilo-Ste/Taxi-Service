package Taxi_Service.controller.context;

import com.epam.taxi_service.controller.context.AppContext;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class AppContextTest {
    private static final String PROPERTIES_FILE = "context.properties";
    private final ServletContext SERVLET_CONTEXT = mock(ServletContext.class);

    @Test
    void testRightFile() {
        assertDoesNotThrow(() -> AppContext.createAppContext(SERVLET_CONTEXT, PROPERTIES_FILE));
        AppContext appContext = AppContext.getAppContext();
        assertNotNull(appContext.getUserService());

    }

    @Test
    void testWrongFile() {
        assertDoesNotThrow(() -> AppContext.createAppContext(SERVLET_CONTEXT, "wrong"));
        AppContext appContext = AppContext.getAppContext();
        assertNotNull(appContext.getUserService());

    }
}