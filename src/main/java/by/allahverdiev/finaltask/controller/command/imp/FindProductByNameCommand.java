package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.UUID;

public class FindProductByNameCommand implements Command {
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();
    DestinationMap map = new DestinationMap();


    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        Product result = (Product) service.findProductByName(request.getParameter("product"), connection);
        request.setAttribute("result", result);
        request.getSession(false).setAttribute("result", result);
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        request.getSession(false).removeAttribute("uid");
        request.getSession(false).setAttribute("uid", UUID.randomUUID());
        return request;
    }
}
