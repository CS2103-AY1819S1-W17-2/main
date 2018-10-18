package seedu.modsuni.logic.commands;

import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modsuni.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.modsuni.testutil.TypicalCredentials.getTypicalCredentialStore;

import org.junit.Before;
import org.junit.Test;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.AddressBook;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.user.User;
import seedu.modsuni.testutil.StudentBuilder;

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
            getTypicalCredentialStore());
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
            getTypicalCredentialStore());
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
