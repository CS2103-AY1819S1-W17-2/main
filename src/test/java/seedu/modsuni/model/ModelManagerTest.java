package seedu.modsuni.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static seedu.modsuni.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.modsuni.testutil.TypicalAdmins.BRAD;
import static seedu.modsuni.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.modsuni.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;
import static seedu.modsuni.testutil.TypicalModules.ACC1002;
import static seedu.modsuni.testutil.TypicalModules.CS1010;
import static seedu.modsuni.testutil.TypicalPersons.ALICE;
import static seedu.modsuni.testutil.TypicalPersons.BENSON;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.model.user.exceptions.NotStudentUserException;
import seedu.modsuni.model.user.student.Student;
import seedu.modsuni.testutil.AddressBookBuilder;
import seedu.modsuni.testutil.CredentialStoreBuilder;
import seedu.modsuni.testutil.ModuleListBuilder;
import seedu.modsuni.testutil.StudentBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Before
    public void setUp() {
        modelManager = new ModelManager();
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasPerson(null);
    }

    @Test
    public void hasModuleTaken_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModuleTaken(null);
    }

    @Test
    public void hasModuleTaken_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.hasModuleTaken(ACC1002);
    }

    @Test
    public void hasModuleTaken_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.setCurrentUser(BRAD);
        modelManager.hasModuleTaken(ACC1002);
    }

    @Test
    public void hasModuleTaken_moduleNotInTakenModuleList_returnsFalse() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        assertFalse(modelManager.hasModuleTaken(ACC1002));
    }

    @Test
    public void hasModuleTaken_moduleInTakenModuleList_returnsTrue() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        student.addModulesTaken(ACC1002);
        assertTrue(modelManager.hasModuleTaken(ACC1002));
    }

    @Test
    public void addModuleTaken_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.addModuleTaken(null);
    }

    @Test
    public void addModuleTaken_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.addModuleTaken(ACC1002);
    }

    @Test
    public void addModuleTaken_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.setCurrentUser(BRAD);
        modelManager.addModuleTaken(ACC1002);
    }

    @Test
    public void addModuleTaken_withStudent_addsSuccess() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        modelManager.addModuleTaken(ACC1002);
        assertTrue(student.hasModulesTaken(ACC1002));
    }

    @Test
    public void removeModuleTaken_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.removeModuleTaken(null);
    }

    @Test
    public void removeModuleTaken_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.removeModuleTaken(ACC1002);
    }

    @Test
    public void removeModuleTaken_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.setCurrentUser(BRAD);
        modelManager.removeModuleTaken(ACC1002);
    }

    @Test
    public void removeModuleTaken_withStudent_removesSuccess() {
        Student student = new StudentBuilder().build();
        student.addModulesTaken(ACC1002);
        modelManager.setCurrentUser(student);
        modelManager.removeModuleTaken(ACC1002);
        assertFalse(student.hasModulesTaken(ACC1002));
    }

    @Test
    public void hasModuleStaged_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModuleStaged(null);
    }


    @Test
    public void hasModuleStaged_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.hasModuleStaged(ACC1002);
    }

    @Test
    public void hasModuleStaged_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager.setCurrentUser(BRAD);
        modelManager.hasModuleStaged(ACC1002);
    }

    @Test
    public void hasModuleStaged_moduleNotInStagedModuleList_returnsFalse() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        assertFalse(modelManager.hasModuleStaged(ACC1002));
    }

    @Test
    public void hasModuleStaged_moduleInStagedModuleList_returnsTrue() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        student.addModulesStaged(ACC1002);
        assertTrue(modelManager.hasModuleStaged(ACC1002));
    }

    @Test
    public void addModuleStaged_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.addModuleStaged(null);
    }

    @Test
    public void addModuleStaged_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.addModuleStaged(ACC1002);
    }

    @Test
    public void addModuleStaged_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.setCurrentUser(BRAD);
        modelManager.addModuleStaged(ACC1002);
    }

    @Test
    public void addModuleStaged_withStudent_addsSuccess() {
        Student student = new StudentBuilder().build();
        modelManager.setCurrentUser(student);
        modelManager.addModuleStaged(ACC1002);
        assertTrue(student.hasModulesStaged(ACC1002));
    }

    @Test
    public void removeModuleStaged_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.removeModuleStaged(null);
    }

    @Test
    public void removeModuleStaged_nullUser_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.removeModuleStaged(ACC1002);
    }

    @Test
    public void removeModuleStaged_notStudent_throwsNotStudentUserException() {
        thrown.expect(NotStudentUserException.class);
        modelManager = new ModelManager();
        modelManager.setCurrentUser(BRAD);
        modelManager.removeModuleStaged(ACC1002);
    }

    @Test
    public void removeModuleStaged_withStudent_removesSuccess() {
        Student student = new StudentBuilder().build();
        student.addModulesStaged(ACC1002);
        modelManager.setCurrentUser(student);
        modelManager.removeModuleStaged(ACC1002);
        assertFalse(student.hasModulesStaged(ACC1002));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredPersonList().remove(0);
    }

    @Test
    public void addModuleToDatabase_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.addModuleToDatabase(null);
    }

    @Test
    public void removeModuleFromDatabase_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.removeModuleFromDatabase(null);
    }

    @Test
    public void hasModuleInDatabase_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasModuleInDatabase(null);
    }

    @Test
    public void hasModuleInDatabase_moduleAbsent_returnsFalse() {
        assertFalse(modelManager.hasModuleInDatabase(CS1010));
    }

    @Test
    public void hasModuleInDatabase_moduleExist_returnsTrue() {
        modelManager.addModuleToDatabase(CS1010);
        assertTrue(modelManager.hasModuleInDatabase(CS1010));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredModuleList().remove(0);
    }

    @Test
    public void equals() {
        ModuleList moduleList = new ModuleListBuilder().withModule(CS1010).build();
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();
        CredentialStore credentialStore = new CredentialStoreBuilder()
            .withCredentials(CREDENTIAL_STUDENT_MAX)
            .withCredentials(CREDENTIAL_STUDENT_SEB).build();
        CredentialStore differentCredentialStore = new CredentialStore();

        // same values -> returns true
        modelManager = new ModelManager(moduleList, addressBook, userPrefs,
            credentialStore);
        ModelManager modelManagerCopy = new ModelManager(moduleList,
            addressBook, userPrefs, credentialStore);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(moduleList, differentAddressBook, userPrefs,
                                                        differentCredentialStore)));

        // different filteredList -> returns false
        // String[] keywords = ALICE.getName().fullName.split("\\s+");
        // modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        // ModelManager test = new ModelManager(moduleList, addressBook, userPrefs,
        // credentialStore, configStore);
        // assertFalse(modelManager.equals(new ModelManager(moduleList, addressBook, userPrefs,
        //                                                  credentialStore, configStore)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertTrue(modelManager.equals(new ModelManager(moduleList,
            addressBook, differentUserPrefs, credentialStore)));
    }
}
