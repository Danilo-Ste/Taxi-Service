package com.epam.taxi_service.controller.actions.implementation.base;


import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import jakarta.servlet.http.*;

import static com.epam.taxi_service.controller.actions.implementation.Page.SIGN_IN_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

public class SignOutAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if (session.getAttribute(LOGGED_USER) != null) {
            String locale = (String) session.getAttribute(LOCALE);
            session.invalidate();
            request.getSession(true).setAttribute(LOCALE, locale);
        }
        return SIGN_IN_PAGE;
    }
}
