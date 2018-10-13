package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.module.CodeStartsKeywordsPredicate;

/**
 * Finds and lists all modules in the user's profile whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all modules whose codes begin with any of "
            + "the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " CS101";

    private final CodeStartsKeywordsPredicate predicate;

    public SearchCommand(CodeStartsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)); // state check
    }
}
