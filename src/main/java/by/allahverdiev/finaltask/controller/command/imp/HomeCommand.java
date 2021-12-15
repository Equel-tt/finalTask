package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class HomeCommand implements Command {

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        User user = (User) request.getSession(false).getAttribute("user");
        switch (user.getRole()) {
            case 1:
                request.setAttribute("destination", "/homePageBookkeeping.jsp");
                break;
            case 2:
                request.setAttribute("destination", "/homePageSupply.jsp");
                break;
            case 3:
                request.setAttribute("destination", "/homePageWarehouse.jsp");
                break;
        }
        request.removeAttribute("result");
        request.getSession(false).removeAttribute("result");
        String destination = "/WEB-INF/jsp" + request.getAttribute("destination");
        request.getSession(false).setAttribute("lastPage", destination);
        request.setAttribute("way", "forward");
        return request;
    }
}
