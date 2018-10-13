package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemoveModuleFromStudentStagedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;


/**
 * Parses input arguments and creates a new RemoveModuleFromStudentTakenCommand object
 */
public class RemoveModuleFromStudentStagedCommandParser implements Parser<RemoveModuleFromStudentStagedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveModuleFromStudentTakenCommand
     * and returns an RemoveModuleFromStudentTakenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveModuleFromStudentStagedCommand parse(String args) throws ParseException {
        String inputModuleCode = args.toUpperCase().trim();

        if (inputModuleCode.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveModuleFromStudentStagedCommand.MESSAGE_USAGE));
        }

        Module module = new Module(inputModuleCode);
        return new RemoveModuleFromStudentStagedCommand(module);
    }

}
