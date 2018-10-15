package seedu.address.logic;

import seedu.address.model.ModuleList;
import seedu.address.model.module.Code;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Generate {

    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List
    private List<Code> codesToTake;

    //Constructor
    public Generate(List<Code> codesToTake) {
        this.codesToTake = codesToTake;
        V = codesToTake.size();
        adj = new LinkedList[V];
        for (int i= 0; i < V; ++i) {
            adj[i] = new LinkedList();
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
    public void topologicalSort() {
        Stack stack = new Stack();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (stack.empty() == false) {
            int position = (Integer) stack.pop();
            System.out.print(codesToTake.get(position) + " -> ");
        }
    }
}
