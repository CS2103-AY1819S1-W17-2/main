package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ConfigStore;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.CredentialStore;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;
import seedu.address.model.user.Admin;
import seedu.address.testutil.AdminBuilder;

public class AddAdminCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore(),
            new ConfigStore());
    }

    @Test
    public void execute_newAdmin_success() {
        Admin validAdmin = new AdminBuilder().build();
        Credential validCredential = new Credential(
            new Username("u"),
            new Password("#Qwerty123"),
            "k");

        Model expectedModel = new ModelManager(
            model.getModuleList(),
            model.getAddressBook(),
            new UserPrefs(),
            new CredentialStore(),
            new ConfigStore());
        expectedModel.addAdmin(validAdmin);
        expectedModel.addCredential(validCredential);

        model.setCurrentUser(new AdminBuilder().build());

        assertCommandSuccess(
            new AddAdminCommand(
                validAdmin,
                new Credential(
                    new Username("u"),
                    new Password("#Qwerty123"),
                    "k")),
            model,
            commandHistory,
            String.format(AddAdminCommand.MESSAGE_SUCCESS, validAdmin), expectedModel);
    }
}
