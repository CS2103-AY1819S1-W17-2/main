package seedu.address.storage;

import java.util.List;

/**
 * JAXB-friendly version of the And Prereq.
 */
public class XmlAdaptedAnd {

    private String and;
    private List<XmlAdaptedOr> or;

    public String getAnd() {
        return and;
    }

    public void setAnd(String and) {
        this.and = and;
    }

    public List<XmlAdaptedOr> getOr() {
        return or;
    }

    public void setOr(List<XmlAdaptedOr> or) {
        this.or = or;
    }

    @Override
    public String toString() {
        String returnVal = "";
        if (and != null) {
            returnVal = "And: " + and;
        }
        if (or != null) {
            returnVal += ", Or: " + or.toString();
        }
        return returnVal;
    }
}
