package seedu.address.model.semester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the students schedule.
 */
public class SemesterList {
    private List<Semester> semesterList;

    public SemesterList() {
        this.semesterList = new ArrayList<>();
    }

    public void addSemester(Semester newSemester) {
        semesterList.add(newSemester);
    }

    public void reverseOrder() {
        Collections.reverse(semesterList);
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (Semester semester : semesterList) {
            sb.append("semester ");
            sb.append(count);
            sb.append(": ");
            sb.append(semester.toString());
            sb.append("\n");
            count++;
        }
        return sb.toString();
    }
}
