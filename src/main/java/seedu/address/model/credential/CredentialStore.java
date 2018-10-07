package seedu.address.model.credential;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wraps all CredentialStore data
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class CredentialStore implements ReadOnlyCredentialStore {

    private final HashMap<String, Credential> credentialStore = new HashMap<>();
    private final HashMap<String, String> keyMap = new HashMap<>();

    /**
     * Returns true if a credential with the same parameters as {@code
     * credential} exists in the credential store.
     *
     * @param credential
     * @return
     */
    public boolean hasCredential(Credential credential) {
        requireNonNull(credential);
        return credentialStore.containsKey(credential.getUsername().toString());
    }

    /**
     * Adds a credential to the credential store.
     * The person must not already exist in the credential store.
     *
     * @param credential
     */
    public void addCredential(Credential credential) {
        credentialStore.put(credential.getUsername().toString(),
            credential);
        keyMap.put(credential.getUsername().toString(), credential.getKey());
    }

    @Override
    public List<Credential> getCredentials() {
        List<Credential> credentials = new ArrayList<>();
        for (Map.Entry<String, Credential> entry : credentialStore.entrySet()) {
            Credential account = new Credential(
                new Username(entry.getKey()),
                entry.getValue().getPassword(),
                keyMap.get(entry.getKey()));
            credentials.add(account);
        }
        return credentials;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof CredentialStore)) {
            return false;
        }

        // state check
        CredentialStore other = (CredentialStore) obj;
        return credentialStore.equals(other.credentialStore)
            && keyMap.equals(other.keyMap);
    }
}
