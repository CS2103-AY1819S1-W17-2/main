package seedu.modsuni.commons.events.ui;

import seedu.modsuni.commons.events.BaseEvent;

/**
 * An event requesting to view the User Tab
 */
public class ShowUserTabRequestEvent extends BaseEvent {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
