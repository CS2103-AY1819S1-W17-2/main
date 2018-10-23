package seedu.modsuni.logic.commands;

import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modsuni.testutil.TypicalModules.getTypicalModuleList;
import static seedu.modsuni.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.user.Admin;
import seedu.modsuni.testutil.AdminBuilder;

public class AddAdminCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore());
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
            new CredentialStore());
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
