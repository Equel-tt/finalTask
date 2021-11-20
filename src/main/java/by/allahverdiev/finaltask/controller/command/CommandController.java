package by.allahverdiev.finaltask.controller.command;

import by.allahverdiev.finaltask.dao.TransactionFactory;

import javax.servlet.http.HttpServletRequest;

public class CommandController {
    private final CommandProvider provider = new CommandProvider();

    public CommandController() {
    }

    public HttpServletRequest executeTask(HttpServletRequest request) {
//        String request = validator.takeLine();//TODO Валидаторы
        Command executionCommand;
        try {
            executionCommand = provider.getCommand(request.getParameter("command"));
            request = executionCommand.execute(request, TransactionFactory.getInstance().getConnection());
        } catch (Exception e) {
            //TODO пустой catch
        }
        return request;
    }
}
