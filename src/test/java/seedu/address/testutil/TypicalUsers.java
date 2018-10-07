package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.user.student.Student;
import seedu.address.model.user.User;

/**
 * A utility class containing a list of {@code User} objects to be used in
 * tests.
 */
public class TypicalUsers {

    public static final Student STUDENT_MAX = new StudentBuilder().build();
    public static final Student STUDENT_SEB = new StudentBuilder()
        .withName("Sebestian Vettel")
        .withUsername("vettel5")
        .withProfilePicFilePath("ferrari.img")
        .withEnrollmentDate("17/06/2007")
        .withMajor(Arrays.asList("CS", "BA"))
        .withMinor(Arrays.asList("IS", "MA")).build();
    // private static final User ADMIN_TOTO = new AdminBuilder().build();

    private TypicalUsers() {
    }

    /**
     * Returns a {@code List<User>} with all the typical users.
     */
    public List<User> getTypicalUsers() {
        return new ArrayList<>(Arrays.asList(STUDENT_MAX, STUDENT_SEB));
    }
}
