package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
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
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    SupplyService service = factory.getSupplyService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        List<Need> result = service.findAllNeed(ConnectionPool.getInstance().getConnection());
        request.setAttribute("result", result);
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
