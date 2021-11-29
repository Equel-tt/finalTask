package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListenerImpl.class);

    public void sessionCreated(HttpSessionEvent event) {
        logger.info(event);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info(event);
    }
}
