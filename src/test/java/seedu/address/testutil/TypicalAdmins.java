package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;

import seedu.address.model.user.Admin;
import seedu.address.model.user.Role;

/**
 * A list of typical admin.
 */
public class TypicalAdmins {

    public static final Admin ALICE = new AdminBuilder().withName("Alice Pauline")
            .withRole(Role.ADMIN).withPic("alice.img")
            .withSalary("3000")
            .withEmployedDate("01/01/2000").build();

    public static final Admin BRAD = new AdminBuilder().withName("Brad Bread")
            .withRole(Role.ADMIN).withPic("brad.img")
            .withSalary("3500")
            .withEmployedDate("01/01/2006").build();

    public static final Admin MORGAN = new AdminBuilder().withName("Morgan More")
            .withRole(Role.ADMIN).withPic("morgan.img")
            .withSalary("4000")
            .withEmployedDate("01/04/2010").build();

    public static final Admin AMY = new AdminBuilder().withName(VALID_NAME_AMY)
            .withRole(Role.ADMIN)
            .withPic(VALID_PATH_AMY)
            .withSalary(VALID_SALARY_AMY)
            .withEmployedDate(VALID_EMPLOY_DATE_AMY).build();
}
