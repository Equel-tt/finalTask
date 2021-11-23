package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.User;
import by.allahverdiev.finaltask.service.AccessException;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.UUID;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    DestinationMap map = new DestinationMap();

    ServiceFactory factory = ServiceFactory.getInstance();
    UserService service = factory.getUserService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        try {
            User user = service.login(connection, request.getParameter("login"), request.getParameter("password"));
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("uid", UUID.randomUUID());
            if (user.getRole() == 2) {
                request.setAttribute("destination", "/supplyHomePage.jsp");
            }
            request.setAttribute("way", "forward");
        } catch (AccessException e) {
            logger.debug("исключение команды Login");
            logger.info(e.getMessage());
            request.setAttribute("way", "directRedirect");
        }
        return request;
    }
}
