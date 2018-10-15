package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalModules.ACC1002X;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
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
import seedu.address.model.user.student.Student;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalModules;

public class AddModuleToStudentStagedCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddModuleToStudentStagedCommand(null);
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        AddModuleToStudentStagedCommandTest.ModelStubAcceptingModuleAdded modelStub =
                new AddModuleToStudentStagedCommandTest.ModelStubAcceptingModuleAdded();
        Module validModuleBeforeSearch = new Module("ACC1002X");
        AddModuleToStudentStagedCommand addModuleToStudentStagedCommand =
                new AddModuleToStudentStagedCommand(validModuleBeforeSearch);

        CommandResult commandResult = addModuleToStudentStagedCommand.execute(modelStub, commandHistory);
        Module validModuleAfterSearch = addModuleToStudentStagedCommand.getSearchedModule();
        ModuleList expectModuleList = new ModuleList();
        expectModuleList.addModule(validModuleAfterSearch);


        assertNotEquals(validModuleBeforeSearch, validModuleAfterSearch);
        assertEquals(String.format(AddModuleToStudentStagedCommand.MESSAGE_SUCCESS,
                validModuleAfterSearch), commandResult.feedbackToUser);
        assertEquals(expectModuleList, modelStub.student.getModulesStaged());
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() throws Exception {
        Module validModuleBeforeSearch = new Module("ACC1002X");
        AddModuleToStudentStagedCommand addModuleToStudentStagedCommand =
                new AddModuleToStudentStagedCommand(validModuleBeforeSearch);
        AddModuleToStudentStagedCommandTest.ModelStub modelStub =
                new AddModuleToStudentStagedCommandTest.ModelStubWithModule(validModuleBeforeSearch);

        Module validModuleAfterSearch = ACC1002X;
        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format(AddModuleToStudentStagedCommand.MESSAGE_DUPLICATE_MODULE,
                validModuleAfterSearch));

        addModuleToStudentStagedCommand.execute(modelStub, commandHistory);

    }

    @Test
    public void execute_nonexistentModule_throwsCommandException() throws Exception {
        Module nonexistentModule = new Module("CS1010");
        AddModuleToStudentStagedCommand addModuleToStudentStagedCommand =
                new AddModuleToStudentStagedCommand(nonexistentModule);
        AddModuleToStudentStagedCommandTest.ModelStub modelStub =
                new AddModuleToStudentStagedCommandTest.ModelStubWithModule(nonexistentModule);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddModuleToStudentStagedCommand.MESSAGE_MODULE_NOT_EXISTS_IN_DATABASE);
        addModuleToStudentStagedCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void execute_nonStudentUser_throwsCommandException() throws Exception {
        Module validModuleBeforeSearch = new Module("ACC1002X");
        AddModuleToStudentStagedCommand addModuleToStudentStagedCommand =
                new AddModuleToStudentStagedCommand(validModuleBeforeSearch);
        AddModuleToStudentStagedCommandTest.ModelStub modelStub =
                new AddModuleToStudentStagedCommandTest.ModelStubWithNonStudentUser();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddModuleToStudentStagedCommand.MESSAGE_NOT_STUDENT);
        addModuleToStudentStagedCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Module cs1010 = new ModuleBuilder().withCode("CS1010").build();
        Module acc1002x = new ModuleBuilder().withCode("ACC1002X").build();
        AddModuleToStudentStagedCommand addCs1010Command = new AddModuleToStudentStagedCommand(cs1010);
        AddModuleToStudentStagedCommand addAcc1002XCommand = new AddModuleToStudentStagedCommand(acc1002x);

        // same object -> returns true
        assertTrue(addCs1010Command.equals(addCs1010Command));

        // same values -> returns true
        AddModuleToStudentStagedCommand addCs1010CommandCopy = new AddModuleToStudentStagedCommand(cs1010);
        assertTrue(addCs1010Command.equals(addCs1010CommandCopy));

        // different types -> returns false
        assertFalse(addCs1010Command.equals(1));

        // null -> returns false
        assertFalse(addCs1010Command.equals(null));

        // different person -> returns false
        assertFalse(addCs1010Command.equals(addAcc1002XCommand));
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
        public boolean hasModuleTaken(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeModuleTaken(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleTaken(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleStaged(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeModuleStaged(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModuleStaged(Module module) {
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
        public ObservableList<Module> getObservableModuleList() {
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
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
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
        public void removeModuleFromDatabase(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleInDatabase(Module module) {
            return false;
        }

        @Override
        public boolean hasCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isVerifiedCredential(Credential credential) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAdmin() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isStudent() {
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
        public Optional<Module> searchModuleInModuleList(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Module> searchKeyWordInModuleList(Module keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserFile(User user, Path savePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithNonStudentUser extends AddModuleToStudentStagedCommandTest.ModelStub {
        @Override
        public boolean isStudent() {
            return false;
        }
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends AddModuleToStudentStagedCommandTest.ModelStub {
        private final Module module;
        private final ModuleList moduleList = TypicalModules.getTypicalModuleList();

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModuleStaged(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            return moduleList.getModuleInformation(module);
        }

        @Override
        public boolean isStudent() {
            return true;
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingModuleAdded extends AddModuleToStudentStagedCommandTest.ModelStub {
        final Student student = new StudentBuilder().build();
        final ModuleList moduleList = TypicalModules.getTypicalModuleList();

        @Override
        public boolean hasModuleStaged(Module module) {
            requireNonNull(module);
            return student.hasModulesStaged(module);
        }

        @Override
        public void addModuleStaged(Module module) {
            requireNonNull(module);
            student.addModulesStaged(module);
        }

        @Override
        public Optional<Module> searchModuleInModuleList(Module module) {
            return moduleList.getModuleInformation(module);
        }

        @Override
        public boolean isStudent() {
            return true;
        }

    }

}
