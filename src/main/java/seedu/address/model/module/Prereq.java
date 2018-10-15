package seedu.address.model.module;

import java.util.HashSet;
import java.util.List;

public class Prereq {
    private List<Code> andCodes;
    private List<Code> orCodes;

    public List<Code> getAndCodes() {
        return andCodes;
    }

    public void setAndCodes(List<Code> andCodes) {
        this.andCodes = andCodes;
    }

    public List<Code> getOrCodes() {
        return orCodes;
    }

    public void setOrCodes(List<Code> orCodes) {
        this.orCodes = orCodes;
    }

    public Prereq(List<Code> andCodes, List<Code> orCodes) {
        this.andCodes = andCodes;
        this.orCodes = orCodes;
    }

    public Prereq() {
    }

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

    public int noOfOrPrereq() {
        return orCodes.size();
    }

    public int noOfAndPrereq() {
        return andCodes.size();
    }

    @Override
    public String toString() {
        return "Prereq - or: " + orCodes.toString() + ", and: " + andCodes.toString();
    }
}
