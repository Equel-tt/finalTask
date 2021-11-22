package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        HttpSession session = request.getSession(false);
        logger.debug(session.isNew());
        session.invalidate();
        request.setAttribute("way", "directRedirect");
        return request;
    }
}
