package com.epam.taxi_service.controllers.filters;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static com.epam.taxi_service.controllers.actions.implementation.ActionNames.*;

public class DomainActionsSets {
    private DomainActionsSets() {}

    @Getter private static final Set<String> anonymousUserActions = new HashSet<>();
    private static final Set<String> loggedUserActions = new HashSet<>();
    @Getter private static final Set<String> visitorActions = new HashSet<>();
    @Getter private static final Set<String> speakerActions = new HashSet<>();
    @Getter private static final Set<String> moderatorActions = new HashSet<>();
    @Getter private static final Set<String> adminActions = new HashSet<>();

    static {
        anonymousUserActions.add(DEFAULT_ACTION);
        anonymousUserActions.add(SIGN_IN_ACTION);
        anonymousUserActions.add(SIGN_UP_ACTION);
        anonymousUserActions.add(PASSWORD_RESET_ACTION);
        anonymousUserActions.add(ERROR_ACTION);
        anonymousUserActions.add(SIGN_OUT_ACTION);
    }

    static {
        loggedUserActions.addAll(anonymousUserActions);
        loggedUserActions.add(EDIT_PROFILE_ACTION);
        loggedUserActions.add(CHANGE_PASSWORD_ACTION);
    }

    static {
        visitorActions.addAll(loggedUserActions);
        visitorActions.add(VIEW_UPCOMING_EVENTS_ACTION);
        visitorActions.add(VIEW_VISITORS_EVENTS_ACTION);
        visitorActions.add(VIEW_EVENT_BY_VISITOR_ACTION);
        visitorActions.add(REGISTER_OR_CANCEL_ACTION);
    }

    static {
        adminActions.addAll(loggedUserActions);
        adminActions.add(SEARCH_USER_ACTION);
        adminActions.add(DELETE_USER_ACTION);
        adminActions.add(SET_ROLE_ACTION);
        adminActions.add(VIEW_USERS_ACTION);
        adminActions.add(USERS_PDF_ACTION);
    }
}
