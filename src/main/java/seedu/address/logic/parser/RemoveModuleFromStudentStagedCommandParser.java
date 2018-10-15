package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemoveModuleFromStudentStagedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Prereq;

import java.util.ArrayList;


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

        Module module = new Module(new Code(inputModuleCode), "", "", "", 0, false, false, false, false,
                new ArrayList<Code>(), new Prereq());
        return new RemoveModuleFromStudentStagedCommand(module);
    }

}
