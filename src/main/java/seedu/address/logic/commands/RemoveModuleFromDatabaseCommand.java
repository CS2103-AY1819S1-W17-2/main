package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Removes a module identified using it's module code from the module database.
 */
public class RemoveModuleFromDatabaseCommand extends Command {

    public static final String COMMAND_WORD = "removeModuleDB";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a module from database. "
            + "Parameters: "
            + "[MODULE_CODE]\n"
            + "Example " + COMMAND_WORD + " "
            + "CS2109\n";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    public static final String MESSAGE_MODULE_NOT_FOUND = "module does not exist";

    public static final String MESSAGE_NOT_ADMIN = "Only an admin user can execute this command";

    private final String code;

    public RemoveModuleFromDatabaseCommand(String code) {
        requireNonNull(code);
        this.code = code;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isAdmin()) {
            throw new CommandException(MESSAGE_NOT_ADMIN);
        }

        Optional<Module> moduleToRemove = model.getObservableModuleList()
                .stream()
                .filter(module -> module.getCode().equalsIgnoreCase(code))
                .findFirst();

        if (moduleToRemove.isPresent()) {
            model.removeModuleFromDatabase(moduleToRemove.get());
            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, code));
        } else {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveModuleFromDatabaseCommand // instanceof handles nulls
                && code.equals(((RemoveModuleFromDatabaseCommand) other).code)); // state check
    }
}
