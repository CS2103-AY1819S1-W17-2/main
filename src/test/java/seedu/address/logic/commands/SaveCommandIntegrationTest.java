package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.CredentialStore;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * SaveCommand}.
 */
public class SaveCommandIntegrationTest {

    private static Model model;
    private CommandHistory commandHistory = new CommandHistory();
    private static Path SAVE_PATH = Paths.get("/userdata.xml");

    @Before
    public void setUp() {
        model = new ModelManager(
                new ModuleList(),
                new AddressBook(),
                new UserPrefs(),
                new CredentialStore());
    }

    @Test
    public void executeNewSaveStudentSuccess() {
        model.setCurrentUser(new StudentBuilder().build());
        assertCommandSuccess(new SaveCommand(SAVE_PATH), model,
                commandHistory, String.format(SaveCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void executeNewSaveAdminSuccess() {
        model.setCurrentUser(new AdminBuilder().build());
        assertCommandSuccess(new SaveCommand(SAVE_PATH), model,
                commandHistory, String.format(SaveCommand.MESSAGE_SUCCESS), model);
    }

}
