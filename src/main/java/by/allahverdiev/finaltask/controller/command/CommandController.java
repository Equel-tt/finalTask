package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.TransactionFactory;
import by.allahverdiev.finaltask.dao.pool.PooledConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class CommandController {
    private final CommandProvider provider = new CommandProvider();

    public HttpServletRequest executeTask(HttpServletRequest request) {
        Command executionCommand;
        Connection connection = TransactionFactory.getInstance().getConnection();
        executionCommand = provider.getCommand((String) request.getAttribute("command"));
        request = executionCommand.execute(request, connection);
        TransactionFactory.getInstance().closeConnection((PooledConnection) connection);
        return request;
    }
}
