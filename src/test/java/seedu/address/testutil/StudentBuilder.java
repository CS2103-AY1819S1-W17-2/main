package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.credential.Username;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.student.EnrollmentDate;
import seedu.address.model.user.student.Student;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Max Verstappen";
    public static final String DEFAULT_USERNAME = "max33";
    public static final String DEFAULT_PROFILE_PIC_FILEPATH = "redbull.img";
    public static final Role DEFAULT_ROLE = Role.STUDENT;
    public static final String DEFAULT_ENROLLMENT_DATE = "15/03/2015";
    public static final List<String> DEFAULT_MAJOR = Arrays.asList("CS", "DA");
    public static final List<String> DEFAULT_MINOR = Arrays.asList("MA", "BA");

    private Name name;
    private Username username;
    private final Role role;
    private PathToProfilePic profilePicFilePath;
    private EnrollmentDate enrollmentDate;
    private List<String> major;
    private List<String> minor;

    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        username = new Username(DEFAULT_USERNAME);
        role = DEFAULT_ROLE;
        profilePicFilePath = new PathToProfilePic(DEFAULT_PROFILE_PIC_FILEPATH);
        enrollmentDate = new EnrollmentDate(DEFAULT_ENROLLMENT_DATE);
        major = DEFAULT_MAJOR;
        minor = DEFAULT_MINOR;
    }

    public StudentBuilder(Student student) {
        name = student.getName();
        username = student.getUsername();
        role = student.getRole();
        profilePicFilePath = student.getPathToProfilePic();
        enrollmentDate = student.getEnrollmentDate();
        major = student.getMajor();
        minor = student.getMinor();
    }

    /**
     * Sets the {@code name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code username} of the {@code Student} that we are building.
     */
    public StudentBuilder withUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    /**
     * Sets the {@code profilePicFilePath} of the {@code Student} that we are
     * building.
     */
    public StudentBuilder withProfilePicFilePath(String pathToPic) {
        this.profilePicFilePath = new PathToProfilePic(pathToPic);
        return this;
    }

    /**
     * Sets the {@code enrollmentDate} of the {@code Student} that we are
     * building.
     */
    public StudentBuilder withEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = new EnrollmentDate(enrollmentDate);
        return this;
    }

    /**
     * Sets the {@code majors} of the {@code Student} that we are building.
     */
    public StudentBuilder withMajor(List<String> inputMajor) {
        this.major = inputMajor;
        return this;
    }

    /**
     * Sets the {@code minors} of the {@code Student} that we are building.
     */
    public StudentBuilder withMinor(List<String> inputMinor) {
        this.minor = inputMinor;
        return this;
    }

    /**
     * Builds the actual Student with its respective attributes.
     */
    public Student build() {
        return new Student(username, name, role, profilePicFilePath,
            enrollmentDate, major, minor);
    }
}
