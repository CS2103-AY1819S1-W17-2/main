package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.modsuni.logic.commands.RemoveUserCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.credential.Username;

/**
 * Parses input arguments and creates a new RemoveUserCommand.
 */
public class RemoveUserCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveModuleFromDatabaseCommand
     * and returns a RemoveModuleFromDatabaseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveUserCommand parse(String args) throws ParseException {
        try {
            Username username = ParserUtil.parseUsername(args);
            return new RemoveUserCommand(username);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveUserCommand.MESSAGE_USAGE));
        }
    }
}
