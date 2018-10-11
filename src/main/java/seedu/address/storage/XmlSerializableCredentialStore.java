package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.CredentialStore;
import seedu.address.model.credential.ReadOnlyCredentialStore;


/**
 * An Immutable CredentialStore that is serializable to XML format
 */
@XmlRootElement(name = "credentialstore")
public class XmlSerializableCredentialStore {

    public static final String MESSAGE_DUPLICATE_CREDENTIAL = "Credential Store "
        + "contains duplicate user(s).";

    @XmlElement
    private List<XmlAdaptedCredential> credentials;

    /**
     * Creates an empty XmlSerializableCredentialStore.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableCredentialStore() {
        credentials = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableCredentialStore(ReadOnlyCredentialStore src) {
        this();
        credentials.addAll(src.getCredentials()
            .stream()
            .map(XmlAdaptedCredential::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this XmlCredentialStore into the model's {@code CredentialStore}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     *                               {@code XmlAdaptedUserCredentials}.
     */
    public CredentialStore toModelType() throws IllegalValueException {
        CredentialStore credentialStore = new CredentialStore();
        for (XmlAdaptedCredential a : credentials) {
            Credential credential = a.toModelType();
            if (credentialStore.hasCredential(credential)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CREDENTIAL);
            }
            credentialStore.addCredential(credential);
        }
        return credentialStore;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableCredentialStore)) {
            return false;
        }
        return credentials.equals(((XmlSerializableCredentialStore) other).credentials);
    }
}
