package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.dao.postgres.ConsumptionDaoPg;
import by.allahverdiev.finaltask.entity.Product;
import by.allahverdiev.finaltask.service.DateConversion;
import by.allahverdiev.finaltask.service.ServiceFactory;
import by.allahverdiev.finaltask.service.WarehouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class FindAllProdInCurrDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindProductByIdCommand.class);
    DateConversion conversion = new DateConversion();
    ServiceFactory factory = ServiceFactory.getInstance();
    WarehouseService service = factory.getWarehouseService();


    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        try {
            String s = request.getParameter("end");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(s);
            LocalDate end = conversion.toLocalDate(date);
            logger.info(end);
            Map<Product, Integer> products = service.findAllProductsCountInCurrentDate(ConsumptionDaoPg.startDate, end, ConnectionPool.getInstance().getConnection());
            logger.info(products.isEmpty());
            request.setAttribute("result", products);
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        return request;
    }
}
