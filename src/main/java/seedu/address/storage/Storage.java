package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.ConfigStoreChangedEvent;
import seedu.address.commons.events.model.CredentialStoreChangedEvent;
import seedu.address.commons.events.model.ModuleListChangedEvent;
import seedu.address.commons.events.model.SaveUserChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModuleListStorage, AddressBookStorage,
    UserPrefsStorage, CredentialStoreStorage, UserStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleAddressBookChangedEvent(AddressBookChangedEvent abce);


    /**
     * Saves the current version of the Module List to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleModuleListChangedEvent(ModuleListChangedEvent abce);

    /**
     * Saves the current version of the Credential Store to the hard disks.
     *   Creates the data file it it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleCredentialStoreChangedEvent(CredentialStoreChangedEvent csce);


    /**
     * Export the current user to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleSaveUserChangedEvent(SaveUserChangedEvent suce);
}
