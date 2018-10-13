package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SAVE_PATH;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.credential.Username;
import seedu.address.model.module.Module;
import seedu.address.model.user.Admin;
import seedu.address.model.user.EmployDate;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.Salary;
import seedu.address.model.user.User;
import seedu.address.model.user.student.EnrollmentDate;
import seedu.address.model.user.student.Student;

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

//        if(currentUser == null){
//            throw new CommandException(MESSAGE_ERROR);
//        }

        // TEST DATA
        List<String> major = Arrays.asList("Computer Science");
        List<String> minor = Arrays.asList("Math", "Business Management");

        Module module1 = new Module("CS1231");
        Module module2 = new Module("MA1521");
        List<Module> takenMod = Arrays.asList(module1, module2);
//        takenMod.add(module1);
//        takenMod.add(module2);
        System.out.println("SaveCommand: " + takenMod);

//        currentUser = new Admin(new Username("peter"), new Name("name"), Role.ADMIN, new PathToProfilePic("123.img"), new Salary("123"), new EmployDate("11/11/1111"));
        currentUser = new Student(new Username("peter"), new Name("name"),
                Role.STUDENT, new PathToProfilePic("123.img"), new EnrollmentDate("11/11/1111"),
                major, minor, takenMod);

        model.saveUserFile(currentUser, savePath);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
