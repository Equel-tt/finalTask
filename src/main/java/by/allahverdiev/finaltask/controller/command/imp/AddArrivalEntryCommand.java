package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.entity.User;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AddArrivalEntryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddArrivalEntryCommand.class);
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        String result = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String doc = request.getParameter("doc");
            int count = Integer.parseInt(request.getParameter("count"));
            Date date = format.parse(request.getParameter("date"));
            Product product = (Product) service.findProductByName(request.getParameter("product"), connection);
            double price = Double.parseDouble(request.getParameter("price"));
            User user = (User) request.getSession(false).getAttribute("user");

            if (service.addArrivalEntry(doc, count, date, product, price, user, connection)) {
                result = "success.add";
                logger.info(result);
            }
            request.setAttribute("result", result);
            request.getSession(false).setAttribute("result", result);
            request.getSession(false).setAttribute("uid", UUID.randomUUID());

            request.setAttribute("message", "message.success");
        } catch (ParseException | RuntimeException | SQLException e) {
            request.setAttribute("result", e.getMessage());
            request.setAttribute("message", "error.add"); //TODO вывести ошибки в отдельную категорию
            logger.info(e.getMessage());
        }
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
