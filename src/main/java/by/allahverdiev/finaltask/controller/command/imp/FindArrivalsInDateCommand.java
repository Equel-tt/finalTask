package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.Arrival;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FindArrivalsInDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindArrivalsInDateCommand.class);
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter("date"));
            List<Arrival> result = service.findArrivalInDate(date, connection);
            request.getSession(false).setAttribute("uid", UUID.randomUUID());
            request.setAttribute("result", result);
            request.getSession(false).setAttribute("result", result);
            request.setAttribute("destination", map.getDestination(this.getClass().getName()));
            request.setAttribute("way", "forward");
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        return request;
    }
}
