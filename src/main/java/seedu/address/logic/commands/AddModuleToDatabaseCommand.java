package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_AVAILABLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TITLE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a new module to database.
 */
public class AddModuleToDatabaseCommand extends Command {
    public static final String COMMAND_WORD = "addModuleDB";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new module to database. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_MODULE_DEPARTMENT + "DEPARTMENT "
            + PREFIX_MODULE_TITLE + "TITLE "
            + PREFIX_MODULE_DESCRIPTION + "DESCRIPTION "
            + PREFIX_MODULE_CREDIT + "CREDIT "
            + PREFIX_MODULE_AVAILABLE + "[Sem1][Sem2][SpecialTerm1][SpecialTerm2] "
            + "(for each entry enter '1' if available, '0' if not)\n"
            + "Example " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2109 "
            + PREFIX_MODULE_DEPARTMENT + "SOC "
            + PREFIX_MODULE_TITLE + "The new module "
            + PREFIX_MODULE_DESCRIPTION + "This is a new module that will be offered starting from this semester "
            + PREFIX_MODULE_CREDIT + "4 "
            + PREFIX_MODULE_AVAILABLE + "1100\n";

    public static final String MESSAGE_SUCCESS = "New module added to database";
    public static final String MESSAGE_NOT_ADMIN = "Only an admin user can execute this command";


    private final Module toAdd;

    public AddModuleToDatabaseCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isAdmin()) {
            throw new CommandException(MESSAGE_NOT_ADMIN);
        }

        model.addModuleToDatabase(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
