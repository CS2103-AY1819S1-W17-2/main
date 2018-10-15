package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;
import static seedu.address.testutil.TypicalModules.CS1010;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.credential.CredentialStore;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.CredentialStoreBuilder;
import seedu.address.testutil.ModuleListBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasPerson(null);
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
