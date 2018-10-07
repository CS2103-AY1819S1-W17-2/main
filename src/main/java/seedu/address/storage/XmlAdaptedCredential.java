package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;

/**
 * JAXB-friendly version of the Credential.
 */
public class XmlAdaptedCredential {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Credential's "
        + "%s field is missing!";

    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String key;

    /**
     * Constructs an XmlAdaptedCredential.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedCredential() {}

    /**
     * Constructs an {@code XmlAdaptedCredential} with the given details.
     */
    public XmlAdaptedCredential(String username, String password, String key) {
        this.username = username;
        this.password = password;
        this.key = key;
    }

    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedCredential
     */
    public XmlAdaptedCredential(Credential source) {
        username = source.getUsername().toString();
        password = source.getPassword().toString();
        key = source.getKey();
    }


    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Credential toModelType() throws IllegalValueException {

        if (username == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Username"));
        }

        if (password == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Password"));
        }

        if (key == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Key"));
        }

        return new Credential(
            new Username(username),
            new Password(password),
            key);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedCredential)) {
            return false;
        }

        XmlAdaptedCredential otherCredential = (XmlAdaptedCredential) other;
        return Objects.equals(username, otherCredential.username)
            && Objects.equals(password, otherCredential.password)
            && Objects.equals(key, otherCredential.key);
    }
}
