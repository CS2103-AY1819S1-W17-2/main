package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Prereq;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {
    public static final Code DEFAULT_CODE = new Code("CS1010");
    public static final String DEFAULT_DEPARTMENT = "Computer Science";
    public static final String DEFAULT_TITLE = "Programming Methodology";
    public static final String DEFAULT_DESCRIPTION = "This module introduces the fundamental concepts of "
            + "problem solving by computing and programming using an imperative programming language. It "
            + "is the first and foremost introductory course to computing. It is also the first part of a "
            + "three-part series on introductory programming and problem solving by computing, which also "
            + "includes CS1020 and CS2010. Topics covered include problem solving by computing, writing "
            + "pseudo-codes, basic problem formulation and problem solving, program development, coding, "
            + "testing and debugging, fundamental programming constructs (variables, types, expressions, "
            + "assignments, functions, control structures, etc.), fundamental data structures: arrays, "
            + "strings and structures, simple file processing, and basic recursion. This module is "
            + "appropriate for SoC students.";
    public static final int DEFAULT_CREDIT = 4;
    public static final boolean DEFAULT_ISAVAILABLEINSEM1 = true;
    public static final boolean DEFAULT_ISAVAILABLEINSEM2 = true;
    public static final boolean DEFAULT_ISAVAILABLEINSPECIALTERM1 = false;
    public static final boolean DEFAULT_ISAVAILABLEINSPECIALTERM2 = false;
    public static final List<Code> DEFAULT_LOCKED_MODULES = new ArrayList<>();
    public static final Prereq DEFAULT_PREREQ = new Prereq();

    private Code code;
    private String department;
    private String title;
    private String description;
    private int credit;
    private boolean isAvailableInSem1;
    private boolean isAvailableInSem2;
    private boolean isAvailableInSpecialTerm1;
    private boolean isAvailableInSpecialTerm2;
    private List<Code> lockedModules;
    private Prereq prereq;

    public ModuleBuilder() {
        code = DEFAULT_CODE;
        department = DEFAULT_DEPARTMENT;
        title = DEFAULT_TITLE;
        description = DEFAULT_DESCRIPTION;
        credit = DEFAULT_CREDIT;
        isAvailableInSem1 = DEFAULT_ISAVAILABLEINSEM1;
        isAvailableInSem2 = DEFAULT_ISAVAILABLEINSEM2;
        isAvailableInSpecialTerm1 = DEFAULT_ISAVAILABLEINSPECIALTERM1;
        isAvailableInSpecialTerm2 = DEFAULT_ISAVAILABLEINSPECIALTERM2;
        lockedModules = DEFAULT_LOCKED_MODULES;
        prereq = DEFAULT_PREREQ;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        department = moduleToCopy.getDepartment();
        title = moduleToCopy.getTitle();
        description = moduleToCopy.getDescription();
        credit = moduleToCopy.getCredit();
        isAvailableInSem1 = moduleToCopy.isAvailableInSem1();
        isAvailableInSem2 = moduleToCopy.isAvailableInSem2();
        isAvailableInSpecialTerm1 = moduleToCopy.isAvailableInSpecialTerm1();
        isAvailableInSpecialTerm2 = moduleToCopy.isAvailableInSpecialTerm2();
        lockedModules = moduleToCopy.getLockedModules();
        prereq = moduleToCopy.getPrereq();
    }
    /**
     * Sets the code of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(Code code) {
        this.code = code;
        return this;
    }
    /**
     * Sets the department of the {@code Module} that we are building.
     */
    public ModuleBuilder withDepartment(String department) {
        this.department = department;
        return this;
    }
    /**
     * Sets the title of the {@code Module} that we are building.
     */
    public ModuleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    /**
     * Sets the description of the {@code Module} that we are building.
     */
    public ModuleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    /**
     * Sets the credit of the {@code Module} that we are building.
     */
    public ModuleBuilder withCredit(int credit) {
        this.credit = credit;
        return this;
    }
    /**
     * Sets isAvailableInSem1 of the {@code Module} that we are building.
     */
    public ModuleBuilder withIsAvailableInSem1(boolean isAvailableInSem1) {
        this.isAvailableInSem1 = isAvailableInSem1;
        return this;
    }
    /**
     * Sets isAvailableInSem2 of the {@code Module} that we are building.
     */
    public ModuleBuilder withIsAvailableInSem2(boolean isAvailableInSem2) {
        this.isAvailableInSem2 = isAvailableInSem2;
        return this;
    }
    /**
     * Sets isAvailableInSpecialTerm1 of the {@code Module} that we are building.
     */
    public ModuleBuilder withisAvailableInSpecialTerm1(boolean isAvailableInSpecialTerm1) {
        this.isAvailableInSpecialTerm1 = isAvailableInSpecialTerm1;
        return this;
    }
    /**
     * Sets isAvailableInSpecialTerm2 of the {@code Module} that we are building.
     */
    public ModuleBuilder withisAvailableInSpecialTerm2(boolean isAvailableInSpecialTerm2) {
        this.isAvailableInSpecialTerm2 = isAvailableInSpecialTerm2;
        return this;
    }
    /**
     * Sets lockedModules of the {@code Module} that we are building.
     */
    public ModuleBuilder withLockedModules(List<Code> lockedModules) {
        this.lockedModules = lockedModules;
        return this;
    }
    /**
     * Sets prereq of the {@code Module} that we are building.
     */
    public ModuleBuilder withPrereq(Prereq prereq) {
        this.prereq = prereq;
        return this;
    }
    /**
     * Sets every semester available.
     */
    public ModuleBuilder availableInAllSemesters() {
        this.isAvailableInSem1 = true;
        this.isAvailableInSem2 = true;
        this.isAvailableInSpecialTerm1 = true;
        this.isAvailableInSpecialTerm2 = true;
        return this;
    }
    /**
     * Sets every semester not available.
     */
    public ModuleBuilder notAvailableInAllSemesters() {
        this.isAvailableInSem1 = false;
        this.isAvailableInSem2 = false;
        this.isAvailableInSpecialTerm1 = false;
        this.isAvailableInSpecialTerm2 = false;
        return this;
    }
    /**
     * Creates a module object.
     */
    public Module build() {
        return new Module(code, department, title, description, credit, isAvailableInSem1,
                isAvailableInSem2, isAvailableInSpecialTerm1, isAvailableInSpecialTerm2,
                lockedModules, prereq);
    }
}
