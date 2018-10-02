package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.credential.Credential;
import seedu.address.model.user.Admin;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.testutil.AdminBuilder;

public class AddAdminCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullCredential_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(new AdminBuilder().build(), null);
    }

    @Test
    public void constructor_nullAdmin_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(null,
                new Credential("username", "password" , "key"));
    }

    @Test
    public void constructor_bothNull_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddAdminCommand(null, null);
    }

    @Test
    public void notAdmin_throwsCommandException() throws Exception {
        AddAdminCommand addAdminCommand =
            new AddAdminCommand(new AdminBuilder().build(),
            new Credential("u", "p", "k"));

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
        Credential credential = new Credential("u", "p", "k");
        AddAdminCommand addAliceCommand = new AddAdminCommand(alice, credential);
        AddAdminCommand addBobCommand = new AddAdminCommand(bob, credential);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddAdminCommand addAliceCommandCopy = new AddAdminCommand(alice, credential);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
}
