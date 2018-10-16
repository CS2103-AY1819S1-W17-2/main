package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the application
 */
public class Code {

    public static final String MESSAGE_CODE_CONSTRAINTS =
            "Code should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = ".*";

    public final String code;

    /**
     * Constructs a {@code Code}.
     *
     * @param code A valid code.
     */
    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CODE_CONSTRAINTS);
        this.code = code;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCode(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Code // instanceof handles nulls
                && code.equals(((Code) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
