package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.entity.Archive;
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
import java.util.List;

public class FindArchiveEntryByMonthCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindArchiveEntryByMonthCommand.class);
    DateConversion conversion = new DateConversion();
    DestinationMap map = new DestinationMap();

    ServiceFactory factory = ServiceFactory.getInstance();
    BookkeepingService service = factory.getBookkeepingService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        try {
            String s = request.getParameter("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = format.parse(s);
            LocalDate date = conversion.toLocalDate(tempDate);
            List<Archive> result = service.findArchiveEntryByMonth(date, ConnectionPool.getInstance().getConnection());
            request.setAttribute("result", result);
            request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        return request;
    }
}