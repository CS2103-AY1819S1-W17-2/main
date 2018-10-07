package seedu.address.model.user.student;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.credential.Username;
import seedu.address.model.module.Module;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;

/**
 * Represents a Student User.
 *
 */
public class Student extends User {
    protected EnrollmentDate enrollmentDate;
    protected List<String> major;
    protected List<String> minor;
    protected List<Module> modulesTaken;
    /**
     * Constructor method of User
     *
     * @param username         The username of the user.
     * @param name             The name of the user.
     * @param role             The role of the user.
     * @param pathToProfilePic The path to the image to be used as profile picture.
     */

    public Student(Username username, Name name, Role role,
                   PathToProfilePic pathToProfilePic, EnrollmentDate enrollmentDate,
                   List<String> major, List<String> minor) {
        super(username, name, role, pathToProfilePic);
        this.enrollmentDate = enrollmentDate;
        this.major = major;
        this.minor = minor;
        this.modulesTaken = new ArrayList<>();
    }

    @Override
    public void updatePassword(String newPassword) {
        //TODO
    }

    @Override
    public void updateName(String newName) {
        //TODO
    }

    @Override
    public void updateProfilePic(String newPath) {
        //TODO
    }

    public void updateEnrollmentDate(EnrollmentDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void updateMajors(List<String> newMajors) {
        this.major = newMajors;
    }

    public void updateMinors(List<String> newMinors) {
        this.minor = newMinors;
    }

    /**
     * Returns true if both student's profile contains the module and false otherwise.
     */
    public boolean hasModulesTaken(Module module) {
        for (Module existModule: modulesTaken) {
            if (existModule.equals(module)) {
                return true;
            }
        }
        return false;
    }

    public void removeModulesTaken(Module module) {
        modulesTaken.remove(module);
    }

    public void addModulesTaken(Module module) {
        modulesTaken.add(module);
    }

    public List<Module> getModulesTaken() {
        return modulesTaken;
    }

    public EnrollmentDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<String> getMajor() {
        return major;
    }

    public List<String> getMinor() {
        return minor;
    }
}
