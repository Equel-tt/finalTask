package by.allahverdiev.finaltask.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    HttpServletRequest execute(HttpServletRequest request);
}
