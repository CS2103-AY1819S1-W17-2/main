package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * Saves the current user.
 */
public class SaveCommand extends Command {

    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_SUCCESS = "Current user configuration has be saved!";

    public static final String MESSAGE_ERROR = "Unable to save. Please read the user guide for more information.";


    private final User toSaveUser;

    public SaveCommand(User currentUser) {
        requireNonNull(currentUser);
        toSaveUser = currentUser;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if(model.getCurrentUser() == null){
            throw new CommandException(MESSAGE_ERROR);
        }

        model.saveUserFile(toSaveUser);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
