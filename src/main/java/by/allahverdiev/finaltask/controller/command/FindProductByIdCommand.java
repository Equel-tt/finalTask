package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.entity.Entity;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FindProductByIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindProductByIdCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();


    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        Entity product = service.findProductById(Integer.parseInt(request.getParameter("productId")), ConnectionPool.getInstance().getConnection());
        request.setAttribute("result", product);
        return request;
    }
}
