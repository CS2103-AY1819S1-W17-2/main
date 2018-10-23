package seedu.modsuni.logic;

import javafx.collections.ObservableList;
import seedu.modsuni.logic.commands.CommandResult;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.module.Module;
import seedu.modsuni.model.person.Person;
import seedu.modsuni.model.user.User;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /** Returns a view of the current user */
    User getCurrentUser();

    /** Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object */
    ListElementPointer getHistorySnapshot();
}
