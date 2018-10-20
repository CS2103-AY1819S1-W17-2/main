package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;

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

        public EditStudentDescriptor() {}

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
            return (majors != null) ?
                Optional.of(Collections.unmodifiableList(majors)) : Optional.empty();
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
            return (minors != null) ?
                Optional.of(Collections.unmodifiableList(minors)) : Optional.empty();
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
