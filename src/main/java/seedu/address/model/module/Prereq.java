package seedu.address.model.module;

import java.util.HashSet;
import java.util.List;

/**
 * Encapsulates the prereqs data for a module.
 */
public class Prereq {
    private List<Code> andCodes;
    private List<Code> orCodes;

    public Prereq(List<Code> andCodes, List<Code> orCodes) {
        this.andCodes = andCodes;
        this.orCodes = orCodes;
    }

    public Prereq() { }

    /**
     * Checks if or prereq has been taken before.
     */
    public boolean checkOrCodes(HashSet<Code> taken) {
        if (orCodes != null && orCodes.size() != 0) {
            for (Code code : orCodes) {
                if (taken.contains(code)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the total number of or prereqs.
     */
    public int noOfOrPrereq() {
        return orCodes.size();
    }

    /**
     * Returns the total number of and prereqs.
     */
    public int noOfAndPrereq() {
        return andCodes.size();
    }

    @Override
    public String toString() {
        return "Prereq - or: " + orCodes.toString() + ", and: " + andCodes.toString();
    }
}
