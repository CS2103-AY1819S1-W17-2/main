package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.modsuni.logic.commands.RemoveModuleFromStudentTakenCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.Module;
import seedu.modsuni.model.module.Prereq;

/**
 * Parses input arguments and creates a new RemoveModuleFromStudentTakenCommand object
 */
public class RemoveModuleFromStudentTakenCommandParser implements Parser<RemoveModuleFromStudentTakenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveModuleFromStudentTakenCommand
     * and returns an RemoveModuleFromStudentTakenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveModuleFromStudentTakenCommand parse(String args) throws ParseException {
        String inputModuleCode = args.toUpperCase().trim();

        if (inputModuleCode.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveModuleFromStudentTakenCommand.MESSAGE_USAGE));
        }

        Module module = new Module(new Code(inputModuleCode), "", "", "", 0, false, false, false, false,
                new ArrayList<Code>(), new Prereq());
        return new RemoveModuleFromStudentTakenCommand(module);
    }

}
