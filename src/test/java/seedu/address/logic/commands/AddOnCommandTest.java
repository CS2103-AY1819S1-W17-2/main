package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalModules.ACC1002X;
import static seedu.address.testutil.TypicalModules.CS1010;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
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
import seedu.address.model.user.Student;
import seedu.address.model.user.User;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalModules;

public class AddOnCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddOnCommand(null);
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        AddOnCommandTest.ModelStubAcceptingModuleAdded modelStub = new AddOnCommandTest.ModelStubAcceptingModuleAdded();
        Module validModuleBeforeSearch = new Module("ACC1002X");
        AddOnCommand addOncommand = new AddOnCommand(validModuleBeforeSearch);

        CommandResult commandResult = addOncommand.execute(modelStub, commandHistory);
        Module validModuleAfterSearch = addOncommand.getSearchedMoudle();

        assertNotEquals(validModuleBeforeSearch, validModuleAfterSearch);
        assertEquals(String.format(AddOnCommand.MESSAGE_SUCCESS, validModuleAfterSearch), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validModuleAfterSearch), modelStub.student.getModules());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() throws Exception {
        Module validModule = new Module("ACC1002X");
        AddOnCommand addOnCommand = new AddOnCommand(validModule);
        AddOnCommandTest.ModelStub modelStub = new AddOnCommandTest.ModelStubWithModule(validModule);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddOnCommand.MESSAGE_DUPLICATE_MODULE);
        addOnCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_nonexistentModule_throwsCommandException() throws Exception {
        Module nonexistentModule = new Module("CS1010");
        AddOnCommand addOnCommand = new AddOnCommand(nonexistentModule);
        AddOnCommandTest.ModelStub modelStub = new AddOnCommandTest.ModelStubWithModule(nonexistentModule);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddOnCommand. MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        addOnCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Module cs1010 = new ModuleBuilder().withCode("CS1010").build();
        Module acc1002x = new ModuleBuilder().withCode("ACC1002X").build();
        AddOnCommand addCS1010Command = new AddOnCommand(cs1010);
        AddOnCommand addACC1002XCommand = new AddOnCommand(acc1002x);

        // same object -> returns true
        assertTrue(addCS1010Command.equals(addCS1010Command));

        // same values -> returns true
        AddOnCommand addCS1010CommandCopy = new AddOnCommand(cs1010);
        assertTrue(addCS1010Command.equals(addCS1010CommandCopy));

        // different types -> returns false
        assertFalse(addCS1010Command.equals(1));

        // null -> returns false
        assertFalse(addCS1010Command.equals(null));

        // different person -> returns false
        assertFalse(addCS1010Command.equals(addACC1002XCommand));
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithModule extends AddOnCommandTest.ModelStub {
        private final Module module;
        private final ModuleList moduleList = TypicalModules.getTypicalModuleList();

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            return moduleList.getModuleInformation(module);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingModuleAdded extends AddOnCommandTest.ModelStub {
        final Student student = new StudentBuilder().build();
        final ModuleList moduleList = TypicalModules.getTypicalModuleList();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return student.hasModule(module);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            student.addModule(module);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            return moduleList.getModuleInformation(module);
        }

    }

}
