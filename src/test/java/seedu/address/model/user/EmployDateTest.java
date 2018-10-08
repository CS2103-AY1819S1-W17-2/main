package seedu.address.model.user;

import org.junit.Test;
import seedu.address.testutil.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new EmployDate(null));
    }

    @Test
    public void constructor_invalidEmployDate_throwsIllegalArgumentException() {
        String invalidEmployDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new EmployDate(invalidEmployDate));
    }

    @Test
    public void isValidEmployDate() {
        // null employ date
        Assert.assertThrows(NullPointerException.class, () -> EmployDate.isValidEmployDate(null));

        // invalid employ date
        assertFalse(EmployDate.isValidEmployDate("")); // empty string
        assertFalse(EmployDate.isValidEmployDate(" ")); // spaces only
        assertFalse(EmployDate.isValidEmployDate("^")); // only non-alphanumeric characters
        assertFalse(EmployDate.isValidEmployDate("peter*")); // contains non-alphanumeric characters
        assertFalse(EmployDate.isValidEmployDate("12082014")); //no slashes

        // valid name
        assertTrue(EmployDate.isValidEmployDate("01/01/2018")); // alphabets only
    }

}
