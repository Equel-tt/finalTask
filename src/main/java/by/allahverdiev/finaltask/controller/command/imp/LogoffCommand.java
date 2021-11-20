package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class LogoffCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoffCommand.class);
    DestinationMap map = new DestinationMap();

    ServiceFactory factory = ServiceFactory.getInstance();
    UserService service = factory.getUserService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        HttpSession session = request.getSession(false);
        logger.info(session.isNew() + " есть ли сессия");
//        session.removeAttribute("user");
        session.invalidate();
        request.setAttribute("result", "вы вышли");
//        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "redirect");
        return request;
    }
}
