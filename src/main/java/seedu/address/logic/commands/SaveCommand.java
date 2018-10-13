package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SAVE_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.nio.file.Path;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.user.User;

/**
 * Saves the current user.
 */
public class SaveCommand extends Command {

    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves current user to a specific path. "
            + "Parameters: "
            + PREFIX_SAVE_PATH + "FILEPATH\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "userconfig";

    public static final String MESSAGE_SUCCESS = "Current user configuration has be saved!";

    public static final String MESSAGE_ERROR = "Unable to save. Please read the user guide for more information.";

    private final Path savePath;

    public SaveCommand(Path savePath) {
        this.savePath = savePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
         User currentUser = model.getCurrentUser();

        if(currentUser == null){
            throw new CommandException(MESSAGE_ERROR);
        }

        model.saveUserFile(currentUser, savePath);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
