package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH_TO_PIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ENROLLMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_MINOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.student.EnrollmentDate;
import seedu.address.model.user.student.Student;
import seedu.address.model.user.User;

/**
 * Parses input arguments and creates a new RegisterCommand object
 */
public class RegisterCommandParser implements Parser<RegisterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * RegisterCommand and returns an RegisterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RegisterCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_USERNAME,
                PREFIX_PASSWORD, PREFIX_NAME, PREFIX_PATH_TO_PIC,
                PREFIX_STUDENT_ENROLLMENT_DATE, PREFIX_STUDENT_MAJOR, PREFIX_STUDENT_MINOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_USERNAME, PREFIX_PASSWORD,
            PREFIX_NAME, PREFIX_PATH_TO_PIC)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RegisterCommand.MESSAGE_USAGE));
        }

        Username username = ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get());
        Password password = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());
        Name name = new Name(argMultimap.getValue(PREFIX_NAME).get());
        PathToProfilePic pathToPic =
            new PathToProfilePic(argMultimap.getValue(PREFIX_PATH_TO_PIC).get());
        EnrollmentDate enrollmentDate = new EnrollmentDate(
            argMultimap.getValue(PREFIX_STUDENT_ENROLLMENT_DATE).get());
        List<String> majors = argMultimap.getAllValues(PREFIX_STUDENT_MAJOR);
        List<String> minors = argMultimap.getAllValues(PREFIX_STUDENT_MINOR);
        User newUser = new Student(username, name, Role.STUDENT, pathToPic,
            enrollmentDate, majors, minors);

        //TODO key to be replaced
        Credential credential = new Credential(
            username,
            password,
            password.getValue());

        return new RegisterCommand(credential, newUser);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
