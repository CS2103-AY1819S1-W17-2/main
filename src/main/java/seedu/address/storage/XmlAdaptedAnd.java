package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;

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
