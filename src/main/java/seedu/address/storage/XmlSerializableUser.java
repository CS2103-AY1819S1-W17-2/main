package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.user.User;


/**
 * An Immutable User that is serializable to XML format
 */
@XmlRootElement(name = "user")
public class XmlSerializableUser {

    @XmlElement
    private XmlAdapterUser user;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableUser() {
    }

    /**
     * Conversion
     */
    public XmlSerializableUser(User user) {
        this.user = new XmlAdapterUser(user);
    }

    /**
     * Converts this XmlUser into the model's {@code User}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     *                               {@code XmlAdaptedUser}.
     */
    public User toModelType() throws IllegalValueException {
        return this.user.toModelType();
    }
}
