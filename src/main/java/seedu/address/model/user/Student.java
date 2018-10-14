package seedu.address.model.user;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.credential.Username;
import seedu.address.model.module.Module;

/**
 * Represents a Student User.
 *
 */
public class Student extends User {
    protected String enrollmentDate;
    protected List<String> major;
    protected List<String> minor;
    protected List<Module> modules;
    /**
     * Constructor method of User
     *
     * @param username         The username of the user.
     * @param name             The name of the user.
     * @param role             The role of the user.
     * @param pathToProfilePic The path to the image to be used as profile picture.
     */

    public Student(Username username, Name name, Role role,
                   PathToProfilePic pathToProfilePic, String enrollmentDate,
                   List<String> major, List<String> minor) {
        super(username, name, role, pathToProfilePic);
        this.enrollmentDate = enrollmentDate;
        this.major = major;
        this.minor = minor;
        this.modules = new ArrayList<>();
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

    public void updateEnrollmentDate(String enrollmentDate) {
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
    public boolean hasModule(Module module) {
        for (Module existModule: modules) {
            if (existModule.equals(module)) {
                return true;
            }
        }
        return false;
    }

    public void removeModule(Module module) {
        modules.remove(module);
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }


}
