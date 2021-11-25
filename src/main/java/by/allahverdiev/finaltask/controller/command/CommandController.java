package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.TransactionFactory;
import by.allahverdiev.finaltask.dao.pool.PooledConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class CommandController {
    private final CommandProvider provider = new CommandProvider();

    public CommandController() {
    }

    public HttpServletRequest executeTask(HttpServletRequest request) {
//        String request = validator.takeLine();//TODO Валидаторы
        Command executionCommand;
        try {
            Connection connection = TransactionFactory.getInstance().getConnection();
            executionCommand = provider.getCommand((String) request.getAttribute("command"));
            request = executionCommand.execute(request, connection);
            TransactionFactory.getInstance().closeConnection((PooledConnection) connection);
        } catch (Exception e) {
            //TODO пустой catch
        }
        return request;
    }
}
