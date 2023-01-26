package com.epam.taxi_service.controllers.actions;

import com.epam.taxi_service.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.StringJoiner;

import static com.epam.taxi_service.controllers.actions.implementation.Page.*;
import static com.epam.taxi_service.controllers.actions.implementation.Parameters.*;

public class Util {

    public static boolean isPostMethod(HttpServletRequest request) {
        return request.getMethod().equals("POST");
    }


    public static String getPath(HttpServletRequest request){
        return (String) request.getSession().getAttribute(CURRENT_PATH);
    }


    public static void transferUserDTOFromSessionToRequest(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER);
        if (user != null) {
            request.setAttribute(USER, user);
            request.getSession().removeAttribute(USER);
        }
    }


    public static void transferStringFromSessionToRequest(HttpServletRequest request, String attributeName) {
        String attributeValue = (String) request.getSession().getAttribute(attributeName);
        if (attributeValue != null) {
            request.setAttribute(attributeName, attributeValue);
            request.getSession().removeAttribute(attributeName);
        }
    }


    public static String getActionToRedirect(String action, String... parameters) {
        String base = CONTROLLER_PAGE + "?" + ACTION + "=" + action;
        StringJoiner stringJoiner = new StringJoiner("&", "&", "");
        stringJoiner.setEmptyValue("");
        for (int i = 0; i < parameters.length; i+=2) {
            stringJoiner.add(parameters[i] + "=" + parameters[i + 1]);
        }
        return base + stringJoiner;
    }


    public static String getURL(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String requestURL = request.getRequestURL().toString();
        return requestURL.replace(servletPath, "");
    }
}
