package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Generate;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModuleList;
import seedu.address.model.Semester.Semester;
import seedu.address.model.Semester.SemesterList;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.user.student.Student;

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
