package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.module.Module;

/**
 * Deletes several modules from the student's staged module list.
 * Keyword matching is case insensitive.
 */
public class RemoveModuleFromStudentStagedCommand extends Command {

    public static final String COMMAND_WORD = "removeModuleS";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the module identified by its code.\n"
            + "Parameters: MOD_CODE(case insensitive)"
            + "[MORE MOD_CODE]\n"
            + "Example: " + COMMAND_WORD + " CS2103T cs1010";

    public static final String MESSAGE_REMOVE_MODULE_SUCCESS = "These modules have been successfully removed:";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE =
            "These modules can not be removed because they do not exist in our database:";
    public static final String MESSAGE_MODULE_NOT_EXISTS =
            "These modules can not be removed because they do not exist in your staged module list:";
    public static final String MESSAGE_NOT_STUDENT = "Only a student user can execute this command";
    public static final String MESSAGE_NOT_LOGIN = "Please register or login";

    private final ArrayList<Module> toSearch;
    private Module toRemove;
    private String removeSuccessCode;
    private String notExistOwnCode;
    private String notExistDataCode;

    public RemoveModuleFromStudentStagedCommand(ArrayList<Module> moduleList) {
        requireNonNull(moduleList);
        toSearch = moduleList;
        toRemove = null;
        removeSuccessCode = MESSAGE_REMOVE_MODULE_SUCCESS;
        notExistOwnCode = MESSAGE_MODULE_NOT_EXISTS;
        notExistDataCode = MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE;
    }

    public Module getSearchedModule() {
        return toRemove;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentUser() == null) {
            throw new CommandException(MESSAGE_NOT_LOGIN);
        }

        if (!model.isStudent()) {
            throw new CommandException(MESSAGE_NOT_STUDENT);
        }

        for (Module module : toSearch) {
            Optional<Module> optionalModule = model.searchModuleInModuleList(module);

            if (optionalModule.isPresent()) {
                toRemove = optionalModule.get();
            } else {
                notExistDataCode = notExistDataCode.concat(" " + module.getCode().toString());
                continue;
            }

            if (!model.hasModuleStaged(toRemove)) {
                notExistOwnCode = notExistOwnCode.concat(" " + module.getCode().toString());
                continue;
            }

            model.removeModuleStaged(toRemove);
            removeSuccessCode = removeSuccessCode.concat(" " + module.getCode().toString());
        }

        return new CommandResult(notExistDataCode + '\n'
                + notExistOwnCode + '\n' + removeSuccessCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveModuleFromStudentStagedCommand // instanceof handles nulls
                && toSearch.equals(((RemoveModuleFromStudentStagedCommand) other).toSearch)); // state check
    }
}
