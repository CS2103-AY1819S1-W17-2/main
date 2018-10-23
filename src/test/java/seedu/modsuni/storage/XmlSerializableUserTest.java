package seedu.modsuni.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.modsuni.commons.util.XmlUtil;

public class XmlSerializableUserTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "XmlSerializableUserTest");
    private static final Path VALID_USER_DATA_FILE =
            TEST_DATA_FOLDER.resolve("validUserData.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_validUserDataFile_success() throws Exception {
        XmlSerializableUser dataFromFile = XmlUtil.getDataFromFile(VALID_USER_DATA_FILE,
                        XmlSerializableUser.class);
        dataFromFile.toModelType();
    }
}
