package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.user.User;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.StudentBuilder;

public class XmlUserStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "XmlUserStorageTest");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private Optional<User> readUser(Path filePath)
            throws DataConversionException, FileNotFoundException {
        return new XmlUserStorage(filePath).readUser(addToTestDataPathIfNotNull(filePath.toString()));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUser_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readUser(null);
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUser(Paths.get("NonExistentFile.xml")).isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {
        thrown.expect(DataConversionException.class);
        readUser(Paths.get("NotXmlFormatUserData.xml"));
    }

    @Test
    public void readAndSaveAdmin_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempUserData.xml");
        XmlUserStorage xmlUserStorage = new XmlUserStorage(filePath);
        User admin = new AdminBuilder().build();

        // Save and read back
        xmlUserStorage.saveUser(admin, filePath);
        Optional<User> readBackUser = xmlUserStorage.readUser(filePath);
        assertEquals(admin, readBackUser.get());
    }

    @Test
    public void readAndSaveStudent_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempUserData.xml");
        XmlUserStorage xmlUserStorage = new XmlUserStorage(filePath);
        User student = new StudentBuilder().build();

        // Save and read back
        xmlUserStorage.saveUser(student, filePath);
        Optional<User> readBackUser = xmlUserStorage.readUser(filePath);
        assertEquals(student, readBackUser.get());
    }
}
