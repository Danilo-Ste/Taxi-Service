package com.epam.taxi_service.controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.epam.taxi_service.controllers.actions.implementation.Page.SIGN_IN_PAGE;
import static com.epam.taxi_service.controllers.actions.implementation.ParameterValues.ACCESS_DENIED;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;
import static com.epam.taxi_service.controllers.filters.Domain.getDomain;

public class AuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String servletPath = httpRequest.getServletPath();
        String action = httpRequest.getParameter(ACTION);
        if (isNoLoggedUser(httpRequest) && isAccessDenied(servletPath, action)) {
            httpRequest.setAttribute(MESSAGE, ACCESS_DENIED);
            request.getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
    private boolean isAccessDenied(String servletPath, String action) {
        return getDomain(servletPath, action).checkAccess();
    }
    private static boolean isNoLoggedUser(HttpServletRequest request) {
        return request.getSession().getAttribute(LOGGED_USER) == null;
    }
}
