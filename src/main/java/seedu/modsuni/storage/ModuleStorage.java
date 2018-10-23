package seedu.modsuni.storage;
import java.nio.file.Path;
/**
 * Represents a storage for {@link seedu.modsuni.model.Module}.
 */
public interface ModuleStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();
}
