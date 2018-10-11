package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedCredential.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;

import org.junit.Ignore;
import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;
import seedu.address.testutil.Assert;

public class XmlAdaptedCredentialTest {
    private static final String INVALID_USERNAME = "R@chel";
    private static final String INVALID_PASSWORD = "qwerty123";

    private static final String VALID_USERNAME =
        CREDENTIAL_STUDENT_SEB.getUsername().toString();
    private static final String VALID_PASSWORD =
        CREDENTIAL_STUDENT_SEB.getPassword().toString();
    private static final String VALID_KEY =
        CREDENTIAL_STUDENT_SEB.getKey();


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        XmlAdaptedCredential credential = new XmlAdaptedCredential(CREDENTIAL_STUDENT_SEB);
        assertEquals(CREDENTIAL_STUDENT_SEB, credential.toModelType());
    }

    @Test
    public void toModelType_invalidUsername_throwsIllegalValueException() {
        XmlAdaptedCredential person =
                new XmlAdaptedCredential(INVALID_USERNAME, VALID_PASSWORD, VALID_KEY);
        String expectedMessage = Username.MESSAGE_USERNAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullUsername_throwsIllegalValueException() {
        XmlAdaptedCredential credential = new XmlAdaptedCredential(
            null,
            VALID_PASSWORD,
            VALID_KEY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            Username.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
            credential::toModelType);
    }

    @Test
    public void toModelType_nullPassword_throwsIllegalValueException() {
        XmlAdaptedCredential credential = new XmlAdaptedCredential(VALID_USERNAME, null,
            VALID_KEY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            Password.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
            credential::toModelType);
    }

    @Test
    @Ignore
    public void toModelType_invalidKey_throwsIllegalValueException() {
        XmlAdaptedCredential credential =
                new XmlAdaptedCredential(VALID_USERNAME, VALID_PASSWORD, VALID_KEY);
        String expectedMessage = "";
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
            credential::toModelType);
    }

    @Test
    @Ignore
    public void toModelType_nullKey_throwsIllegalValueException() {
        XmlAdaptedCredential credential = new XmlAdaptedCredential(
            VALID_USERNAME,
            VALID_PASSWORD,
            null);
        String expectedMessage = "";
        Assert.assertThrows(IllegalValueException.class, expectedMessage,
            credential::toModelType);
    }

}
