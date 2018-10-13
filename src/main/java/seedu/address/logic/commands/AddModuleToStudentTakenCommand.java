package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the student's taken module list.
 * Keyword matching is case insensitive.
 */
public class AddModuleToStudentTakenCommand extends Command {

    public static final String COMMAND_WORD = "addModuleT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds on one module to your profile. "
            + "Parameters: "
            + "MOD_CODE(case insensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in your profile: %1$s";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE = "This module does not exist in our database";
    public static final String MESSAGE_NOT_STUDENT = "Only a student user can execute this command";

    private final Module toSearch;
    private Module toAdd;

    /**
     * Creates an AddModuleToStudentTakenCommand to add the specified {@code module}
     */
    public AddModuleToStudentTakenCommand(Module module) {
        requireNonNull(module);
        toSearch = module;
        toAdd = null;
    }

    public Module getSearchedModule() {
        return toAdd;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isStudent()) {
            throw new CommandException(MESSAGE_NOT_STUDENT);
        }

        Optional<Module> optionalModule = model.searchModuleInModuleList(toSearch);

        if (optionalModule.isPresent()) {
            toAdd = optionalModule.get();
        } else {
            throw new CommandException(MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        }

        if (model.hasModuleTaken(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_MODULE, toAdd));
        }

        model.addModuleTaken(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleToStudentTakenCommand // instanceof handles nulls
                && toSearch.equals(((AddModuleToStudentTakenCommand) other).toSearch));
    }
}
