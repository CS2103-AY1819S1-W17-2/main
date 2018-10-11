package seedu.address.model.credential;

import java.util.List;

/**
 * Unmodifiable view of a credential store
 *
 */
public interface ReadOnlyCredentialStore {
    /**
     * Returns an list of credentials.
     * This list will not contain any duplicate credentials.
     */
    List<Credential> getCredentials();
}
