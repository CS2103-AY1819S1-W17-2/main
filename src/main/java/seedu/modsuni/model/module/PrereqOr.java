package seedu.modsuni.model.module;

import java.util.List;
import java.util.Optional;

/**
 * Encapsulates the Or type of prereq data.
 */
public class PrereqOr {

    private Optional<List<PrereqOr>> or;
    private Optional<List<PrereqAnd>> and;
    private Optional<Code> code;

    public Optional<List<PrereqOr>> getOr() {
        return or;
    }

    public void setOr(Optional<List<PrereqOr>> or) {
        this.or = or;
    }

    public Optional<List<PrereqAnd>> getAnd() {
        return and;
    }

    public void setAnd(Optional<List<PrereqAnd>> and) {
        this.and = and;
    }

    public Optional<Code> getCode() {
        return code;
    }

    public void setCode(Optional<Code> code) {
        this.code = code;
    }
}
