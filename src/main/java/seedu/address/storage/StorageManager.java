package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.ConfigStoreChangedEvent;
import seedu.address.commons.events.model.CredentialStoreChangedEvent;
import seedu.address.commons.events.model.ModuleListChangedEvent;
import seedu.address.commons.events.model.SaveUserChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ConfigStore;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.ReadOnlyCredentialStore;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ModuleListStorage moduleListStorage;
    private CredentialStoreStorage credentialStoreStorage;
    private UserStorage userStorage;
    private ConfigStoreStorage configStoreStorage;

    public StorageManager(ModuleListStorage moduleListStorage,
                          AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          CredentialStoreStorage credentialStoreStorage,
                          ConfigStoreStorage configStoreStorage) {
        super();
        this.moduleListStorage = moduleListStorage;
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.credentialStoreStorage = credentialStoreStorage;
        this.configStoreStorage = configStoreStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Module methods ==============================

    @Override
    public Path getModuleFilePath() {
        return moduleListStorage.getModuleFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, moduleListStorage.getModuleFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleListStorage.saveModuleList(moduleList, filePath);
    }

    @Override
    @Subscribe
    public void handleModuleListChangedEvent(ModuleListChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "ModuleList data changed, saving to file"));
        try {
            saveModuleList(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    @Override
    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveAddressBook(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    // ================ CredentialStore methods =========================

    @Override
    public Path getCredentialStoreFilePath() {
        return credentialStoreStorage.getCredentialStoreFilePath();
    }

    @Override
    public Optional<ReadOnlyCredentialStore> readCredentialStore() throws DataConversionException, IOException {
        return credentialStoreStorage.readCredentialStore();
    }

    @Override
    public Optional<ReadOnlyCredentialStore> readCredentialStore(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return credentialStoreStorage.readCredentialStore();
    }

    @Override
    public void saveCredentialStore(ReadOnlyCredentialStore credentialStore) throws IOException {
        credentialStoreStorage.saveCredentialStore(credentialStore,
            credentialStoreStorage.getCredentialStoreFilePath());
    }

    @Override
    public void saveCredentialStore(ReadOnlyCredentialStore credentialStore,
                                    Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        credentialStoreStorage.saveCredentialStore(credentialStore, filePath);
    }

    @Override
    @Subscribe
    public void handleCredentialStoreChangedEvent(CredentialStoreChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Credential "
            + "Store changed. Saving to file"));
        try {
            saveCredentialStore(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    // ================ Save User methods ==============================

//    @Override
//    public Path getConfigStoreStorageFilePath() {
//        return configStoreStorage.getConfigStoreStorageFilePath();
//    }
//
//    @Override
//    public Optional<ConfigStore> readConfigStore() throws DataConversionException, IOException {
//        return configStoreStorage.readConfigStore();
//    }
//
//    @Override
//    public Optional<ConfigStore> readConfigStore(Path filePath) throws DataConversionException, IOException {
//        logger.fine("Attempting to read data from file: " + filePath);
//        return configStoreStorage.readConfigStore();
//    }
//
//    @Override
//    public void saveConfigStore(ConfigStore configStore) throws IOException {
//        configStoreStorage.saveConfigStore(configStore, configStoreStorage.getConfigStoreStorageFilePath());
//    }
//
//    @Override
//    public void saveConfigStore(ConfigStore configStore, Path filePath) throws IOException {
//        logger.fine("Attempting to write to data file: " + filePath);
//        configStoreStorage.saveConfigStore(configStore, filePath);
//    }

    @Override
    public void handleSaveUserChangedEvent(SaveUserChangedEvent cuce) {
        logger.fine("Attempting to write " + cuce.user.getName() + " to data file: " + cuce.filePath);
        userStorage.saveUser();
//        configStoreStorage.saveConfigStore(cuce.user, cuce.filePath);
    }

    @Override
    @Subscribe
    public void handleConfigStoreChangedEvent(ConfigStoreChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveConfigStore(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
