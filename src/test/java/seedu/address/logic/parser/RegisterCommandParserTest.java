package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENROLLMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOGIN_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOGIN_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REGISTER_ENROLLMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REGISTER_MAJOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REGISTER_MINOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REGISTER_PATH_TO_PIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENROLLMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MINOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATH_TO_PIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.model.user.student.EnrollmentDate;
import seedu.address.model.user.student.Student;

public class RegisterCommandParserTest {
    private RegisterCommandParser parser = new RegisterCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        String userInput = LOGIN_USERNAME_DESC
            + LOGIN_PASSWORD_DESC
            + NAME_DESC_AMY
            + REGISTER_PATH_TO_PIC_DESC
            + REGISTER_ENROLLMENT_DESC
            + REGISTER_MAJOR_DESC
            + REGISTER_MINOR_DESC;

        Credential toVerify = new Credential(
            ParserUtil.parseUsername(VALID_USERNAME),
            ParserUtil.parsePassword(VALID_PASSWORD));

        User newUser = new Student(
            ParserUtil.parseUsername(VALID_USERNAME),
            ParserUtil.parseName(VALID_NAME_AMY),
            Role.STUDENT,
            ParserUtil.parsePathToProfilePic(VALID_PATH_TO_PIC),
            ParserUtil.parseEnrollmentDate(VALID_ENROLLMENT),
            Arrays.asList(VALID_MAJOR),
            Arrays.asList(VALID_MINOR));

        RegisterCommand expectedCommand = new RegisterCommand(toVerify, newUser);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RegisterCommand.MESSAGE_USAGE);

        // missing username prefix
        assertParseFailure(parser,
            VALID_USERNAME
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing password prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + VALID_PASSWORD
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing name prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + VALID_NAME_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing pic prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + VALID_PATH_TO_PIC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing enrollment date prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + VALID_ENROLLMENT
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing major prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + VALID_MAJOR
                + REGISTER_MINOR_DESC,
            expectedMessage);

        // missing minor prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + VALID_MINOR,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_USERNAME
                + VALID_PASSWORD
                + VALID_NAME_AMY
                + VALID_PATH_TO_PIC
                + VALID_ENROLLMENT
                + VALID_MAJOR
                + VALID_MINOR,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid username
        assertParseFailure(parser,
            INVALID_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            Username.MESSAGE_USERNAME_CONSTRAINTS);

        // invalid password
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + INVALID_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            Password.MESSAGE_PASSWORD_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + INVALID_NAME_DESC
                + REGISTER_PATH_TO_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            Name.MESSAGE_NAME_CONSTRAINTS);

        // invalid pic
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + INVALID_PIC_DESC
                + REGISTER_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            PathToProfilePic.MESSAGE_PATH_CONSTRAINTS);

        // invalid enrollment date
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC
                + LOGIN_PASSWORD_DESC
                + NAME_DESC_AMY
                + REGISTER_PATH_TO_PIC_DESC
                + INVALID_ENROLLMENT_DESC
                + REGISTER_MAJOR_DESC
                + REGISTER_MINOR_DESC,
            EnrollmentDate.MESSAGE_DATE_CONSTRAINTS);

        //TODO invalid major & minor
    }
}
