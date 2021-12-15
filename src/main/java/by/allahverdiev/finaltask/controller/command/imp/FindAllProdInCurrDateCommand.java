package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.util.DateConversion;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FindAllProdInCurrDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindAllProdInCurrDateCommand.class);
    DateConversion conversion = new DateConversion();
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();


    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        try {
            String s = (String) request.getAttribute("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = format.parse(s);
            LocalDate date = conversion.toLocalDate(tempDate);
            Map<Product, Integer> result = service.findAllProductsCountInCurrentDate(date, connection);
            request.getSession(false).setAttribute("uid", UUID.randomUUID());
            request.setAttribute("result", result);
            request.getSession(false).setAttribute("result", result);
            request.setAttribute("destination", map.getDestination(this.getClass().getName()));
            request.setAttribute("way", "forward");
        } catch (ParseException | RegulationException e) {
            logger.info(e.getMessage());
        }
        return request;
    }
}
