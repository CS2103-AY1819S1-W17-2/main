package seedu.address.logic.parser;

import seedu.address.logic.commands.AddOnCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new AddOnCommand object
 */
public class AddOnCommandParser implements Parser<AddOnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOnCommand
     * and returns an AddOnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOnCommand parse(String args) throws ParseException {
        String inputModuleCode = args.toUpperCase().trim();

        if (inputModuleCode.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOnCommand.MESSAGE_USAGE));
        }

        Module module = new Module(inputModuleCode);
        return new AddOnCommand(module);
    }

}
