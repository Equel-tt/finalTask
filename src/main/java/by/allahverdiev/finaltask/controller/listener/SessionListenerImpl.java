package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListenerImpl.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info(event);
    }
}
