package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.CredentialStore;

public class ClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitAddressBook();

        assertCommandSuccess(
            new ClearCommand(),
            model,
            commandHistory,
            ClearCommand.MESSAGE_SUCCESS,
            expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore());
        Model expectedModel = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore());
        expectedModel.resetData(new AddressBook());
        expectedModel.commitAddressBook();

        assertCommandSuccess(
            new ClearCommand(),
            model,
            commandHistory,
            ClearCommand.MESSAGE_SUCCESS,
            expectedModel);
    }

}
