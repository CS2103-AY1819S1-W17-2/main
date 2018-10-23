package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_DATE;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_PATH_TO_PIC;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.modsuni.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.util.stream.Stream;

import seedu.modsuni.logic.commands.AddAdminCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;
import seedu.modsuni.model.user.Admin;
import seedu.modsuni.model.user.EmployDate;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.Role;
import seedu.modsuni.model.user.Salary;

/**
 * Parses input arguments and creates a new AddAdminCommand object
 */
public class AddAdminCommandParser implements Parser<AddAdminCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAdminCommand
     * and returns an AddAdminCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAdminCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_USERNAME,
                        PREFIX_PASSWORD, PREFIX_PATH_TO_PIC , PREFIX_SALARY, PREFIX_EMPLOYMENT_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_USERNAME, PREFIX_PASSWORD, PREFIX_SALARY, PREFIX_EMPLOYMENT_DATE,
                PREFIX_NAME, PREFIX_PATH_TO_PIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAdminCommand.MESSAGE_USAGE));
        }


        //Admin Credential
        Username username = ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get());
        Password password =
            ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());

        //Admin info
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Role role = Role.ADMIN;
        PathToProfilePic pathToProfilePic =
                ParserUtil.parsePathToProfilePic(argMultimap.getValue(PREFIX_PATH_TO_PIC).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
        EmployDate employmentDate = ParserUtil.parseEmployDate(argMultimap.getValue(PREFIX_EMPLOYMENT_DATE).get());
        Admin admin = new Admin(username, name, role, pathToProfilePic, salary,
            employmentDate);
        Credential credential = new Credential(
            username,
            password,
            password.getValue());
        return new AddAdminCommand(admin, credential);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
