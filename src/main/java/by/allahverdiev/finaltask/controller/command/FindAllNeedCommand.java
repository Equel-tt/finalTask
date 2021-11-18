package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.SupplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllNeedCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindAllNeedCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    SupplyService service = factory.getSupplyService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        List<Need> result = service.findAllNeed(ConnectionPool.getInstance().getConnection());
        request.setAttribute("result", result);
        return request;
    }
}
