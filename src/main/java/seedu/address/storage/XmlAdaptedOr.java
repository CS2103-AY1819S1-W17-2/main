package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class XmlAdaptedOr {
    
    private String or;
    private List<XmlAdaptedAnd> and;

    public XmlAdaptedOr(String or) {
        this.or = or;
    }

    public XmlAdaptedOr() {
    }

    public XmlAdaptedOr(String or, List<XmlAdaptedAnd> and) {
        this.or = or;
        this.and = and;
    }

    public String getOr() {
        return or;
    }

    public void setOr(String or) {
        this.or = or;
    }

    public List<XmlAdaptedAnd> getAnd() {
        return and;
    }

    public void setAnd(List<XmlAdaptedAnd> and) {
        this.and = and;
    }

    @Override
    public String toString() {
        String returnVal = "";
        if (or != null) {
            returnVal = "or: " + or;
        }
        if (and != null) {
            returnVal += ", and: " + and.toString();
        }
        return returnVal;
    }
}
