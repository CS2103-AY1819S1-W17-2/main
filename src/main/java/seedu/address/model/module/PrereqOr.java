package seedu.address.model.module;

import java.util.Arrays;

/**
 * Encapsulates the Or type of prereq data.
 */
public class PrereqOr {
    private PrereqAnd[] and;

    public PrereqAnd[] getAnd () {
        return and;
    }

    public void setAnd (PrereqAnd[] and) {
        this.and = and;
    }

    @Override
    public String toString() {
        if (and != null) {
            return Arrays.toString(and);
        } else {
            return "";
        }
    }
}
