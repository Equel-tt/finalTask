package by.allahverdiev.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.FIND_PRODUCT_BY_ID, new FindProductByIdCommand());
        repository.put(CommandName.FIND_ALL_PRODUCTS_IN_CURRENT_DATE, new FindAllProdInCurrDateCommand());
        repository.put(CommandName.FIND_ALL_NEED, new FindAllNeedCommand());
        repository.put(CommandName.FIND_NEED_FOR_MONTH, new FindNeedForMonthCommand());
        repository.put(CommandName.ADD_ARCHIVE_ENTRY, new AddArchiveEntryCommand());
        repository.put(CommandName.FIND_ALL_ARCHIVE, new FindAllArchiveCommand());
        repository.put(CommandName.FIND_ARCHIVE_ENTRY_BY_MONTH, new FindArchiveEntryByMonthCommand());

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
