package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ServletListenerImpl.class);

    public void contextInitialized(ServletContextEvent event) {
        logger.info(event);
    }

    public void contextDestroyed(ServletContextEvent event) {
        logger.info(event);
    }
}
