package seedu.modsuni.commons.events.model;

import java.nio.file.Path;

import seedu.modsuni.commons.events.BaseEvent;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.user.User;

/** Indicates the User in the model has changed*/
public class SaveUserChangedEvent extends BaseEvent {

    public final User user;
    public final Password password;
    public final Path filePath;

    public SaveUserChangedEvent(User user, Password password, Path filePath) {
        this.user = user;
        this.password = password;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Current user: " + user.getName();
    }
}
