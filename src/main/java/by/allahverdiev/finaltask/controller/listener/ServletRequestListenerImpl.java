package by.allahverdiev.finaltask.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class ServletRequestListenerImpl implements ServletRequestAttributeListener {
    private static final Logger logger = LogManager.getLogger(ServletRequestListenerImpl.class);

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        logger.info("add: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        logger.info("replace: " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent e) {
        logger.info(e);
    }
}
