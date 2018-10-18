package seedu.modsuni.testutil;

import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.CredentialStore;

/**
 * A utility class to help with building CredentialStore objects.
 * Example usage: <br>
 * {@code CredentialStore cs = new CredentialStoreBuilder()
 * .withCredential(newCredential).build();}
 */
public class CredentialStoreBuilder {

    private CredentialStore credentialStore;

    public CredentialStoreBuilder() {
        credentialStore = new CredentialStore();
    }

    public CredentialStoreBuilder(CredentialStore credentialStore) {
        this.credentialStore = credentialStore;
    }

    /**
     * Adds a new {@code Credential} to the {@code CredentialStore} that we are
     * building.
     */
    public CredentialStoreBuilder withCredentials(Credential cred) {
        credentialStore.addCredential(cred);
        return this;
    }

    public CredentialStore build() {
        return credentialStore;
    }
}
