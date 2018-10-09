package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_AVAILABLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddModuleToDatabaseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;


/**
 * The parser for AddModuleToDatabaseCommand.
 */
public class AddModuleToDatabaseCommandParser implements Parser<AddModuleToDatabaseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleToDatabaseCommand
     * and returns an AddModuleToDatabaseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleToDatabaseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_MODULE_DEPARTMENT, PREFIX_MODULE_TITLE,
                        PREFIX_MODULE_DESCRIPTION, PREFIX_MODULE_CREDIT, PREFIX_MODULE_AVAILABLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE, PREFIX_MODULE_DEPARTMENT, PREFIX_MODULE_TITLE,
                PREFIX_MODULE_DESCRIPTION, PREFIX_MODULE_CREDIT, PREFIX_MODULE_AVAILABLE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddModuleToDatabaseCommand.MESSAGE_USAGE));
        }

        String code = argMultimap.getValue(PREFIX_MODULE_CODE).get();
        String department = argMultimap.getValue(PREFIX_MODULE_DEPARTMENT).get();
        String title = argMultimap.getValue(PREFIX_MODULE_TITLE).get();
        String description = argMultimap.getValue(PREFIX_MODULE_DESCRIPTION).get();
        int credit = Integer.parseInt(argMultimap.getValue(PREFIX_MODULE_CREDIT).get());
        boolean[] sems = getAvailableSems(argMultimap.getValue(PREFIX_MODULE_AVAILABLE).get());

        Module module = new Module(code, department, title, description, credit,
                sems[0], sems[1], sems[2], sems[3]);

        return new AddModuleToDatabaseCommand(module);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Converts the given string of available sems into booleans.
     * @param sem
     * @return
     */
    private boolean[] getAvailableSems(String sem) {
        boolean[] sems = new boolean[4];

        for (int i = 0; i < sems.length; i++) {

            if (sem.charAt(i) == '1') {
                sems[i] = true;
            } else {
                sems[i] = false;
            }
        }

        return sems;
    }
}
