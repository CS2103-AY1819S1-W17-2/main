package seedu.address.storage;

import java.util.List;

public class XmlAdaptedPrereq {
    private List<XmlAdaptedAnd> and;
    private List<XmlAdaptedOr> or;

    public List<XmlAdaptedAnd> getAnd() {
        return and;
    }

    public void setAnd(List<XmlAdaptedAnd> and) {
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
            returnVal = "And: " + and.toString();
        }
        if (or != null) {
            returnVal += ", Or: " + or.toString();
        }
        return returnVal;
    }
}
