package seedu.address.model.module;

import java.util.Arrays;

public class Prereq {
    private PrereqOr[] or;
    private PrereqAnd[] and;

    public PrereqOr[] getOr() {
        return or;
    }

    public void setOr(PrereqOr[] or) {
        this.or = or;
    }

    public PrereqAnd[] getAnd() {
        return and;
    }

    public void setAnd(PrereqAnd[] and) {
        this.and = and;
    }

    @Override
    public String toString() {
        return "Prereq - or: " + Arrays.toString(or) + ", and: " + Arrays.toString(and);
    }
}
