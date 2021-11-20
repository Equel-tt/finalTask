package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.dao.RegulationException;
import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.service.BookkeepingService;
import by.allahverdiev.finaltask.service.DateConversion;
import by.allahverdiev.finaltask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddArchiveEntryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddArchiveEntryCommand.class);
    DestinationMap map = new DestinationMap();
    DateConversion conversion = new DateConversion();

    ServiceFactory factory = ServiceFactory.getInstance();
    BookkeepingService service = factory.getBookkeepingService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        String answer = "";
        try {
            String s = request.getParameter("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(s);
            LocalDate month = conversion.toLocalDate(date);
            logger.info(month);
            if (service.createArchiveEntry(month, ConnectionPool.getInstance().getConnection())) {
                answer = "success";
                logger.info(answer);
            }
            request.setAttribute("result", answer);
        } catch (ParseException | RuntimeException | RegulationException e) {
            request.setAttribute("result", e.getMessage());
            logger.info(e.getMessage());
        }
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
