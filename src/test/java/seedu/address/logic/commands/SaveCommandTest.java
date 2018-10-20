package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.credential.Credential;

public class SaveCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructorNullCredentialThrowsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SaveCommand(null);
    }

    @Test
    public void equals() {

        Path firstPath = Paths.get("/path1.xml");
        Path secondPath = Paths.get("/path2.xml");

        SaveCommand firstSave = new SaveCommand(firstPath);
        SaveCommand secondSave = new SaveCommand(secondPath);

        // same object -> returns true
        assertTrue(firstSave.equals(firstSave));

        // same values -> returns true
        SaveCommand firstSaveCopy = new SaveCommand(firstPath);
        assertTrue(firstSave.equals(firstSaveCopy));

        // different types -> returns false
        assertFalse(firstSave.equals(1));

        // null -> returns false
        assertFalse(firstSave.equals(null));

        // different person -> returns false
        assertFalse(firstSave.equals(secondSave));
    }
}
