package com.epam.taxi_service.controller.actions;

import com.epam.taxi_service.controller.actions.implementation.admin.*;
import com.epam.taxi_service.controller.actions.implementation.base.*;
import com.epam.taxi_service.controller.context.AppContext;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.epam.taxi_service.controller.actions.implementation.ActionNames.*;

public class ActionFactory {
    @Getter
    private static final ActionFactory actionFactory = new ActionFactory();

    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    private static final AppContext APP_CONTEXT = AppContext.getAppContext();

    static {
        ACTION_MAP.put(DEFAULT_ACTION, new DefaultAction());
        ACTION_MAP.put(SIGN_UP_ACTION, new SignUpAction(APP_CONTEXT));
        ACTION_MAP.put(SIGN_IN_ACTION, new SignInAction(APP_CONTEXT));
        ACTION_MAP.put(CREATE_ORDER_ACTION, new CreateOrderAction(APP_CONTEXT));
        ACTION_MAP.put(ERROR_ACTION, new ErrorAction());

        ACTION_MAP.put(SIGN_OUT_ACTION, new SignOutAction());
        ACTION_MAP.put(EDIT_PROFILE_ACTION, new EditProfileAction(APP_CONTEXT));
        ACTION_MAP.put(ADD_CAR_ACTION, new AddCarAction(APP_CONTEXT));
        ACTION_MAP.put(CHANGE_PASSWORD_ACTION, new ChangePasswordAction(APP_CONTEXT));

        ACTION_MAP.put(VIEW_USERS_ACTION, new ViewUsersAction(APP_CONTEXT));
        ACTION_MAP.put(SEARCH_USER_ACTION, new SearchUserAction(APP_CONTEXT));
        ACTION_MAP.put(DELETE_USER_ACTION, new DeleteUserAction(APP_CONTEXT));
        ACTION_MAP.put(SET_ROLE_ACTION, new SetRoleAction(APP_CONTEXT));


    }
    public Action createAction(String actionName) {
        return ACTION_MAP.getOrDefault(actionName, new DefaultAction());
    }
}
