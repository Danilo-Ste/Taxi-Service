package com.epam.taxi_service.controller.filters;

import com.epam.taxi_service.models.entities.Role;

import java.util.Set;

public class Domain {
    private final String servletPath;

    /** contains action name */
    private final String action;

    /** contains user allowed pages */
    private Set<String> domainPages;

    /** contains user allowed actions */
    private Set<String> domainActions;

    private Domain(String servletPath, String action) {
        this.servletPath = servletPath;
        this.action = action;
        setDomains();
    }

    private Domain(String servletPath, String action, String role) {
        this.servletPath = servletPath;
        this.action = action;
        setDomains(role);
    }

    /**
     * Obtains Domain for anonymous user
     * @param servletPath - contains page to access
     * @param action - contains action to call
     * @return Domain
     */
    public static Domain getDomain(String servletPath, String action) {
        return new Domain(servletPath, action);
    }


    public static Domain getDomain(String servletPath, String action, String role) {
        return new Domain(servletPath, action, role);
    }

    private void setDomains() {
        domainPages = DomainPagesSets.getAnonymousUserPages();
        domainActions = DomainActionsSets.getAnonymousUserActions();
    }

    private void setDomains(String role) {
        Role roleValue = Role.valueOf(role);
        switch (roleValue) {
            case ADMIN: domainPages = DomainPagesSets.getAdminPages();
                domainActions = DomainActionsSets.getAdminActions();
                break;
            default: domainPages = DomainPagesSets.getVisitorPages();
                domainActions = DomainActionsSets.getVisitorActions();
        }
    }

    public boolean checkAccess() {
        return !checkPages() || !checkActions();
    }

    private boolean checkPages() {
        if (servletPath != null) {
            return domainPages.contains(servletPath.substring(1));
        }
        return true;
    }

    private boolean checkActions() {
        if (action != null) {
            return domainActions.contains(action);
        }
        return true;
    }
}
