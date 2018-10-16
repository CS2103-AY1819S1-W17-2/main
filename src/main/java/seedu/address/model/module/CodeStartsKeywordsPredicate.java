package seedu.address.model.module;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Module}'s {@code Code} starts with the keywords given.
 */
public class CodeStartsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public CodeStartsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> new Module(new Code(keyword), "", "", "", 0, false, false, false, false,
                        new ArrayList<Code>(), new Prereq()).isPrefixModule(module));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CodeStartsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CodeStartsKeywordsPredicate) other).keywords)); // state check
    }

}
