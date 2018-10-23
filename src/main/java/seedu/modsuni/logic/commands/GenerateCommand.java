package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.Generate;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.semester.SemesterList;
import seedu.modsuni.model.user.student.Student;

/**
 * Generates a schedule for the current student user.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = "generate";
    public static final String MESSAGE_SUCCESS = "Generate success!";
    public static final String MESSAGE_INVALID_ROLE = "Only students can generate a schedule, please login"
            + " with a student account and try again";
    public static final String MESSAGE_NO_MODULES = "Ensure that you have added module(s) that you "
            + "would like to take before running the generate command";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isStudent()) {
            throw new CommandException(MESSAGE_INVALID_ROLE);
        }

        Student currentStudent = (Student) model.getCurrentUser();

        if (!currentStudent.hasModuleToTake()) {
            throw new CommandException(MESSAGE_NO_MODULES);
        }

        Generate generate = new Generate(currentStudent);
        SemesterList semesterList = generate.getSchedule();
        System.out.println(semesterList.toString());

        return new CommandResult(MESSAGE_SUCCESS + "\n" + semesterList.toString());
    }
}
