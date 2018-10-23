package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_DATE;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PATH_TO_PIC;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.user.Admin;

/**
 * Adds a new admin account.
 */
public class AddAdminCommand extends Command {
    public static final String COMMAND_WORD = "addAdmin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new admin account. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD "
            + PREFIX_NAME + "NAME "
            + PREFIX_PATH_TO_PIC + "PATH_TO_PIC "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_EMPLOYMENT_DATE + "EMPLOYMENTDATE\n"
            + "Example " + COMMAND_WORD + " "
            + PREFIX_USERNAME + "myUsername "
            + PREFIX_PASSWORD + "myPassword "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PATH_TO_PIC + "path "
            + PREFIX_SALARY + "3000 "
            + PREFIX_EMPLOYMENT_DATE + "30/09/2018\n";

    public static final String MESSAGE_SUCCESS = "New admin added: %1$s";
    public static final String MESSAGE_DUPLICATE_ADMIN = "This admin username already exists";
    public static final String MESSAGE_DUPLICATE_USERNAME = "This username already exists in the database";
    public static final String MESSAGE_NOT_ADMIN = "Only an admin user can execute this command";



    private final Admin toAdd;
    private final Credential credential;

    public AddAdminCommand(Admin admin, Credential credential) {
        requireNonNull(admin);
        requireNonNull(credential);
        toAdd = admin;
        this.credential = credential;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isAdmin()) {
            throw new CommandException(MESSAGE_NOT_ADMIN);
        }
        if (model.hasCredential(credential)) {
            throw new CommandException(MESSAGE_DUPLICATE_USERNAME);
        }

        model.addCredential(credential);

        /*TODO: Prevent Duplicate Admin
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ADMIN);
        }
        */

        model.addAdmin(toAdd);

        /*TODO: undo redo
        model.commitAddressBook();
        */

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAdminCommand // instanceof handles nulls
                && toAdd.equals(((AddAdminCommand) other).toAdd));
    }
}
