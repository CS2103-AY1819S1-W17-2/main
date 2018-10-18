package seedu.modsuni.logic.commands;

import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modsuni.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modsuni.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;
import static seedu.modsuni.testutil.TypicalCredentials.getTypicalCredentialStore;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import seedu.modsuni.logic.CommandHistory;
import seedu.modsuni.model.AddressBook;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.Role;
import seedu.modsuni.model.user.User;
import seedu.modsuni.model.user.student.EnrollmentDate;
import seedu.modsuni.model.user.student.Student;
import seedu.modsuni.testutil.CredentialBuilder;
import seedu.modsuni.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * RegisterCommand}.
 */
public class LoginCommandIntegrationTest {

    private static Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore());
        model.setCurrentUser(new StudentBuilder().build());
    }

    @Test
    public void execute_loginSuccess() {
        Credential toVerify = CREDENTIAL_STUDENT_SEB;

        Model expectedModel = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore());

        User loginUser = new Student(
            toVerify.getUsername(),
            new Name("dummy"),
            Role.STUDENT,
            new PathToProfilePic("dummy.img"),
            new EnrollmentDate("08/08/2018"),
            Arrays.asList("CS"),
            Arrays.asList("MA"));
        expectedModel.setCurrentUser(loginUser);

        assertCommandSuccess(new LoginCommand(toVerify), model,
            commandHistory,
            String.format(LoginCommand.MESSAGE_SUCCESS,
                toVerify.getUsername().toString()),
            expectedModel);
    }

    @Test
    public void execute_loginFailure() {
        Credential toVerify = new CredentialBuilder(CREDENTIAL_STUDENT_SEB)
            .withPassword("incorrectPassword")
            .build();
        assertCommandFailure(new LoginCommand(toVerify),
            model,
            commandHistory,
            LoginCommand.MESSAGE_LOGIN_FAILURE);
    }


}
