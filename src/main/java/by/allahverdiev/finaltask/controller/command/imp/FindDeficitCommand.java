package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.dao.util.DateConversion;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.entity.User;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.SupplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FindDeficitCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindDeficitCommand.class);
    DateConversion conversion = new DateConversion();
    DestinationMap map = new DestinationMap();

    ServiceFactory factory = ServiceFactory.getInstance();
    SupplyService service = factory.getSupplyService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        try {
            String s = (String) request.getAttribute("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = format.parse(s);
            LocalDate date = conversion.toLocalDate(tempDate);
            User user = (User) request.getSession(false).getAttribute("user");
            Map<Product, ArrayList<Integer>> result = service.findDeficit(date, user.getId(), connection);
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
