package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.pool.ConnectionPool;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.service.BookkeepingService;
import by.allahverdiev.finaltask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllArchiveCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindAllArchiveCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    BookkeepingService service = factory.getBookkeepingService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request) {
        List<Archive> result = service.findAllArchive(ConnectionPool.getInstance().getConnection());
        request.setAttribute("result", result);
        return request;
    }
}
