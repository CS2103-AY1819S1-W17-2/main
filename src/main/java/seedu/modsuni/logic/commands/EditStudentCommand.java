package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modsuni.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PATH_TO_PIC;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_STUDENT_ENROLLMENT_DATE;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_STUDENT_MAJOR;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_STUDENT_MINOR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.modsuni.commons.util.CollectionUtil;
import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.student.EnrollmentDate;
import seedu.modsuni.model.user.student.Student;

/**
 * Command to allow students to edit their profiles.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit current"
        + " student account with the given parameters.\n"
        + "Parameters:\n"
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PATH_TO_PIC + "PATH.img] "
        + "[" + PREFIX_STUDENT_ENROLLMENT_DATE + "DD/MM/YYYY] "
        + "[" + PREFIX_STUDENT_MAJOR + "MAJOR]... "
        + "[" + PREFIX_STUDENT_MINOR + "MINOR]...\n"
        + "Example: " + COMMAND_WORD
        + PREFIX_NAME + "Max Emilian Verstappen "
        + PREFIX_STUDENT_ENROLLMENT_DATE + "11/03/2015";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edit "
        + "Successfully!\n%1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to "
        + "edit must be provided.";

    public static final String MESSAGE_NOT_LOGGED_IN = "You need to be logged"
        + " in!";

    private final EditStudentDescriptor editStudentDescriptor;

    public EditStudentCommand(EditStudentDescriptor editStudentDescriptor) {
        requireAllNonNull(editStudentDescriptor);
        this.editStudentDescriptor = editStudentDescriptor;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentUser() == null) {
            throw new CommandException(MESSAGE_NOT_LOGGED_IN);
        }

        Student editedStudent =
            createEditedStudent((Student) model.getCurrentUser(),
                editStudentDescriptor);

        model.setCurrentUser(editedStudent);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS,
            editedStudent.toString()));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code
     * studentToEdit }
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit,
                                               EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName =
            editStudentDescriptor.getName().orElse(studentToEdit.getName());
        PathToProfilePic updatedPic =
            editStudentDescriptor.getProfilePic().orElse(studentToEdit.getPathToProfilePic());
        EnrollmentDate updatedEnrollmentDate =
            editStudentDescriptor.getEnrollmentDate().orElse(studentToEdit.getEnrollmentDate());
        List<String> updatedMajor =
            editStudentDescriptor.getMajors().orElse(studentToEdit.getMajor());
        List<String> updatedMinor =
            editStudentDescriptor.getMinors().orElse(studentToEdit.getMinor());

        return new Student(
            studentToEdit.getUsername(),
            updatedName,
            studentToEdit.getRole(),
            updatedPic,
            updatedEnrollmentDate,
            updatedMajor,
            updatedMinor);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value
     * will replace the corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private PathToProfilePic profilePic;
        private EnrollmentDate enrollmentDate;
        private List<String> majors;
        private List<String> minors;

        public EditStudentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPathToProfilePic(toCopy.profilePic);
            setEnrollmentDate(toCopy.enrollmentDate);
            setMajors(toCopy.majors);
            setMinors(toCopy.minors);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, profilePic,
                enrollmentDate, majors,
                minors);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPathToProfilePic(PathToProfilePic phone) {
            this.profilePic = phone;
        }

        public Optional<PathToProfilePic> getProfilePic() {
            return Optional.ofNullable(profilePic);
        }

        public void setEnrollmentDate(EnrollmentDate enrollmentDate) {
            this.enrollmentDate = enrollmentDate;
        }

        public Optional<EnrollmentDate> getEnrollmentDate() {
            return Optional.ofNullable(enrollmentDate);
        }

        /**
         * Sets {@code majors} to this object's {@code majors}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setMajors(List<String> majors) {
            this.majors = (majors != null) ? new ArrayList<>(majors) : null;
        }

        /**
         * Returns an unmodifiable major set, which throws {@code
         * UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code major} is null.
         */
        public Optional<List<String>> getMajors() {
            return (majors != null)
                ? Optional.of(Collections.unmodifiableList(majors)) : Optional.empty();
        }

        /**
         * Sets {@code majors} to this object's {@code majors}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setMinors(List<String> minors) {
            this.minors = (minors != null) ? new ArrayList<>(minors) : null;
        }

        /**
         * Returns an unmodifiable major set, which throws {@code
         * UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code major} is null.
         */
        public Optional<List<String>> getMinors() {
            return (minors != null)
                ? Optional.of(Collections.unmodifiableList(minors)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                && getProfilePic().equals(e.getProfilePic())
                && getEnrollmentDate().equals(e.getEnrollmentDate())
                && getMajors().equals(e.getMajors())
                && getMinors().equals(e.getMinors());
        }
    }
}
