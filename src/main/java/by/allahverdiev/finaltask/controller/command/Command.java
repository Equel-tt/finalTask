package by.allahverdiev.finaltask.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public interface Command {
    HttpServletRequest execute(HttpServletRequest request, Connection connection);
}
