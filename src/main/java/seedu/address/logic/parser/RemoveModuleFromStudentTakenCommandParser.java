package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveModuleFromStudentTakenCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Prereq;

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

        String[] searchList = inputModuleCode.split(" ");
        Stream<String> searchStream = Arrays.stream(searchList);
        Stream<Module> searchModuleStream = searchStream.map(code -> new Module(new Code(code),
                "", "", "", 0, false, false, false, false,
                new ArrayList<Code>(), new Prereq()));

        return new RemoveModuleFromStudentTakenCommand(searchModuleStream.collect
                (Collectors.toCollection(ArrayList::new)));

    }

}
