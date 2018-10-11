package seedu.address.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * The salary of the employed administrator.
 */
public class Salary {

    public static final String MESSAGE_SALARY_CONSTRAINTS =
            "Salary should only contain numeric characters";

    public static final String SALARY_VALIDATION_REGEX = "\\p{Digit}++";

    public final String salary;

    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_SALARY_CONSTRAINTS);
        this.salary = salary;
    }

    public static boolean isValidSalary(String test) {
        return test.matches(SALARY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return salary;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && salary.equals(((Salary) other).salary)); // state check
    }

    @Override
    public int hashCode() {
        return salary.hashCode();
    }
}
