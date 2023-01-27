package com.epam.taxi_service.controller;

import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.actions.ActionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.slf4j.*;


import java.io.IOException;

import static com.epam.taxi_service.controller.actions.implementation.Page.ERROR_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.ACTION;

public class Controller extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static final ActionFactory ACTION_FACTORY = ActionFactory.getActionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(process(request, response)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(process(request, response));
    }

    private String process(HttpServletRequest request, HttpServletResponse response) {
        Action action = ACTION_FACTORY.createAction(request.getParameter(ACTION));
        String path = ERROR_PAGE;
        try {
            path = action.execute(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return path;
    }
}
