package com.epam.taxi_service.controllers.actions.implementation.base;

import com.epam.taxi_service.controllers.actions.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controllers.actions.implementation.Page.INDEX_PAGE;

public class DefaultAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return INDEX_PAGE;
    }
}
