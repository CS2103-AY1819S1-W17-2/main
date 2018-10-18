package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SAVE_PATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.NoSuchPaddingException;

import seedu.address.commons.exceptions.CorruptedFileException;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.InvalidPasswordException;
import seedu.address.commons.util.DataSecurityUtil;
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
            + PREFIX_SAVE_PATH + "userconfig";

    public static final String MESSAGE_SUCCESS = "Current user configuration has be saved!";

    public static final String MESSAGE_ERROR = "Unable to save. Please ensure that you are registered or logged in.";

    private final Path savePath;

    public SaveCommand(Path savePath) {
        this.savePath = savePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        User currentUser = model.getCurrentUser();

        if (currentUser == null) {
            throw new CommandException(MESSAGE_ERROR);
        }

        model.saveUserFile(currentUser, savePath);
        Path path = Paths.get("data/userdata.xml");
        try {
            DataSecurityUtil.decryptFile(path.toFile(), "1qazXSW@");
            Optional<User> u = model.readUserFile(path);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataConversionException e) {
            e.printStackTrace();
        } catch (CorruptedFileException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
