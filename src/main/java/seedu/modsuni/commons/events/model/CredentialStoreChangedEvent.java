package seedu.modsuni.commons.events.model;

import seedu.modsuni.commons.events.BaseEvent;
import seedu.modsuni.model.credential.ReadOnlyCredentialStore;

/** Indicates the CredentialStore in the model has changed*/
public class CredentialStoreChangedEvent extends BaseEvent {

    public final ReadOnlyCredentialStore data;

    public CredentialStoreChangedEvent(ReadOnlyCredentialStore data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "No. of Credentials: " + data.getCredentials().size();
    }
}
