package seedu.address.testutil;

import seedu.address.model.credential.Username;
import seedu.address.model.user.Admin;
import seedu.address.model.user.EmployDate;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.Salary;

/**
 * Builds a Admin user.
 */
public class AdminBuilder {

    public static final String DEFAULT_USERNAME = "Admin123";
    public static final String DEFAULT_NAME = "John Doe";
    public static final Role DEFAULT_ROLE = Role.ADMIN;
    public static final String DEFAULT_PIC = "test.img";
    public static final String DEFAULT_SALARY = "3000";
    public static final String DEFAULT_EMPLOYEDDATE = "01/01/2018";

    private Username username;
    private Name name;
    private Role role;
    private PathToProfilePic pic;
    private Salary salary;
    private EmployDate employedDate;

    public AdminBuilder() {
        username = new Username(DEFAULT_USERNAME);
        name = new Name(DEFAULT_NAME);
        role = DEFAULT_ROLE;
        pic = new PathToProfilePic(DEFAULT_PIC);
        salary = new Salary(DEFAULT_SALARY);
        employedDate = new EmployDate(DEFAULT_EMPLOYEDDATE);
    }

    /**
     * Initializes the AdminBuilder with the data of {@code adminToCopy}.
     */
    public AdminBuilder(Admin adminToCopy) {
        username = adminToCopy.getUsername();
        name = adminToCopy.getName();
        role = adminToCopy.getRole();
        pic = adminToCopy.getPathToProfilePic();
        salary = adminToCopy.getSalary();
        employedDate = adminToCopy.getEmploymentDate();
    }

    /**
     * Sets the {@code name} of the {@code Admin} that we are building.
     */
    public AdminBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Admin} that we are building.
     */
    public AdminBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    /**
     * Sets the {@code pic} of the {@code Admin} that we are building.
     */
    public AdminBuilder withPic(String path) {
        this.pic = new PathToProfilePic(path);
        return this;
    }

    /**
     * Sets the {@code salary} of the {@code Admin} that we are building.
     */
    public AdminBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code employedDate} of the {@code Admin} that we are building.
     */
    public AdminBuilder withEmployedDate(String date) {
        this.employedDate = new EmployDate(date);
        return this;
    }

    public Admin build() {
        return new Admin(username, name, role, pic, salary, employedDate);
    }

}
