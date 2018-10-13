package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * An User that is serializable to XML format
 */
public class XmlAdapterUser {

    private static final String MISSING_FIELD_MESSAGE_FORMAT = "User's "
            + "%s field is missing!";

    // Must have for all users
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String role;
    @XmlElement(required = true)
    private String pathToProfilePic;

    // Attributes for Admin
    @XmlElement
    private String salary;
    @XmlElement
    private String employmentDate;

    // Attributes for Student
    @XmlElement
    private String enrollmentDate;
    @XmlElement
    private String major;
    @XmlElement
    private String minor;
    @XmlElement
    private List<XmlAdaptedModule> modulesTaken = new ArrayList<>();

    /**
     * Creates an empty XmlAdapterUser.
     * This empty constructor is required for marshalling.
     */
    public XmlAdapterUser() {}

    /**
     * Constructs an {@code XmlAdapterUser} with the given user details.
     */
    public XmlAdapterUser(Username username, Name name, Role role, PathToProfilePic pathToProfilePic,
                               Salary salary, EmployDate employmentDate) {
        this.username = username.toString();
        this.name = name.toString();
        this.role = role.toString();
        this.pathToProfilePic = pathToProfilePic.toString();
        this.salary = salary.toString();
        this.employmentDate = employmentDate.toString();
    }

    /**
     * Constructs an {@code XmlAdapterUser} with the given user details.
     */
    public XmlAdapterUser(Username username, Name name, Role role, PathToProfilePic pathToProfilePic, EnrollmentDate enrollmentDate,
                               List<String> major, List<String> minor, List<Module> modulesTaken) {
        this.username = username.toString();
        this.name = name.toString();
        this.role = role.toString();
        this.pathToProfilePic = pathToProfilePic.toString();
        this.enrollmentDate = enrollmentDate.toString();
        this.major = major.toString();
        this.minor = minor.toString();
        this.modulesTaken.addAll(modulesTaken.stream().map(XmlAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts a given User into this class for JAXB use.
     *
     * @param user future changes to this will not affect the created XmlAdapterUser
     */
    public XmlAdapterUser(User user) {
        requireNonNull(user);
        this.username = user.getUsername().toString();
        this.name = user.getName().toString();
        this.role = user.getRole().toString();
        this.pathToProfilePic = user.getPathToProfilePic().toString();

        if(user.getRole() == Role.ADMIN){
            Admin admin = (Admin) user;
            this.salary = admin.getSalary().toString();
            this.employmentDate = admin.getEmploymentDate().toString();
        }

        if(user.getRole() == Role.STUDENT){
            Student student = (Student) user;
            this.enrollmentDate = student.getEnrollmentDate().toString();
            this.major = student.getMajor().toString();
            this.minor = student.getMinor().toString();
            this.modulesTaken.addAll(student.getModulesTaken().stream().map(XmlAdaptedModule::new).collect(Collectors.toList()));
        }
    }

    /**
     * Converts this User into the model's {@code User} object.
     *
     * @throws IllegalValueException if there were any data constraints violated
     */
    public User toModelType() throws IllegalValueException {
        User user = null;
        // Username
        if (username == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Username"));
        }
        if (!Username.isValidUsername(username)) {
            throw new IllegalValueException(Username.MESSAGE_USERNAME_CONSTRAINTS);
        }

        // Name
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }

        // Role
        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "role"));
        }

        // Path to profilepic
        if (pathToProfilePic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "pathToProfilePic"));
        }
        if (!PathToProfilePic.isValidPath(pathToProfilePic)) {
            throw new IllegalValueException(PathToProfilePic.MESSAGE_PATH_CONSTRAINTS);
        }

        if (role.equals("ADMIN")){
            // Salary
            if (salary == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "salary"));
            }
            if (!Salary.isValidSalary(salary)) {
                throw new IllegalValueException(Salary.MESSAGE_SALARY_CONSTRAINTS);
            }

            // employment date
            if (employmentDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "employment"));
            }
            if (!EmployDate.isValidEmployDate(employmentDate)) {
                throw new IllegalValueException(EmployDate.MESSAGE_DATE_CONSTRAINTS);
            }
            user = new Admin(new Username(username), new Name(name), Role.ADMIN,
                    new PathToProfilePic(pathToProfilePic), new Salary(salary),
                    new EmployDate(employmentDate));
        }

        if (role.equals("STUDENT")){
            if (enrollmentDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "enrollment"));
            }
            if (!EnrollmentDate.isValidEmployDate(enrollmentDate)) {
                throw new IllegalValueException(EnrollmentDate.MESSAGE_DATE_CONSTRAINTS);
            }
            if (major == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "major"));
            }
            if (minor == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "minor"));
            }
            if (modulesTaken == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "modules taken"));
            }

            List<String> majorConverted = Arrays.asList(major.substring(1, major.length() - 1).split(", "));
            List<String> minorConverted = Arrays.asList(minor.substring(1, minor.length() - 1).split(", "));

            List<Module> modulesConverted = new ArrayList<>();
            for (XmlAdaptedModule m : modulesTaken) {
                Module module = m.toModelType();
                modulesConverted.add(module);
            }


            user = new Student(new Username(username), new Name(name), Role.STUDENT,
                    new PathToProfilePic(pathToProfilePic), new EnrollmentDate(enrollmentDate),
                    majorConverted, minorConverted, modulesConverted);
        }
        return user;
    }
}
