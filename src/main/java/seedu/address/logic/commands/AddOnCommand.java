package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the user's profile.
 * Keyword matching is case insensitive.
 */
public class AddOnCommand extends Command {

    public static final String COMMAND_WORD = "addon";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds on one module to your profile. "
            + "Parameters: "
            + "MOD_CODE(case insensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in your profile: %1$s";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE = "This module does not exist in our database";

    private final Module toSearch;
    private Module toAdd;

    /**
     * Creates an AddOnCommand to add the specified {@code module}
     */
    public AddOnCommand(Module module) {
        requireNonNull(module);
        toSearch = module;
        toAdd = null;
    }

    public Module getSearchedMoudle() {
        return toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        Optional<Module> optionalModule = model.searchModuleInModuleList(toSearch);
        if (optionalModule.isPresent()) {
            toAdd = optionalModule.get();
        } else {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        }
        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOnCommand // instanceof handles nulls
                && toSearch.equals(((AddOnCommand) other).toSearch));
    }
}
