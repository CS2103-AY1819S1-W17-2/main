package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Deletes a module from the user's profile.
 * Keyword matching is case insensitive.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the module identified by its code.\n"
            + "Parameters: MOD_CODE(case insensitive)\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_REMOVE_MODULE_SUCCESS = "Removed Module: %1$s";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE = "This module does not exist in our database";
    public static final String MESSAGE_MODULE_NOT_EXISTS = "This module does not exist in your profile";

    private final Module toSearch;
    private Module toRemove;

    public RemoveCommand(Module module) {
        requireNonNull(module);
        this.toSearch = module;
        this.toRemove = null;
    }

    public Module getSearchedMoudle() {
        return toRemove;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        Optional<Module> optionalModule = model.searchModuleInModuleList(toSearch);
        if (optionalModule.isPresent()) {
            toRemove = optionalModule.get();
        } else {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        }
        if (!model.hasModule(toRemove)) {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS);
        }

        model.removeModule(toRemove);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_REMOVE_MODULE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && toSearch.equals(((RemoveCommand) other).toSearch)); // state check
    }
}
