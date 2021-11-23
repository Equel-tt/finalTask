package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class RefreshCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RefreshCommand.class);

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        request.setAttribute("result", request.getSession(false).getAttribute("result"));
        request.setAttribute("way", "redirect");
        return request;
    }
}
