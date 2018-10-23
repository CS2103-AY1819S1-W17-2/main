package seedu.modsuni.model;

import javafx.collections.ObservableList;
import seedu.modsuni.model.person.Person;

/**
 * Unmodifiable view of an modsuni book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
