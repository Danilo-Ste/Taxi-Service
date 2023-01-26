package com.epam.taxi_service.controllers.context;

import com.epam.taxi_service.models.connectionPool.ConnectionPool;
import com.epam.taxi_service.models.dao.*;
import com.epam.taxi_service.models.services.CarServices;
import com.epam.taxi_service.models.services.OrderService;
import com.epam.taxi_service.models.services.ServiceFactory;
import com.epam.taxi_service.models.services.UserService;
import lombok.*;
import jakarta.servlet.ServletContext;
import org.slf4j.*;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class AppContext {
    private static final Logger logger = LoggerFactory.getLogger(AppContext.class);
    private static AppContext appContext;

    private final OrderService orderService;
    private final CarServices carService;
    private final UserService userService;
    //private final EmailSender emailSender;
    //private final PdfUtil pdfUtil;

    private AppContext(ServletContext servletContext, String propertiesFile) {
        //pdfUtil = new PdfUtil(servletContext);
        Properties properties = getProperties(propertiesFile);
        //emailSender = new EmailSender(properties);
        DataSource dataSource = ConnectionPool.getDataSource(properties);
        DAOFactory daoFactory = DAOFactory.getInstance( dataSource);
        ServiceFactory serviceFactory = ServiceFactory.getInstance(daoFactory);
        orderService = serviceFactory.getOrderService();
        carService =  serviceFactory.getCarService();
        userService = serviceFactory.getUserService();
    }


    public static AppContext getAppContext() {
        return appContext;
    }


    public static void createAppContext(ServletContext servletContext, String propertiesFile) {
        appContext = new AppContext(servletContext, propertiesFile);
    }

    private static Properties getProperties(String propertiesFile) {
        Properties properties = new Properties();
        try (InputStream resource = AppContext.class.getClassLoader().getResourceAsStream(propertiesFile)){
            properties.load(resource);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return properties;
    }
}
