package seedu.modsuni.logic.commands;

import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modsuni.testutil.TypicalModules.getTypicalModuleList;
import static seedu.modsuni.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.user.Admin;
import seedu.modsuni.testutil.AdminBuilder;

/**
 * Contains integration tests for {@code AddAdminCommand}.
 */
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
            validAdmin.getUsername(),
            new Password("#Qwerty123"),
            "k");

        Model expectedModel = new ModelManager(
            model.getModuleList(),
            model.getAddressBook(),
            new UserPrefs(),
            new CredentialStore());
        expectedModel.addCredential(validCredential);
        expectedModel.addAdmin(validAdmin, Paths.get("dummyconfig"));

        model.setCurrentUser(new AdminBuilder().build());

        assertCommandSuccess(
            new AddAdminCommand(
                validAdmin,
                new Credential(
                    validAdmin.getUsername(),
                    new Password("#Qwerty123"),
                    "k"), Paths.get("dummyconfig")),
            model,
            commandHistory,
            String.format(AddAdminCommand.MESSAGE_SUCCESS, validAdmin), expectedModel);
    }
}
