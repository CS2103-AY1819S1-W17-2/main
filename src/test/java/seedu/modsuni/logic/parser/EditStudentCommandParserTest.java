package seedu.modsuni.logic.parser;

import static seedu.modsuni.logic.commands.CommandTestUtil.*;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.modsuni.logic.commands.EditStudentCommand;
import seedu.modsuni.logic.commands.EditStudentCommand.EditStudentDescriptor;

import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.student.EnrollmentDate;
import seedu.modsuni.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {

    private EditStudentCommandParser parser = new EditStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = NAME_DESC_AMY
            + PATH_TO_PIC_DESC
            + ENROLLMENT_DESC
            + MAJOR_DESC
            + MINOR_DESC;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .withProfilePic(VALID_PATH_TO_PIC)
            .withEnrollmentDate(VALID_ENROLLMENT)
            .withMajors(Arrays.asList("CS"))
            .withMinors(Arrays.asList("MA"))
            .build();

        EditStudentCommand expectedCommand = new EditStudentCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsPresent_success() {
        String userInput = NAME_DESC_AMY
            + PATH_TO_PIC_DESC;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .withProfilePic(VALID_PATH_TO_PIC)
            .build();

        EditStudentCommand expectedCommand = new EditStudentCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsPresent_success() {
        String userInput = NAME_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .build();

        EditStudentCommand expectedCommand = new EditStudentCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidFields_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_NAME_CONSTRAINTS);
        assertParseFailure(parser, INVALID_PATH_DESC, PathToProfilePic.MESSAGE_PATH_CONSTRAINTS);
        assertParseFailure(parser, INVALID_ENROLLMENT_DESC, EnrollmentDate.MESSAGE_DATE_CONSTRAINTS);
    }

}
