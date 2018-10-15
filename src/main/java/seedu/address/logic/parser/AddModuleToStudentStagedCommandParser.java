package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddModuleToStudentStagedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;


/**
 * Parses input arguments and creates a new AddModuleToStudentTakenCommand object
 */
public class AddModuleToStudentStagedCommandParser implements Parser<AddModuleToStudentStagedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleToStudentTakenCommand
     * and returns an AddModuleToStudentTakenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleToStudentStagedCommand parse(String args) throws ParseException {
        String inputModuleCode = args.toUpperCase().trim();

        if (inputModuleCode.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleToStudentStagedCommand.MESSAGE_USAGE));
        }

        Module module = new Module(inputModuleCode);
        return new AddModuleToStudentStagedCommand(module);
    }

}
