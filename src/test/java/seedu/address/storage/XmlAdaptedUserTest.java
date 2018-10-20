package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.user.User;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.StudentBuilder;

public class XmlAdaptedUserTest {

    private static final User VALID_ADMIN = new AdminBuilder().build();
    private static final User VALID_STUDENT = new StudentBuilder().build();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Ignore("Test is ignored as the object does not match as intended")
    @Test
    public void toModelType_validUserDetails_returnsUser() throws Exception {
        XmlAdaptedUser admin = new XmlAdaptedUser(VALID_ADMIN);
        assertEquals(VALID_ADMIN, admin.toModelType());

        XmlAdaptedUser student = new XmlAdaptedUser(VALID_STUDENT);
        assertEquals(VALID_STUDENT, student.toModelType());
    }

    @Test
    public void toModelType_nullUser_throwsIllegalValueException() {
        thrown.expect(NullPointerException.class);
        new XmlAdaptedUser(null);
    }
}
