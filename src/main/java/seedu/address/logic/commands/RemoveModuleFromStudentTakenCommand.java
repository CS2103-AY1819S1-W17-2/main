package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Deletes a module from the student's taken module list.
 * Keyword matching is case insensitive.
 */
public class RemoveModuleFromStudentTakenCommand extends Command {

    public static final String COMMAND_WORD = "removeModuleT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the module identified by its code.\n"
            + "Parameters: MOD_CODE(case insensitive)\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_REMOVE_MODULE_SUCCESS = "Removed Module: %1$s";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE = "This module does not exist in our database";
    public static final String MESSAGE_MODULE_NOT_EXISTS = "This module does not exist in your profile";
    public static final String MESSAGE_NOT_STUDENT = "Only a student user can execute this command";

    private final Module toSearch;
    private Module toRemove;

    public RemoveModuleFromStudentTakenCommand(Module module) {
        requireNonNull(module);
        this.toSearch = module;
        this.toRemove = null;
    }

    public Module getSearchedModule() {
        return toRemove;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isStudent()) {
            throw new CommandException(MESSAGE_NOT_STUDENT);
        }

        Optional<Module> optionalModule = model.searchModuleInModuleList(toSearch);

        if (optionalModule.isPresent()) {
            toRemove = optionalModule.get();
        } else {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        }
        if (!model.hasModuleTaken(toRemove)) {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS);
        }

        model.removeModuleTaken(toRemove);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_REMOVE_MODULE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveModuleFromStudentTakenCommand // instanceof handles nulls
                && toSearch.equals(((RemoveModuleFromStudentTakenCommand) other).toSearch)); // state check
    }
}
