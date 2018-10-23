package seedu.modsuni.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static seedu.modsuni.testutil.TypicalModules.CS1010;
import static seedu.modsuni.testutil.TypicalModules.getTypicalModuleList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.modsuni.commons.exceptions.DataConversionException;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.ReadOnlyModuleList;

public class XmlModuleListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "XmlModuleListStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readModuleList_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readModuleList(null);
    }

    private java.util.Optional<ReadOnlyModuleList> readModuleList(String filePath) throws Exception {
        return new XmlModuleListStorage(Paths.get(filePath)).readModuleList(addToTestDataPathIfNotNull
                (filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleList("NonExistentFile.xml").isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readModuleList("NotXmlFormatModuleList.xml");

        /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
         * That means you should not have more than one exception test in one method
         */
    }

    @Test
    @Ignore
    //TODO : Further encapsulate Module class and validate input
    public void readModuleList_invalidModuleModuleList_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readModuleList("invalidModuleModuleList.xml");
    }

    @Test
    @Ignore
    //TODO : Further encapsulate Module class and validate input
    public void readModuleList_invalidAndValidModuleModuleList_throwDataConversionException() throws
            Exception {
        thrown.expect(DataConversionException.class);
        readModuleList("invalidAndValidPersonAddressBook.xml");
    }

    @Test
    public void readAndSaveModuleList_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempModuleList.xml");
        ModuleList original = getTypicalModuleList();
        XmlModuleListStorage xmlModuleListStorage = new XmlModuleListStorage(filePath);

        //Save in new file and read back
        xmlModuleListStorage.saveModuleList(original, filePath);
        ReadOnlyModuleList readBack = xmlModuleListStorage.readModuleList(filePath).get();
        assertEquals(original, new ModuleList(readBack));

        //Modify data, overwrite exiting file, and read back
        original.addModule(CS1010);
        original.removeModule(CS1010);
        xmlModuleListStorage.saveModuleList(original, filePath);
        readBack = xmlModuleListStorage.readModuleList(filePath).get();
        assertEquals(original, new ModuleList(readBack));

        //Save and read without specifying file path
        original.addModule(CS1010);
        xmlModuleListStorage.saveModuleList(original); //file path not specified
        readBack = xmlModuleListStorage.readModuleList().get(); //file path not specified
        assertEquals(original, new ModuleList(readBack));

    }

    @Test
    public void saveModuleList_nullModuleList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveModuleList(null, "SomeFile.xml");
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveModuleList(ReadOnlyModuleList moduleList, String filePath) {
        try {
            new XmlModuleListStorage(Paths.get(filePath))
                    .saveModuleList(moduleList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModuleList_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveModuleList(new ModuleList(), null);
    }

}
