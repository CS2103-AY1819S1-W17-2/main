package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import seedu.modsuni.commons.exceptions.DataConversionException;
import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.Role;
import seedu.modsuni.model.user.User;
import seedu.modsuni.model.user.student.EnrollmentDate;
import seedu.modsuni.model.user.student.Student;

/**
 * Command to allow Users to login and access user specific data.
 */
public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Login to the "
        + "account with the corresponding credentials provided. \n"
        + "Parameters: "
        + PREFIX_USERNAME + "USERNAME "
        + PREFIX_PASSWORD + "PASSWORD ";

    public static final String MESSAGE_SUCCESS = "Login Successfully! Welcome "
        + "%1$s";
    public static final String MESSAGE_LOGIN_FAILURE = "Incorrect "
        + "Password/Invalid User Account";

    public static final String MESSAGE_UNABLE_TO_READ_FILE = "Unable to read "
        + "file/Invalid file provided. Please check file.";

    private final Credential toLogin;
    private final Path pathToSaveFile;

    public LoginCommand(Credential credential, Path pathToSaveFile) {
        requireNonNull(credential);
        this.toLogin = credential;
        this.pathToSaveFile = pathToSaveFile;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isVerifiedCredential(toLogin)) {
            throw new CommandException(MESSAGE_LOGIN_FAILURE);
        }

        User toSetCurrentUser;
        try {
            Optional<User> userFromFile = model.readUserFile(pathToSaveFile);
            if (!userFromFile.isPresent()) {
                throw new CommandException(MESSAGE_UNABLE_TO_READ_FILE);
            }
            toSetCurrentUser = userFromFile.get();
        } catch (DataConversionException | IOException e) {
            throw new CommandException(MESSAGE_UNABLE_TO_READ_FILE);
        }

        model.setCurrentUser(toSetCurrentUser);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
            toSetCurrentUser.getUsername()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LoginCommand // instanceof handles nulls
            && toLogin.equals(((LoginCommand) other).toLogin));
    }
}
