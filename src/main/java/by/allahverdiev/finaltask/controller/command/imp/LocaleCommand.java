package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class LocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LocaleCommand.class);

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        request.getSession(false).setAttribute("language", request.getParameter("language"));
        request.setAttribute("result", request.getSession(false).getAttribute("result"));
        request.setAttribute("way", "redirect");
        return request;
    }
}
