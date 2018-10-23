package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.modsuni.logic.commands.AddAdminCommand;
import seedu.modsuni.logic.commands.AddCommand;
import seedu.modsuni.logic.commands.AddModuleToDatabaseCommand;
import seedu.modsuni.logic.commands.AddModuleToStudentStagedCommand;
import seedu.modsuni.logic.commands.AddModuleToStudentTakenCommand;
import seedu.modsuni.logic.commands.ClearCommand;
import seedu.modsuni.logic.commands.Command;
import seedu.modsuni.logic.commands.DeleteCommand;
import seedu.modsuni.logic.commands.EditStudentCommand;
import seedu.modsuni.logic.commands.ExitCommand;
import seedu.modsuni.logic.commands.FindCommand;
import seedu.modsuni.logic.commands.GenerateCommand;
import seedu.modsuni.logic.commands.HelpCommand;
import seedu.modsuni.logic.commands.HistoryCommand;
import seedu.modsuni.logic.commands.ListCommand;
import seedu.modsuni.logic.commands.LoginCommand;
import seedu.modsuni.logic.commands.RedoCommand;
import seedu.modsuni.logic.commands.RegisterCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromDatabaseCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromStudentStagedCommand;
import seedu.modsuni.logic.commands.RemoveModuleFromStudentTakenCommand;
import seedu.modsuni.logic.commands.RemoveUserCommand;
import seedu.modsuni.logic.commands.SaveCommand;
import seedu.modsuni.logic.commands.SearchCommand;
import seedu.modsuni.logic.commands.SelectCommand;
import seedu.modsuni.logic.commands.UndoCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ModsUniParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddModuleToStudentTakenCommand.COMMAND_WORD:
            return new AddModuleToStudentTakenCommandParser().parse(arguments);

        case AddModuleToStudentStagedCommand.COMMAND_WORD:
            return new AddModuleToStudentStagedCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case SaveCommand.COMMAND_WORD:
            return new SaveCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case RemoveModuleFromStudentTakenCommand.COMMAND_WORD:
            return new RemoveModuleFromStudentTakenCommandParser().parse(arguments);

        case RemoveModuleFromStudentStagedCommand.COMMAND_WORD:
            return new RemoveModuleFromStudentStagedCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case GenerateCommand.COMMAND_WORD:
            return new GenerateCommand();

        case RegisterCommand.COMMAND_WORD:
            return new RegisterCommandParser().parse(arguments);

        case AddAdminCommand.COMMAND_WORD:
            return new AddAdminCommandParser().parse(arguments);
        case RemoveUserCommand.COMMAND_WORD:
            return new RemoveUserCommandParser().parse(arguments);

        case AddModuleToDatabaseCommand.COMMAND_WORD:
            return new AddModuleToDatabaseCommandParser().parse(arguments);

        case RemoveModuleFromDatabaseCommand.COMMAND_WORD:
            return new RemoveModuleFromDatabaseCommandParser().parse(arguments);

        case LoginCommand.COMMAND_WORD:
            return new LoginCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
