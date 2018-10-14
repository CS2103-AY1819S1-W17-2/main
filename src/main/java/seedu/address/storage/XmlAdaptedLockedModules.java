package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Code;

import javax.xml.bind.annotation.XmlValue;

public class XmlAdaptedLockedModules {

    @XmlValue
    private String lockedModuleCode;

    public XmlAdaptedLockedModules() { }

    public XmlAdaptedLockedModules(String lockedModuleCode) {
        this.lockedModuleCode = lockedModuleCode;
    }

    public XmlAdaptedLockedModules(Code source) {
        lockedModuleCode = source.code;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Code toModelType() throws IllegalValueException {
        if (!Code.isValidCode(lockedModuleCode)) {
            throw new IllegalValueException(Code.MESSAGE_CODE_CONSTRAINTS);
        }
        return new Code(lockedModuleCode);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedTag)) {
            return false;
        }

        return lockedModuleCode.equals(((XmlAdaptedLockedModules) other).lockedModuleCode);
    }
}
