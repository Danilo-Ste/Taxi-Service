package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.controller.actions.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.taxi_service.controller.actions.implementation.Page.ERROR_PAGE;

public class ErrorAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ERROR_PAGE;
    }
}
