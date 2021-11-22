package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class FindProductByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindProductByNameCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();
    DestinationMap map = new DestinationMap();


    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        Product product = (Product) service.findProductByName(request.getParameter("product"), connection);
        request.setAttribute("result", product);
        request.getSession(false).setAttribute("result", product);
        logger.info(request.getSession(false).getAttribute("result") == null);
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
