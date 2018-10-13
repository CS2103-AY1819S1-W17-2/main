package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
 * An Immutable AddressBook that is serializable to XML format
 */
@XmlRootElement(name = "user")
public class XmlSerializableUser {

    // Must have for all users
    @XmlElement(required = true)
    private Username username;
    @XmlElement(required = true)
    private Name name;
    @XmlElement(required = true)
    private Role role;
    @XmlElement(required = true)
    private PathToProfilePic pathToProfilePic;

    // Attributes for Admin
    @XmlElement
    private Salary salary;
    @XmlElement
    private EmployDate employmentDate;

    // Attributes for Student
    @XmlElement
    private EnrollmentDate enrollmentDate;
    @XmlElement
    private List<String> major;
    @XmlElement
    private List<String> minor;
    @XmlElement
    private List<Module> modulesTaken;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableUser() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given user details.
     */
    public XmlSerializableUser(Username username, Name name, Role role, PathToProfilePic pathToProfilePic,
                          Salary salary, EmployDate employmentDate) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.pathToProfilePic = pathToProfilePic;
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given user details.
     */
    public XmlSerializableUser(Username username, Name name, Role role, PathToProfilePic pathToProfilePic, EnrollmentDate enrollmentDate,
                          List<String> major, List<String> minor, List<Module> modulesTaken) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.pathToProfilePic = pathToProfilePic;
        this.enrollmentDate = enrollmentDate;
        this.major = major;
        this.minor = minor;
        this.modulesTaken = modulesTaken;
    }

    /**
     * Converts a given User into this class for JAXB use.
     *
     * @param user future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlSerializableUser(User user) {
        requireNonNull(user);
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole();
        this.pathToProfilePic = user.getPathToProfilePic();

        if(user.getRole() == Role.ADMIN){
            Admin admin = (Admin) user;
            this.salary = admin.getSalary();
            this.employmentDate = admin.getEmploymentDate();
        }

        if(user.getRole() == Role.STUDENT){
            Student student = (Student) user;
            this.enrollmentDate = student.getEnrollmentDate();
            this.major = student.getMajor();
            this.minor = student.getMinor();
            this.modulesTaken = student.getModulesTaken();
        }
    }

    /**
     * Converts this addressbook into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson}.
     */
    public User toModelType() throws IllegalValueException {
//        if(this.role == Role.ADMIN) {
//
//        }
//        AddressBook addressBook = new AddressBook();
//        for (XmlAdaptedPerson p : persons) {
//            Person person = p.toModelType();
//            if (addressBook.hasPerson(person)) {
//                throw new IllegalValueException(MESSAtGE_DUPLICATE_PERSON);
//            }
//            addressBook.addPerson(person);
//        }
//        return user;
        return null;
    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        if (!(other instanceof XmlSerializableAddressBook)) {
//            return false;
//        }
//        return persons.equals(((XmlSerializableAddressBook) other).persons);
//    }
}
