package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class ProductSearchCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ProductSearchCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        List<String> result = service.prepareForSearch(connection);
        request.setAttribute("result", result);
        request.getSession(false).setAttribute("result", result);
        request.getSession(false).setAttribute("uid", UUID.randomUUID());
        request.setAttribute("way", "forward");
        switch (request.getParameter("mark")) {
            case "supply":
                request.setAttribute("destination", "/productSearch.jsp");
                break;
            case "warehouse":
                request.setAttribute("destination", "/arrivalAdd.jsp");
                break;
        }
        return request;
    }
}
