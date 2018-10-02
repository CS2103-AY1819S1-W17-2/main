package seedu.address.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Admin's employment date;
 */
public class EmployDate {

    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Employment date should be in this format: DD/MM/YYYY";

    public static final String DATE_VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}$";

    public final String employDate;

    public EmployDate(String employDate) {
        requireNonNull(employDate);
        checkArgument(isValidEmployDate(employDate), MESSAGE_DATE_CONSTRAINTS);
        this.employDate = employDate;
    }

    public static boolean isValidEmployDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return employDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployDate // instanceof handles nulls
                && employDate.equals(((EmployDate) other).employDate)); // state check
    }

    @Override
    public int hashCode() {
        return employDate.hashCode();
    }
}
