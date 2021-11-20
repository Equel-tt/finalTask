package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.entity.User;
import by.allahverdiev.finaltask.service.AccessException;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    DestinationMap map = new DestinationMap();

    ServiceFactory factory = ServiceFactory.getInstance();
    UserService service = factory.getUserService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        try {
            User user = service.login(ConnectionPool.getInstance().getConnection(), request.getParameter("login"), request.getParameter("password"));
            logger.debug("старт команды Login");
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            request.setAttribute("result", "Успешно вошли в систему");
            request.setAttribute("destination", map.getDestination(this.getClass().getName()));
            logger.debug("вышли из команды");
        } catch (AccessException e) {
            logger.debug("попали в исключение команды Login");
            request.setAttribute("result", e.getMessage());
            logger.info(e.getMessage());
            request.setAttribute("destination", "/index.jsp");
        }
        request.setAttribute("way", "forward");
        return request;
    }
}
