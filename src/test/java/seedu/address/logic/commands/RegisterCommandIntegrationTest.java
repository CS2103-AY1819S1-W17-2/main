package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.address.testutil.TypicalCredentials.getTypicalCredentialStore;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.ConfigStore;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;
import seedu.address.model.user.User;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * RegisterCommand}.
 */
public class RegisterCommandIntegrationTest {

    private static Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore(),
            new ConfigStore());
        model.setCurrentUser(new StudentBuilder().build());
    }

    @Test
    public void executeNewCredentialSuccess() {
        Credential validCredential = new Credential(
            new Username("u"),
            new Password("#Qwerty123"),
            "k");
        User dummyUser = new StudentBuilder().build();

        Model expectedModel = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore(),
            new ConfigStore());
        expectedModel.addCredential(validCredential);

        assertCommandSuccess(new RegisterCommand(validCredential, dummyUser), model,
            commandHistory,
            String.format(RegisterCommand.MESSAGE_SUCCESS, validCredential), expectedModel);
    }

    @Test
    public void executeDuplicateCredentialThrowsCommandException() {
        assertCommandFailure(new RegisterCommand(CREDENTIAL_STUDENT_MAX, model.getCurrentUser()),
            model,
            commandHistory,
            RegisterCommand.MESSAGE_DUPLICATE_USERNAME);
    }


}
