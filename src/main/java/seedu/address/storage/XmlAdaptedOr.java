package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly version of the Or Prereq.
 */
public class XmlAdaptedOr {

    private List<String> or;
    private List<XmlAdaptedAnd> and;

    public XmlAdaptedOr() { }

    public XmlAdaptedOr(ArrayList<String> or) {
        this.or = or;
    }

    @Override
    public String toString() {
        String returnVal = "XMLOR - ";
        if (or != null) {
            returnVal = "or: " + or.toString();
        }
        if (and != null) {
            returnVal += ", and: " + and.toString();
        }
        return returnVal;
    }
}
