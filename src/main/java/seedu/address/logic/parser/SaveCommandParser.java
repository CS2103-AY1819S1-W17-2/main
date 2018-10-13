package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SAVE_PATH;

import java.nio.file.Path;
import java.util.stream.Stream;

import seedu.address.logic.commands.SaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SaveCommand object
 */
public class SaveCommandParser implements Parser<SaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SaveCommand
     * and returns an SaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SAVE_PATH);

        if (!arePrefixesPresent(argMultimap, PREFIX_SAVE_PATH)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
        }

        Path savePath = ParserUtil.parsePath(argMultimap.getValue(PREFIX_SAVE_PATH).get());

        return new SaveCommand(savePath);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
