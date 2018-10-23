package seedu.modsuni.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.modsuni.commons.exceptions.IllegalValueException;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.PrereqAnd;
import seedu.modsuni.model.module.PrereqOr;

/**
 * JAXB-friendly version of the Or Prereq.
 */
@XmlRootElement(name = "or")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAdaptedOr {
    @XmlElement
    private List<XmlAdaptedOr> or;
    @XmlElement
    private List<XmlAdaptedAnd> and;

    private String code;

    public String getCode() {
        return code;
    }

    /**
     * Converts this jaxb-friendly adapted PrereqAnd object into the model's Module object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted PrereqAnd code
     */
    public PrereqOr toModelType() throws IllegalValueException {
        PrereqOr prereqOr = new PrereqOr();
        if (code != null) {
            if (!Code.isValidCode(code)) {
                throw new IllegalValueException(Code.MESSAGE_CODE_CONSTRAINTS);
            }
            prereqOr.setCode(Optional.of(new Code(code)));
        }
        if (and != null) {
            ArrayList<PrereqAnd> prereqAnds = new ArrayList<>();
            for (XmlAdaptedAnd element : and) {
                prereqAnds.add(element.toModelType());
            }
            prereqOr.setAnd(Optional.of(prereqAnds));
        }
        if (or != null) {
            ArrayList<PrereqOr> prereqOrs = new ArrayList<>();
            for (XmlAdaptedOr element : or) {
                prereqOrs.add(element.toModelType());
            }
            prereqOr.setOr(Optional.of(prereqOrs));
        }
        return prereqOr;
    }

    @Override
    public String toString() {
        String returnVal = "";
        if (code != null) {
            returnVal += code;
        }
        if (and != null) {
            returnVal = "And: " + and.toString();
        }
        if (or != null) {
            returnVal += ", Or: " + or.toString();
        }
        return returnVal;
    }
}
