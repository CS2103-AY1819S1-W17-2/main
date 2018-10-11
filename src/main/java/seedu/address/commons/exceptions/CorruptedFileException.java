package seedu.address.commons.exceptions;

/**
 * Signals that the encrypted file may be corrupted
 */
public class CorruptedFileException extends Exception {
    public CorruptedFileException(String message) {
        super(message);
    }
}
