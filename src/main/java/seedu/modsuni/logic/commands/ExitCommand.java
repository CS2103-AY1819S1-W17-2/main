package seedu.modsuni.logic.commands;

import seedu.modsuni.commons.core.EventsCenter;
import seedu.modsuni.commons.events.ui.ExitAppRequestEvent;
import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        EventsCenter.getInstance().post(new ExitAppRequestEvent());
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
