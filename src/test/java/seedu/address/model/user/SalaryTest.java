package seedu.address.model.user;

import org.junit.Test;
import seedu.address.testutil.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "OneHundred";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        Assert.assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("^abcABC")); // only non-digit characters
        assertFalse(Salary.isValidSalary("3000A")); // contains non-digit characters

        // valid salary
        assertTrue(Salary.isValidSalary("12345")); // digits only
    }
}
