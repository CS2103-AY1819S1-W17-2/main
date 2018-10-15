package seedu.address.commons.events.model;

import java.nio.file.Path;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.user.User;

/** Indicates the User in the model has changed*/
public class SaveUserChangedEvent extends BaseEvent {

    public final User user;
    public final Path filePath;

    public SaveUserChangedEvent(User user, Path filePath) {
        this.user = user;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Current user: " + user.getName();
    }
}
