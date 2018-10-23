package seedu.modsuni.model.module;

import java.util.List;
import java.util.Optional;

/**
 * Encapsulates the prereqs data for a module.
 */
public class Prereq {
    private Optional<List<PrereqOr>> or;
    private Optional<List<PrereqAnd>> and;

    public Optional<List<PrereqOr>> getOr() {
        return or;
    }

    public void setOr(Optional<List<PrereqOr>> or) {
        this.or = or;
    }

    public Optional<List<PrereqAnd>> getAnd() {
        return and;
    }

    public void setAnd(Optional<List<PrereqAnd>> and) {
        this.and = and;
    }

    /**
     * Returns the total number of or prereqs.
     */
    public int noOfOrPrereq() {
        return or.map(o -> o.size()).orElse(0);
    }

    /**
     * Returns the total number of and prereqs.
     */
    public int noOfAndPrereq() {
        return and.map(o -> o.size()).orElse(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prereq - ");

        return sb.toString();
    }
}
