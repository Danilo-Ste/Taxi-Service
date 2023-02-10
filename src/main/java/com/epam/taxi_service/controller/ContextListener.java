package com.epam.taxi_service.controller;

import com.epam.taxi_service.controller.context.AppContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.*;

public class ContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    private static final String PROPERTIES_FILE = "context.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        AppContext.createAppContext(sce.getServletContext(), PROPERTIES_FILE);
        logger.info("AppContext is set");
    }
}
