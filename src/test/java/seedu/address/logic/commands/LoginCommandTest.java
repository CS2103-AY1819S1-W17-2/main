package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.credential.Credential;

public class LoginCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructorNullCredentialThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new LoginCommand(null);
    }

    @Test
    public void equals() {
        Credential maxCredential = CREDENTIAL_STUDENT_MAX;
        Credential sebCredential = CREDENTIAL_STUDENT_SEB;

        LoginCommand loginMaxCommand =
            new LoginCommand(maxCredential);
        LoginCommand loginSebCommand =
            new LoginCommand(sebCredential);

        // same object -> returns true
        assertTrue(loginMaxCommand.equals(loginMaxCommand));

        // same values -> returns true
        LoginCommand loginMaxCommandCopy =
            new LoginCommand(maxCredential);
        assertTrue(loginMaxCommand.equals(loginMaxCommandCopy));

        // different types -> returns false
        assertFalse(loginMaxCommand.equals(1));

        // null -> returns false
        assertFalse(loginMaxCommand.equals(null));

        // different person -> returns false
        assertFalse(loginMaxCommand.equals(loginSebCommand));
    }
}
