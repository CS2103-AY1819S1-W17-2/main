package seedu.modsuni.logic.commands;

import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.AddressBook;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.testutil.AdminBuilder;
import seedu.modsuni.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * SaveCommand}.
 */
public class SaveCommandIntegrationTest {

    private static Model model;
    private static final Path SAVE_PATH = Paths.get("/userdata.xml");
    private CommandHistory commandHistory = new CommandHistory();

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
