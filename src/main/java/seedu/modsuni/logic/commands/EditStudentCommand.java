package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.user.student.Student;

/**
 * Command to allow students to edit their profiles.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit current"
        + " student account with the given parameters.\n"
        + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "Edit Successfully!";

    private final Student toEdit;

    public EditStudentCommand(Student student) {
        requireNonNull(student);
        this.toEdit = student;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        //TODO simply override currentUser
        return null;
    }
}
