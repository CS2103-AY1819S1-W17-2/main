package seedu.address.logic;

import seedu.address.model.ModuleList;
import seedu.address.model.Semester.Semester;
import seedu.address.model.Semester.SemesterList;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.user.student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Generate {

    private int noOfModules; // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List
    private List<Code> codesToTake;
    private Student student;

    //Constructor
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

    // Function to add an edge into the graph
    public void addEdge(Code fromCode, Code toCode) {
        int v = codesToTake.indexOf(fromCode);
        int code = codesToTake.indexOf(toCode);
        adj[v].add(code);
    }

    // A recursive function used by topologicalSort
    public void topologicalSortUtil(int v, boolean visited[], Stack stack) {
        // Mark the current node as visited.
        visited[v] = true;
        int i;

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }

    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    public ArrayList<Code> getLinearSchedule() {
        ArrayList<Code> linearSchedule = new ArrayList<>();
        Stack stack = new Stack();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[noOfModules];
        for (int i = 0; i < noOfModules; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < noOfModules; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (stack.empty() == false) {
            int position = (Integer) stack.pop();
            linearSchedule.add(codesToTake.get(position));
        }

        return linearSchedule;
    }

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

            if (previousCode.isPresent()) {
                if (lockedModules.contains(previousCode.get())) {
                    semesterList.addSemester(newSem);
                    newSem = new Semester();
                    newSem.addCode(module.getCode());
                } else {
                    newSem.addCode(module.getCode());
                }
                previousCode = Optional.of(module.getCode());
            } else {
                newSem.addCode(module.getCode());
                previousCode = Optional.of(module.getCode());
            }
        }
        semesterList.addSemester(newSem);
        semesterList.reverseOrder();
        return semesterList;
    }
}
