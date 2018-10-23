package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.modsuni.logic.commands.AddModuleToStudentTakenCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.module.Code;
import seedu.modsuni.model.module.Module;
import seedu.modsuni.model.module.Prereq;

/**
 * Parses input arguments and creates a new AddModuleToStudentTakenCommand object
 */
public class AddModuleToStudentTakenCommandParser implements Parser<AddModuleToStudentTakenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleToStudentTakenCommand
     * and returns an AddModuleToStudentTakenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleToStudentTakenCommand parse(String args) throws ParseException {
        String inputModuleCode = args.toUpperCase().trim();

        if (inputModuleCode.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleToStudentTakenCommand.MESSAGE_USAGE));
        }

        String[] searchList = inputModuleCode.split(" ");
        Stream<String> searchStream = Arrays.stream(searchList);
        Stream<Module> searchModuleStream = searchStream.map(code -> new Module(new Code(code),
                "", "", "", 0, false, false, false, false,
                new ArrayList<Code>(), new Prereq()));

        return new AddModuleToStudentTakenCommand(searchModuleStream.collect(Collectors.toCollection(ArrayList::new)));

    }

}
