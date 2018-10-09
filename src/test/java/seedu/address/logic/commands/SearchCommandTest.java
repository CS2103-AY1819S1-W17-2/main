package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Config;
import seedu.address.model.Model;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.ReadOnlyCredentialStore;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.user.Admin;
import seedu.address.model.user.User;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;

public class SearchCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SearchCommand(null);
    }

    @Test
    public void execute_moduleSearched_successful() throws Exception {
        Module validModule = new Module("ACC");
        SearchCommandTest.ModelStubForTest modelStub = new SearchCommandTest.ModelStubForTest();

        CommandResult commandResult = new SearchCommand(validModule).execute(modelStub, commandHistory);

        assertEquals(String.format(Messages.MESSAGE_MODULE_LISTED_OVERVIEW, 2), commandResult.feedbackToUser);
    }

    @Test
    public void execute_moduleNotFound() throws Exception {
        Module validModule = new Module("GEH");
        SearchCommandTest.ModelStubForTest modelStub = new SearchCommandTest.ModelStubForTest();

        CommandResult commandResult = new SearchCommand(validModule).execute(modelStub, commandHistory);

        assertEquals(String.format(Messages.MESSAGE_MODULE_LISTED_OVERVIEW, 0), commandResult.feedbackToUser);
    }

    @Test
    public void equals() {
        Module cs1010 = new ModuleBuilder().withCode("CS1010").build();
        Module acc1002x = new ModuleBuilder().withCode("ACC1002X").build();
        SearchCommand searchCs1010Command = new SearchCommand(cs1010);
        SearchCommand searchAcc1002XCommand = new SearchCommand(acc1002x);
        // same object -> returns true
        assertTrue(searchCs1010Command.equals(searchCs1010Command));

        // same values -> returns true
        SearchCommand searchCs1010CommandCopy = new SearchCommand(cs1010);
        assertTrue(searchCs1010Command.equals(searchCs1010CommandCopy));

        // different types -> returns false
        assertFalse(searchCs1010Command.equals(1));

        // null -> returns false
        assertFalse(searchCs1010Command.equals(null));

        // different person -> returns false
        assertFalse(searchCs1010Command.equals(searchAcc1002XCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAdmin(Admin admin) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAdmin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCredentialStore getCredentialStore() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getCurrentUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveConfigFile(Config c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Module> searchKeyWordInModuleList(Module keyword) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being removed.
     */
    private class ModelStubForTest extends SearchCommandTest.ModelStub {
        final ModuleList moduleList = TypicalModules.getTypicalModuleList();

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public List<Module> searchKeyWordInModuleList(Module keyword) {
            return moduleList.searchKeyword(keyword);
        }

    }

}
