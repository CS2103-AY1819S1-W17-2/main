package seedu.address.model.credential;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Credential in the credential store.
 * Guarantees: details are present and not null, no duplicates in credential
 * store.
 */
public class Credential {

    // Identity Field
    private final Username username;

    // Data Field
    private final Password password;
    private final String key; // TODO Awaiting Encryption function

    /**
     * Every field must be present and not null.
     */
    public Credential(Username username, Password password, String key) {
        requireAllNonNull(username, password, key);
        this.username = username;
        this.password = password;
        this.key = key;
    }

    /**
     * Modified Constructor specifically used for Login Procedure
     */
    public Credential(Username username, Password password) {
        requireAllNonNull(username, password);
        this.username = username;
        this.password = password;
        this.key = password.getValue();
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCredential(Credential otherCredential) {
        if (otherCredential == this) {
            return true;
        }

        return otherCredential != null
            && otherCredential.getUsername().equals(getUsername())
            && otherCredential.getPassword().equals(getPassword())
            && otherCredential.getKey().equals(getKey());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Credential)) {
            return false;
        }

        Credential otherCredential = (Credential) other;
        return otherCredential.getUsername().equals(getUsername())
            && otherCredential.getPassword().equals(getPassword())
            && otherCredential.getKey().equals(getKey());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(username, password, key);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Username: ")
            .append(getUsername().getUsername())
            .append(" Password: ")
            .append(getPassword().getValue())
            .append(" Key: ")
            .append(getKey());
        return builder.toString();
    }
}
