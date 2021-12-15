package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger logger = LogManager.getLogger(SessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("add: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("removed: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("replaced: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }
}
