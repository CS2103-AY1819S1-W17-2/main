package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.user.User;

/**
 * A class to access AddressBook data stored as an xml file on the hard disk.
 */
public class XmlUserStorage implements UserStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlAddressBookStorage.class);

    private Path filePath;

    public XmlUserStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUserSavedFilePath() {
        return filePath;
    }

    @Override
    public Optional<User> readUser() throws DataConversionException, IOException {
        return readUser(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<User> readUser(Path filePath)
            throws DataConversionException, FileNotFoundException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("User saved file " + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableUser xmlUser= XmlFileStorage.loadDataFromSaveFile(filePath);
        try {
            return Optional.of(xmlAddressBook.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUser(User user) throws IOException {
        saveUser(user, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveUser(User user, Path filePath) throws IOException {
        requireNonNull(user);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveDataToFile(filePath, new XmlSerializableUser(user));
    }

}

