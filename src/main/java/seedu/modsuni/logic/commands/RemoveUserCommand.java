package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;

/**
 * Removes a user identified using it's username from the Credential Store.
 */
public class RemoveUserCommand extends Command {

    public static final String COMMAND_WORD = "removeUser";

    public static final String MESSAGE_NOT_ADMIN = "Only an admin user can execute this command";

    public static final String MESSAGE_CANNNOT_DELETE_MASTER = "The master account cannot be deleted.";
    public static final String MESSAGE_CANNNOT_DELETE_YOURSELF = "You cannot delete yourself";
    public static final String MESSAGE_USER_NOT_FOUND = "The user %1$s does not exist";
    public static final String MESSAGE_DELETE_USER_SUCCESS = "The user %1$s has been deleted";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a user. "
            + "<USERNAME>\n"
            + "Example " + COMMAND_WORD + " "
            + "usernameToDelete\n";


    private final Username username;
    private final Password dummyPass = new Password("dummypass");

    public RemoveUserCommand(Username username) {
        requireNonNull(username);
        this.username = username;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isAdmin()) {
            throw new CommandException(MESSAGE_NOT_ADMIN);
        }

        if (username.toString().equals("master")) {
            throw new CommandException(MESSAGE_CANNNOT_DELETE_MASTER);
        }

        if (username.equals(model.getCurrentUser().getUsername())) {
            throw new CommandException(MESSAGE_CANNNOT_DELETE_YOURSELF);
        }

        if (model.hasCredential(new Credential(username, dummyPass))) {
            Credential toRemove = model.getCredential(username);
            model.removeCredential(toRemove);
            return new CommandResult(String.format(MESSAGE_DELETE_USER_SUCCESS, toRemove.getUsername().toString()));
        } else {
            throw new CommandException(String.format(MESSAGE_USER_NOT_FOUND, username.toString()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveUserCommand // instanceof handles nulls
                && username.equals(((RemoveUserCommand) other).username)); // state check
    }
}
