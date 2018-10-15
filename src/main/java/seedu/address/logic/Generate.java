package seedu.address.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import seedu.address.model.ModuleList;
import seedu.address.model.semester.Semester;
import seedu.address.model.semester.SemesterList;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.user.student.Student;

/**
 * Responsible for generating a student schedule based on the modules that has been staged.
 */
public class Generate {

    private int noOfModules; // No. of vertices
    private LinkedList<Integer>[] adj; // Adjacency List
    private List<Code> codesToTake;
    private Student student;

    public Generate(Student student) {
        this.student = student;
        codesToTake = new ArrayList<>();
        ModuleList modulesTaken = student.getModulesTaken();
        UniqueModuleList modulesToTake = student.getModulesStaged();
        noOfModules = modulesToTake.size();

        adj = new LinkedList[noOfModules];
        for (int i = 0; i < noOfModules; ++i) {
            adj[i] = new LinkedList();
        }

        codesToTake.addAll(modulesToTake.getAllCode());
        for (Module moduleToTake : modulesToTake) {
            for (Code code : moduleToTake.getLockedModules()) {
                if (codesToTake.contains(code)) {
                    addEdge(moduleToTake.getCode(), code);
                }
            }
        }
    }

    /**
     * Creates an edge from a module code to another module code.
     */
    public void addEdge(Code fromCode, Code toCode) {
        int v = codesToTake.indexOf(fromCode);
        int code = codesToTake.indexOf(toCode);
        adj[v].add(code);
    }

    /**
     * Topological sort recursive function to arrange the modules.
     */
    public void topologicalSortUtil(int v, boolean[] visited, Stack stack) {
        visited[v] = true;
        int i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(new Integer(v));
    }

    /**
     * Creates a linear arrangement of modules to take.
     */
    public ArrayList<Code> getLinearSchedule() {
        ArrayList<Code> linearSchedule = new ArrayList<>();
        Stack stack = new Stack();

        boolean[] visited = new boolean[noOfModules];
        for (int i = 0; i < noOfModules; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < noOfModules; i++) {
            if (visited[i] == false) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (stack.empty() == false) {
            int position = (Integer) stack.pop();
            linearSchedule.add(codesToTake.get(position));
        }

        return linearSchedule;
    }

    /**
     * Creates a schedule of semesters containing the modules to take for each semester.
     */
    public SemesterList getSchedule() {
        List<Code> unlockedModules = new ArrayList<>();
        SemesterList semesterList = new SemesterList();
        UniqueModuleList modulesToTake = this.student.getModulesStaged();

        Semester newSem = new Semester();

        ArrayList<Code> linearSchedule = getLinearSchedule();
        System.out.println(linearSchedule.toString());

        Collections.reverse(linearSchedule);

        Optional<Code> previousCode = Optional.empty();

        for (Code code : linearSchedule) {
            Module module = modulesToTake.getModuleByCode(code);
            List<Code> lockedModules = module.getLockedModules();

            if (!previousCode.isPresent()) {
                newSem.addModule(module);
                previousCode = Optional.of(module.getCode());
                continue;
            }

            if (lockedModules.contains(previousCode.get())) {
                semesterList.addSemester(newSem);
                newSem = new Semester();
            }

            if (newSem.totalCredits() > 16) {
                semesterList.addSemester(newSem);
                newSem = new Semester();
            }
            newSem.addModule(module);

            previousCode = Optional.of(module.getCode());
        }
        semesterList.addSemester(newSem);
        semesterList.reverseOrder();
        return semesterList;
    }
}
