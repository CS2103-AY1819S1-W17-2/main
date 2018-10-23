package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.module.Module;

/**
 * Adds several modules to the student's taken module list.
 * Keyword matching is case insensitive.
 */
public class AddModuleToStudentTakenCommand extends Command {

    public static final String COMMAND_WORD = "addModuleT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds on one module to your profile. "
            + "Parameters: "
            + "MOD_CODE(case insensitive)"
            + "[MORE MOD_CODE]\n"
            + "Example: " + COMMAND_WORD + " "
            + "CS2103T cs1010";

    public static final String MESSAGE_SUCCESS = "These modules have been successfully added:";
    public static final String MESSAGE_DUPLICATE_MODULE =
            "These modules can not be added because they already exist in your taken module list:";
    public static final String MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE =
            "These modules can not be added because they do not exist in our database:";
    public static final String MESSAGE_NOT_STUDENT = "Only a student user can execute this command";
    public static final String MESSAGE_NOT_LOGIN = "Please register or login";

    private final ArrayList<Module> toSearch;
    private Module toAdd;
    private String addSuccessCode;
    private String duplicateCode;
    private String notExistCode;

    /**
     * Creates an AddModuleToStudentTakenCommand to add the specified {@code module}
     */
    public AddModuleToStudentTakenCommand(ArrayList<Module> moduleList) {
        requireNonNull(moduleList);
        toSearch = moduleList;
        toAdd = null;
        addSuccessCode = MESSAGE_SUCCESS;
        duplicateCode = MESSAGE_DUPLICATE_MODULE;
        notExistCode = MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE;
    }

    public Module getSearchedModule() {
        return toAdd;
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
                toAdd = optionalModule.get();
            } else {
                notExistCode = notExistCode.concat(" " + module.getCode().toString());
                continue;
            }

            if (model.hasModuleTaken(toAdd)) {
                duplicateCode = duplicateCode.concat(" " + module.getCode().toString());
                continue;
            }

            model.addModuleTaken(toAdd);
            addSuccessCode = addSuccessCode.concat(" " + module.getCode().toString());
        }

        return new CommandResult(notExistCode + '\n'
                + duplicateCode + '\n' + addSuccessCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleToStudentTakenCommand // instanceof handles nulls
                && toSearch.equals(((AddModuleToStudentTakenCommand) other).toSearch));
    }
}
