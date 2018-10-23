package seedu.modsuni.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modsuni.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.logic.commands.exceptions.CommandException;
import seedu.modsuni.model.Model;

/**
 * Reverts the {@code model}'s modsuni book to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoAddressBook();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
