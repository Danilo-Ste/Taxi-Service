package Taxi_Service.controller.actions;

import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.actions.ActionFactory;
import com.epam.taxi_service.controller.actions.implementation.base.DefaultAction;
import com.epam.taxi_service.controller.actions.implementation.base.SignInAction;
import com.epam.taxi_service.controller.context.AppContext;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static com.epam.taxi_service.controller.actions.implementation.ActionNames.SIGN_IN_ACTION;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class ActionFactoryTest {
    private final AppContext appContext = mock(AppContext.class);

    @Test
    void testCreateAction() {
        try (MockedStatic<AppContext> mocked = mockStatic(AppContext.class)) {
            mocked.when(AppContext::getAppContext).thenReturn(appContext);
            ActionFactory actionFactory = ActionFactory.getActionFactory();
            Action action = actionFactory.createAction(SIGN_IN_ACTION);
            assertInstanceOf(SignInAction.class, action);
        }
    }

    @Test
    void testDefaultAction() {
        try (MockedStatic<AppContext> mocked = mockStatic(AppContext.class)) {
            mocked.when(AppContext::getAppContext).thenReturn(appContext);
            ActionFactory actionFactory = ActionFactory.getActionFactory();
            Action action = actionFactory.createAction("wrongName");
            assertInstanceOf(DefaultAction.class, action);
        }
    }
}