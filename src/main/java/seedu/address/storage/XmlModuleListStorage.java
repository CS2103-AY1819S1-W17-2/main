package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyModuleList;

/**
 * A class to access Modules data stored as an xml file on the hard disk.
 */
public class XmlModuleListStorage implements ModuleListStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlModuleListStorage.class);

    private Path filePath;

    public XmlModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(filePath);
    }

    /**
     * Similar to {@link #readModuleList()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException,
            IOException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("ModuleList file " + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableModuleList xmlModuleList = XmlFileStorage.loadModuleListFromSaveFile(filePath);
        try {
            return Optional.of(xmlModuleList.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    /**
     * Similar to {@link #saveModuleList(ReadOnlyModuleList, Path)}
     * @param filePath location of the data. Cannot be null
     */
    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveDataToFile(filePath, new XmlSerializableModuleList(moduleList));
    }
}
