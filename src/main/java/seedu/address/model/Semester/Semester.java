package seedu.address.model.Semester;

import seedu.address.model.module.Code;

import java.util.ArrayList;
import java.util.List;

public class Semester {

    private List<Code> toBeTaken;

    public Semester() {
        this.toBeTaken = new ArrayList<>();
    }

    public void addCode(Code code) {
        this.toBeTaken.add(code);
    }

    @Override
    public String toString() {
        return toBeTaken.toString();
    }
}
