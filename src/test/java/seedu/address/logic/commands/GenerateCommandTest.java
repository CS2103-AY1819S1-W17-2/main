package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.CredentialStore;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GenerateCommand.
 */
public class GenerateCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore());
        expectedModel = new ModelManager(
            model.getModuleList(),
            model.getAddressBook(),
            new UserPrefs(),
            new CredentialStore());
    }

    @Test
    public void execute() {
        assertCommandSuccess(new GenerateCommand(), model, commandHistory, GenerateCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}
