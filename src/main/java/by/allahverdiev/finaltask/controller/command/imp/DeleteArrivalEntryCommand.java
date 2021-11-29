package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;
import by.allahverdiev.finaltask.controller.command.DestinationMap;
import by.allahverdiev.finaltask.service.BookkeepingService;
import by.allahverdiev.finaltask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteArrivalEntryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteArrivalEntryCommand.class);
    DestinationMap map = new DestinationMap();
    ServiceFactory factory = ServiceFactory.getInstance();
    BookkeepingService service = factory.getBookkeepingService();

    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
        String document = request.getParameter("doc");
        int id = Integer.parseInt(request.getParameter("product"));
        try {
            if (service.deleteArrivalEntry(connection, document, id)) {
                request.setAttribute("message", "message.success.delete");
            } else {
                request.setAttribute("error", "error.delete.issue");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        request.getSession(false).setAttribute("uid", UUID.randomUUID());
        request.setAttribute("destination", map.getDestination(this.getClass().getName()));
        request.setAttribute("way", "forward");
        return request;
    }
}
