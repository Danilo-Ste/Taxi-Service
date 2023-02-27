package Taxi_Service.controller.contextListeners;


import com.epam.taxi_service.controller.ContextListener;
import com.epam.taxi_service.controller.context.AppContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.MockedStatic;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContextListenerTest {
    private static final ServletContextEvent sce = mock(ServletContextEvent.class);
    private static final ServletContext servletContext = mock(ServletContext.class);

    @Test
    void testContextInitialized() {
        when(sce.getServletContext()).thenReturn(servletContext);
        try (MockedStatic<AppContext> mocked = mockStatic(AppContext.class)) {
            mocked.when(() -> AppContext.createAppContext(isA(ServletContext.class), isA(String.class)))
                    .thenAnswer(Answers.RETURNS_DEFAULTS);
            assertDoesNotThrow(() -> new ContextListener().contextInitialized(sce));
        }
    }

    @Test
    void testContextDestroyedException()  {
        Driver driver = mock(Driver.class);
        Stream<Driver> stream = Stream.of(driver);
        try (MockedStatic<DriverManager> mocked = mockStatic(DriverManager.class)) {
            mocked.when(DriverManager::drivers).thenReturn(stream);
            mocked.when(() -> DriverManager.deregisterDriver(driver)).thenThrow(SQLException.class);
            assertDoesNotThrow(() -> new ContextListener().contextDestroyed(sce));
        }
    }
}