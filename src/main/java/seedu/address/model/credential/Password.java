package seedu.address.model.credential;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a User's Password.
 */
public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "Password "
        + "should be of the format alphanumeric "
        + "and adhere to the following constraints:\n"
        + "1. Password should contain alphanumeric characters and include "
        + "these special characters";
    // alphanumeric and special characters

    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*\\d)(?=.*[a-z])"
        + "(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    private String value;

    /**
     * Constructs an {@code Password}.
     *
     * @param password A valid password string.
     */
    public Password(String password) {
        requireNonNull(password);
        value = password;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidPassword(String test) {
        return test.matches(PASSWORD_VALIDATION_REGEX);
    }

    /**
     * Converts byte[] into a hexadecimal string
     *
     * @param bytes
     * @return hexadecimal string of hash
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Returns the hash value of the password
     *
     * @return hash
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Returns true if both passwords have the same hash value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Password)) {
            return false;
        }

        Password otherPassword = (Password) other;
        return otherPassword.getValue().equals(getValue());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return getValue();
    }
}
