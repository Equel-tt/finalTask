package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.Need;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.SupplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class FindAllNeedCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindAllNeedCommand.class);
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    SupplyService service = factory.getSupplyService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        List<Need> result = service.findAllNeed(connection);
        request.getSession(false).setAttribute("uid", UUID.randomUUID());
        request.setAttribute("result", result);
        request.getSession(false).setAttribute("result", result);
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
