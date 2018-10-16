package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleList;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Prereq;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS1010 = new ModuleBuilder().withCode(new Code("CS1010"))
            .withDepartment("Computer Science").withTitle("Programming Methodology")
            .withDescription("This module introduces the fundamental "
                    + "concepts of problem solving by computing and programming using an imperative "
                    + "programming language. It is the first and foremost introductory course to computing. "
                    + "It is also the first part of a three-part series on introductory programming and "
                    + "problem solving by computing, which also includes CS1020 and CS2010. Topics covered "
                    + "include problem solving by computing, writing pseudo-codes, basic problem formulation "
                    + "and problem solving, program development, coding, testing and debugging, fundamental "
                    + "programming constructs (variables, types, expressions, assignments, functions, control"
                    + " structures, etc.), fundamental data structures: arrays, strings and structures, "
                    + "simple file processing, and basic recursion. This module is appropriate for SoC "
                    + "students.").withCredit(4).withIsAvailableInSem1(true).withIsAvailableInSem2(true)
            .withisAvailableInSpecialTerm1(false).withisAvailableInSpecialTerm2(false)
            .withLockedModules(new ArrayList<Code>()).withPrereq(new Prereq()).build();

    public static final Module ACC1002 = new ModuleBuilder().withCode(new Code("ACC1002"))
            .withDepartment("Accounting").withTitle("Financial Accounting")
            .withDescription("The course provides an introduction to financial accounting. It examines "
                    + "accounting from an external user's perspective: an external user being an investor "
                    + "or a creditor. Such users would need to understand financial accounting in order to "
                    + "make investing or lending decisions. However, to attain a good understanding, it is "
                    + "also necessary to be familiar with how the information is derived. Therefore, "
                    + "students would learn how to prepare the reports or statements resulting from "
                    + "financial accounting and how to use them for decision-making.")
            .withCredit(4).withIsAvailableInSem1(true).withIsAvailableInSem2(false)
            .withisAvailableInSpecialTerm1(false).withisAvailableInSpecialTerm2(false)
            .withLockedModules(new ArrayList<Code>()).withPrereq(new Prereq()).build();

    public static final Module ACC1002X = new ModuleBuilder().withCode(new Code("ACC1002X"))
            .withDepartment("Accounting").withTitle("Financial Accounting")
            .withDescription("The course provides an introduction to financial accounting. It examines "
                    + "accounting from an external user's perspective: an external user being an investor or "
                    + "a creditor. Such users would need to understand financial accounting in order to "
                    + "make investing or lending decisions. However, to attain a good understanding, it is"
                    + " also necessary to be familiar with how the information are derived. Therefore, "
                    + "students would learn how to prepare the reports or statements resulting from "
                    + "financial accounting and how to use them for decision-making.")
            .withCredit(4).withIsAvailableInSem1(false).withIsAvailableInSem2(false)
            .withisAvailableInSpecialTerm1(false).withisAvailableInSpecialTerm2(false)
            .withLockedModules(new ArrayList<Code>()).withPrereq(new Prereq()).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code ModuleList} with all the typical modules.
     */
    public static ModuleList getTypicalModuleList() {
        ModuleList moduleList = new ModuleList();
        for (Module module : getTypicalModules()) {
            moduleList.addModule(module);
        }
        return moduleList;
    }
    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(ACC1002, ACC1002X));
    }
}
