package seedu.modsuni.commons.exceptions;

/**
 * Signals that there an error occurred during decryption
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
