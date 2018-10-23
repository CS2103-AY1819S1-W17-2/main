package seedu.modsuni.commons.events.ui;

import seedu.modsuni.commons.events.BaseEvent;

/**
 * An event requesting to view the Database Tab
 */
public class ShowDatabaseTabRequestEvent extends BaseEvent {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
