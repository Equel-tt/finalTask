package by.allahverdiev.finaltask.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandController {
    private final CommandProvider provider = new CommandProvider();

    public CommandController() {
    }

    public HttpServletRequest executeTask(HttpServletRequest request) {
//        String request = validator.takeLine();
        Command executionCommand;
        try {
            executionCommand = provider.getCommand(request.getParameter("command"));
            request = executionCommand.execute(request);
        } catch (Exception e) {

        }
        return request;
    }
}
