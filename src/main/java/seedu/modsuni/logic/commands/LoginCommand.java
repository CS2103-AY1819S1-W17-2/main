package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modsuni.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_USERDATA;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.NoSuchPaddingException;

import seedu.modsuni.commons.exceptions.CorruptedFileException;
import seedu.modsuni.commons.exceptions.DataConversionException;
import seedu.modsuni.commons.exceptions.InvalidPasswordException;
import seedu.modsuni.commons.util.DataSecurityUtil;
import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.user.User;

/**
 * Command to allow Users to login and access user specific data.
 */
public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Login to the "
        + "account with the corresponding credentials provided. \n"
        + "Parameters: "
        + PREFIX_USERNAME + "USERNAME "
        + PREFIX_PASSWORD + "PASSWORD "
        + PREFIX_USERDATA + "PATH_TO_XML";

    public static final String MESSAGE_SUCCESS = "Login Successfully! Welcome "
        + "%1$s";
    public static final String MESSAGE_LOGIN_FAILURE = "Incorrect "
        + "Password/Invalid User Account";

    public static final String MESSAGE_UNABLE_TO_READ_FILE = "Unable to read "
        + "file/Invalid file provided. Please check file.";

    public static final String MESSAGE_ALREADY_LOGGED_IN = "Already logged in!";

    private final Credential toLogin;
    private final Path pathToSaveFile;

    public LoginCommand(Credential credential, Path pathToSaveFile) {
        requireAllNonNull(credential, pathToSaveFile);
        this.toLogin = credential;
        this.pathToSaveFile = pathToSaveFile;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);


        if (model.getCurrentUser() != null) {
            throw new CommandException(MESSAGE_ALREADY_LOGGED_IN);
        }

        if (!model.isVerifiedCredential(toLogin)) {
            throw new CommandException(MESSAGE_LOGIN_FAILURE);
        }

        User toSetCurrentUser;
        try {

            DataSecurityUtil.decryptFile(pathToSaveFile.toFile(),
                toLogin.getPassword().toString());

            Optional<User> userFromFile = model.readUserFile(pathToSaveFile);
            if (!userFromFile.isPresent()) {
                throw new CommandException(MESSAGE_UNABLE_TO_READ_FILE);
            }
            toSetCurrentUser = userFromFile.get();

            DataSecurityUtil.encryptFile(pathToSaveFile.toFile(), toLogin.getPassword().getValue());
        } catch (DataConversionException | IOException | CorruptedFileException
            | NoSuchAlgorithmException | InvalidKeyException | InvalidPasswordException
            | NoSuchPaddingException e) {
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
