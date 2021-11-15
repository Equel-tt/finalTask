package by.allahverdiev.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.FIND_PRODUCT_BY_ID, new FindProductByIdCommand());
        repository.put(CommandName.FIND_ALL_PRODUCTS_IN_CURRENT_DATE, new FindAllProdInCurrDateCommand());

    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
//            command = repository.get(CommandName.WRONG_COMMAND);
        }
        return command;
    }
}
