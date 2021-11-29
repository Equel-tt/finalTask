package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger logger = LogManager.getLogger(SessionAttributeListenerImpl.class);

    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("add: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("replace: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent e) {

    }
}
