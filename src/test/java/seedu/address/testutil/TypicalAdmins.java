package seedu.address.testutil;

import seedu.address.model.user.Admin;
import seedu.address.model.user.Role;

/**
 * A list of typical admin.
 */
public class TypicalAdmins {

    public static final Admin ALICE = new AdminBuilder().withName("Alice Pauline")
            .withRole(Role.ADMIN).withPic("alice@example.com")
            .withSalary("3000")
            .withEmployedDate("01/01/2000").build();

    public static final Admin BRAD = new AdminBuilder().withName("Brad Bread")
            .withRole(Role.ADMIN).withPic("brad@example.com")
            .withSalary("3500")
            .withEmployedDate("01/01/2006").build();

    public static final Admin MORGAN = new AdminBuilder().withName("Morgan More")
            .withRole(Role.ADMIN).withPic("morgan@example.com")
            .withSalary("4000")
            .withEmployedDate("01/04/2010").build();
}
