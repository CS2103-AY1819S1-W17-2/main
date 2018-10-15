package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

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
