package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.ReadOnlyCredentialStore;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.user.Admin;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleToDatabaseCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddModuleToDatabaseCommand(null);
    }

    @Test
    public void notAdmin_throwsCommandException() throws Exception {
        AddModuleToDatabaseCommand addModuleToDatabaseCommand =
                new AddModuleToDatabaseCommand(new ModuleBuilder().build());

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddAdminCommand.MESSAGE_NOT_ADMIN);
        Model model = new ModelManager();
        User fakeAdmin = new AdminBuilder().withRole(Role.STUDENT).build();
        model.setCurrentUser(fakeAdmin);

        addModuleToDatabaseCommand.execute(model, commandHistory);
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddModuleToDatabaseCommand(validModule).execute(modelStub, commandHistory);

        assertEquals(String.format(AddModuleToDatabaseCommand.MESSAGE_SUCCESS), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() throws Exception {
        Module validModule = new ModuleBuilder().build();
        AddModuleToDatabaseCommand addModuleToDatabaseCommand = new AddModuleToDatabaseCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddModuleToDatabaseCommand.MESSAGE_DUPLICATE_MODULE);
        addModuleToDatabaseCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Module module1 = new ModuleBuilder().withCode("CS1000").build();
        Module module2 = new ModuleBuilder().withCode("CS2000").build();
        AddModuleToDatabaseCommand addModule1Command = new AddModuleToDatabaseCommand(module1);
        AddModuleToDatabaseCommand addModule2Command = new AddModuleToDatabaseCommand(module2);

        // same object -> returns true
        assertTrue(addModule1Command.equals(addModule1Command));

        // same values -> returns true
        AddModuleToDatabaseCommand addModule1CommandCopy = new AddModuleToDatabaseCommand(module1);
        assertTrue(addModule1Command.equals(addModule1CommandCopy));

        // different types -> returns false
        assertFalse(addModule1Command.equals(1));

        // null -> returns false
        assertFalse(addModule1Command.equals(null));

        // different person -> returns false
        assertFalse(addModule1Command.equals(addModule2Command));
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
        public void addAdmin(Admin admin) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleToDatabase(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAdmin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCredentialStore getCredentialStore() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public User getCurrentUser() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void setCurrentUser(User user) {
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
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

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
        public boolean isAdmin() {
            return true;
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModuleToDatabase(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            return new ModuleList();
        }

        @Override
        public boolean isAdmin() {
            return true;
        }
    }
}
