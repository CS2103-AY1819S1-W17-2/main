package seedu.modsuni.storage;
import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.modsuni.commons.core.LogsCenter;
/**
 * A class to access Modules data stored as an xml file on the hard disk.
 */
public class XmlModuleStorage implements ModuleStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlAddressBookStorage.class);
    private Path filePath;
    public XmlModuleStorage(Path filePath) {
        this.filePath = filePath;
    }
    @Override
    public Path getAddressBookFilePath() {
        return null;
    }
}
