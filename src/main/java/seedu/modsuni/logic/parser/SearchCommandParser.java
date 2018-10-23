package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.modsuni.logic.commands.SearchCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.module.CodeStartsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns an SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.toUpperCase().split("\\s+");

        return new SearchCommand(new CodeStartsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
