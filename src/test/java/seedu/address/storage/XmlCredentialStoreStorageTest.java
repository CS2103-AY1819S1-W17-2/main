package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;
import static seedu.address.testutil.TypicalCredentials.getTypicalCredentialStore;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.credential.CredentialStore;
import seedu.address.model.credential.ReadOnlyCredentialStore;

public class XmlCredentialStoreStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
        "XmlCredentialStoreStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readCredentialStore_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readCredentialStore(null);
    }

    private java.util.Optional<ReadOnlyCredentialStore> readCredentialStore(String filePath) throws Exception {
        return new XmlCredentialStoreStorage(
            Paths.get(filePath)).readCredentialStore(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCredentialStore("NonExistentFile.xml").isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readCredentialStore("NotXmlFormatCredentialStore.xml");

        /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
         * That means you should not have more than one exception test in one method
         */
    }

    @Test
    public void readCredentialStore_invalidCredentialStore_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readCredentialStore("invalidCredentialCredentialStore.xml");
    }

    @Test
    public void readCredentialStore_invalidAndValidCredentialStore_throwDataConversionException() throws
        Exception {
        thrown.expect(DataConversionException.class);
        readCredentialStore("invalidAndValidCredentialCredentialStore.xml");
    }

    @Test
    public void readAndSaveCredentialStore_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempCredentialStore.xml");
        CredentialStore original = getTypicalCredentialStore();
        XmlCredentialStoreStorage xmlCredentialStoreStorage = new XmlCredentialStoreStorage(filePath);

        //Save in new file and read back
        xmlCredentialStoreStorage.saveCredentialStore(original, filePath);
        ReadOnlyCredentialStore readBack =
            xmlCredentialStoreStorage.readCredentialStore(filePath).get();
        assertEquals(original, new CredentialStore(readBack));

        //Modify data, overwrite exiting file, and read back
        original.addCredential(CREDENTIAL_STUDENT_SEB);
        original.removeCredential(CREDENTIAL_STUDENT_SEB);
        xmlCredentialStoreStorage.saveCredentialStore(original, filePath);
        readBack = xmlCredentialStoreStorage.readCredentialStore(filePath).get();
        assertEquals(original, new CredentialStore(readBack));

        //Save and read without specifying file path
        original.addCredential(CREDENTIAL_STUDENT_SEB);
        xmlCredentialStoreStorage.saveCredentialStore(original); //file path not specified
        readBack = xmlCredentialStoreStorage.readCredentialStore().get(); //file path not
        // specified
        assertEquals(original, new CredentialStore(readBack));

    }

    @Test
    public void saveCredentialStore_nullCredentialStore_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveCredentialStore(null, "SomeFile.xml");
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveCredentialStore(ReadOnlyCredentialStore credentialStore,
                                String filePath) {
        try {
            new XmlCredentialStoreStorage(Paths.get(filePath))
                .saveCredentialStore(credentialStore, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCredentialStore_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveCredentialStore(new CredentialStore(), null);
    }
}
