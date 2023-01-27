package com.epam.taxi_service.controller;

import com.epam.taxi_service.controller.context.AppContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.*;

public class ContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    /** Name of properties file to configure DataSource, EmailSender and Captcha */
    private static final String PROPERTIES_FILE = "context.properties";

    /**
     * creates AppContext and passes ServletContext and properties to initialize all required classes
     * @param sce passed by application
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AppContext.createAppContext(sce.getServletContext(), PROPERTIES_FILE);
        logger.info("AppContext is set");
    }
}
