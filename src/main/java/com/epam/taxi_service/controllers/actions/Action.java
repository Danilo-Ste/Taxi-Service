package com.epam.taxi_service.controllers.actions;

import com.epam.taxi_service.Exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
