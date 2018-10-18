package seedu.modsuni.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.modsuni.commons.exceptions.DataConversionException;
import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.model.credential.ReadOnlyCredentialStore;

/**
 * Represents a storage for {@link CredentialStore}.
 */
public interface CredentialStoreStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getCredentialStoreFilePath();

    /**
     * Returns CredentialStore data as a {@link CredentialStore}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCredentialStore> readCredentialStore() throws DataConversionException,
        IOException;

    /**
     * @see #getCredentialStoreFilePath()
     */
    Optional<ReadOnlyCredentialStore> readCredentialStore(Path filePath)
        throws DataConversionException, IOException;

    /**
     * Saves the given {@link CredentialStore} to the storage.
     *
     * @param credentialStore cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCredentialStore(ReadOnlyCredentialStore credentialStore) throws IOException;

    /**
     * @see #saveCredentialStore(ReadOnlyCredentialStore)
     */
    void saveCredentialStore(ReadOnlyCredentialStore credentialStore,
                             Path filePath) throws IOException;
}
