package seedu.modsuni.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.user.Admin;
import seedu.modsuni.model.user.Role;
import seedu.modsuni.model.user.User;
import seedu.modsuni.testutil.AdminBuilder;

public class AddAdminCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullCredential_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(new AdminBuilder().build(), null, Paths.get("dummyconfig"));
    }

    @Test
    public void constructor_nullAdmin_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(null,
                new Credential(
                    new Username("username"),
                    new Password("#Qwerty123"), "key"), Paths.get("dummyconfig"));
    }

    @Test
    public void constructor_allNull_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(null, null, null);
    }

    @Test
    public void notAdmin_throwsCommandException() throws Exception {
        AddAdminCommand addAdminCommand =
            new AddAdminCommand(new AdminBuilder().build(),
            new Credential(
                new Username("u"),
                new Password("#Qwerty123"),
                "k"), Paths.get("dummyconfig"));

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddAdminCommand.MESSAGE_NOT_ADMIN);
        Model model = new ModelManager();
        User fakeAdmin = new AdminBuilder().withRole(Role.STUDENT).build();
        model.setCurrentUser(fakeAdmin);

        addAdminCommand.execute(model, commandHistory);
    }

    @Test
    public void equals() {
        Admin alice = new AdminBuilder().withName("Alice").build();
        Admin bob = new AdminBuilder().withName("Bob").build();
        Credential credential = new Credential(
            new Username("u"),
            new Password("#Qwerty123"),
            "k");
        AddAdminCommand addAliceCommand = new AddAdminCommand(alice, credential, Paths.get("dummyconfig"));
        AddAdminCommand addBobCommand = new AddAdminCommand(bob, credential, Paths.get("dummyconfig"));

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddAdminCommand addAliceCommandCopy = new AddAdminCommand(alice, credential, Paths.get("dummyconfig"));
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
}
