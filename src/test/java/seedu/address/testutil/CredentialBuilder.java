package seedu.address.testutil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;

/**
 * A utility class to help with building Credential objects.
 */
public class CredentialBuilder {

    public static final String DEFAULT_USERNAME = "lewis";
    public static final String DEFAULT_PASSWORD = "#Hamilton44";
    public static final String DEFAULT_KEY = "randomKeyLewis44";

    private Username username;
    private Password password;
    private String key;

    public CredentialBuilder() {
        username = new Username(DEFAULT_USERNAME);
        password = new Password(DEFAULT_PASSWORD);
        key = DEFAULT_KEY;
    }

    /**
     * Initializes the CredentialBuilder with the data of {@code
     * credentialToCopy}.
     */
    public CredentialBuilder(Credential credentialToCopy) {
        this.username = credentialToCopy.getUsername();
        this.password = credentialToCopy.getPassword();
        this.key = credentialToCopy.getKey();
    }

    /**
     * Sets the {@code username} of the {@code Credential} that we are building.
     */
    public CredentialBuilder withUsername(String withUsername) {
        this.username = new Username(withUsername);
        return this;
    }

    /**
     * Sets the {@code password} of the {@code Credential} that we are building.
     */
    public CredentialBuilder withPassword(String withPassword) {
        this.password = new Password(withPassword);
        return this;
    }

    /**
     * Sets the {@code key} of the {@code Credential} that we are building.
     */
    public CredentialBuilder withKey(String withKey) {
        this.key = withKey;
        return this;
    }


    public Credential build() {
        return new Credential(this.username, this.password, this.key);
    }


}
