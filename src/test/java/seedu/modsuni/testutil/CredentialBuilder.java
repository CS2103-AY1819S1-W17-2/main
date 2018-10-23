package seedu.modsuni.testutil;

import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;

/**
 * A utility class to help with building Credential objects.
 */
public class CredentialBuilder {

    public static final String DEFAULT_USERNAME = "max33";
    public static final String DEFAULT_PASSWORD =
        "8501b3c044ffb3a2d57714bd79bcdbed831eb39d72cb0fe6c3c0a79a16a1208b";

    private Username username;
    private Password password;

    public CredentialBuilder() {
        username = new Username(DEFAULT_USERNAME);
        password = new Password(DEFAULT_PASSWORD);
    }

    /**
     * Initializes the CredentialBuilder with the data of {@code
     * credentialToCopy}.
     */
    public CredentialBuilder(Credential credentialToCopy) {
        this.username = credentialToCopy.getUsername();
        this.password = credentialToCopy.getPassword();
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

    public Credential build() {
        return new Credential(this.username, this.password);
    }


}
