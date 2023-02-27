package com.epam.taxi_service.controller.filters;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static com.epam.taxi_service.controller.actions.implementation.Page.*;

public class DomainPagesSets {
    private DomainPagesSets() {}

    @Getter
    private static final Set<String> anonymousUserPages = new HashSet<>();
    private static final Set<String> loggedUserPages = new HashSet<>();
    @Getter private static final Set<String> visitorPages = new HashSet<>();
    @Getter private static final Set<String> speakerPages = new HashSet<>();
    @Getter private static final Set<String> moderatorPages = new HashSet<>();
    @Getter private static final Set<String> adminPages = new HashSet<>();

    static {
        anonymousUserPages.add(CONTROLLER_PAGE);
        anonymousUserPages.add(INDEX_PAGE);
        anonymousUserPages.add(ABOUT_PAGE);
        anonymousUserPages.add(ERROR_PAGE);
        anonymousUserPages.add(SIGN_IN_PAGE);
        anonymousUserPages.add(SIGN_UP_PAGE);
        anonymousUserPages.add(RESET_PASSWORD_PAGE);
    }

    static {
        loggedUserPages.addAll(anonymousUserPages);
        loggedUserPages.add(PROFILE_PAGE);
        loggedUserPages.add(CREATE_ORDER_PAGE);
        loggedUserPages.add(UNABLE_TO_CREATE_AN_ORDER_PAGE);
        loggedUserPages.add(EDIT_PROFILE_PAGE);
        loggedUserPages.add(CHANGE_PASSWORD_PAGE);
    }

    static {
        visitorPages.addAll(loggedUserPages);
    }

    static {
        adminPages.addAll(loggedUserPages);
        adminPages.add(ADD_CAR_PAGE);
        adminPages.add(VIEW_USERS_PAGE);
        adminPages.add(SEARCH_USER_PAGE);
    }
}
