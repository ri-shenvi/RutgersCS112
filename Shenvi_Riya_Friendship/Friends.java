package friends;

import java.util.ArrayList;
import structures.Queue;
import structures.Stack;

/**
 * A Friends class
 * 
 * @author Riya Shenvi
 * @date 6/7/24
 * @period 3
 */
public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {		
		// Create a queue for BFS
	    Queue<String> queue = new Queue<>();
	    // Create a stack to store the shortest chain
	    Stack<String> stack = new Stack<>();
	    // Create a boolean array to mark visited nodes
	    boolean[] visited = new boolean[g.members.length];
	    // Create a parent array to store the parent of each node
	    int[] parent = new int[g.members.length];
	    
	    // Initialize the queue with the starting node
	    queue.enqueue(p1);
	    // Mark the starting node as visited
	    visited[g.map.get(p1)] = true;
	    // Initialize the parent of the starting node to -1
	    parent[g.map.get(p1)] = -1;
	    
	    // Perform BFS
	    while (!queue.isEmpty()) {
	        String current = queue.dequeue();
	        int currentIndex = g.map.get(current);

	        // If the current node is the target node, construct the shortest chain
	        if (current.equals(p2)) {
	            while (currentIndex!= -1) {
	                stack.push(g.members[currentIndex].name);
	                currentIndex = parent[currentIndex];
	            }
	            break;
	        }
	     // Enqueue all unvisited neighbors of the current node
	        for (Friend friend = g.members[currentIndex].first; friend!= null; friend = friend.next) {
	            int friendIndex = friend.fnum;
	            if (!visited[friendIndex]) {
	                queue.enqueue(g.members[friendIndex].name);
	                visited[friendIndex] = true;
	                parent[friendIndex] = currentIndex;
	            }
	        }
	    }
	    
	 // If the target node is not reachable, return null
	    if (stack.isEmpty()) {
	        return null;
	    }

	    // Convert the stack to an ArrayList and return it
	    ArrayList<String> chain = new ArrayList<>();
	    while (!stack.isEmpty()) {
	        chain.add(stack.pop());
	    }
	    return chain;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an ArrayList of ArrayLists - each constituent ArrayList contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return ArrayList of cliques, where each clique is an ArrayList of names. 
	 * 				null if there is no student in the given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		// Create a boolean array to mark visited nodes
	    boolean[] visited = new boolean[g.members.length];
	    // Create an ArrayList to store the cliques
	    ArrayList<ArrayList<String>> cliques = new ArrayList<>();

	    // Iterate over all nodes in the graph
	    for (int i = 0; i < g.members.length; i++) {
	        // Check if the node is a student at the given school and has not been visited
	        if (g.members[i].student && g.members[i].school.equals(school) && !visited[i]) {
	            // Create a new clique
	        	ArrayList<String> clique = new ArrayList<>();
	            // Perform DFS to find all nodes in the clique
	            cliqdfs(g, i, visited, clique);
	            // Add the clique to the list of cliques
	            cliques.add(clique);
	        }
	    }
	    
	    if (cliques.isEmpty()) {
	        return null;
	    }

	    // Return the list of cliques
	    return cliques;
	}
	
	private static void cliqdfs(Graph g, int index, boolean[] visited, ArrayList<String> clique) {
	    // Mark the node as visited
	    visited[index] = true;
	    // Add the node to the clique
	    clique.add(g.members[index].name);

	    // Iterate over all friends of the node
	    for (Friend friend = g.members[index].first; friend != null; friend = friend.next) {
	        int friendIndex = friend.fnum;
	        // Check if the friend is a student at the same school and has not been visited
	        if (g.members[friendIndex].student && g.members[friendIndex].school.equals(g.members[index].school) && !visited[friendIndex]) {
	            // Perform DFS on the friend
	            cliqdfs(g, friendIndex, visited, clique);
	        }
	    }
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return an ArrayList of names of all connectors. null if there are no connectors.
	 */
//	public static ArrayList<String> connectors(Graph g) {
//	    // Create an ArrayList to store the connectors
//	    ArrayList<String> connectors = new ArrayList<>();
//	    // Create an array to store the dfs numbers
//	    int[] dfsnum = new int[g.members.length];
//	    // Create an array to store the back numbers
//	    int[] back = new int[g.members.length];
//	    // Create a boolean array to mark visited nodes
//	    boolean[] visited = new boolean[g.members.length];
//	    // Initialize the dfs number counter
//	    int dfsCounter = 0;
//
//	    // Iterate over all nodes in the graph
//	    for (int i = 0; i < g.members.length; i++) {
//	        // Check if the node has not been visited
//	        if (!visited[i]) {
//	            // Perform DFS to find connectors
//	            dfs(g, i, visited, dfsnum, back, connectors, dfsCounter);
//	        }
//	    }
//
//	    // If there are no connectors, return null
//	    if (connectors.isEmpty()) {
//	        return null;
//	    }
//
//	    // Return the list of connectors
//	    return connectors;
//	}
//
//	private static void dfs(Graph g, int index, boolean[] visited, int[] dfsnum, int[] back, ArrayList<String> connectors, int dfsCounter) {
//	    // Mark the node as visited
//	    visited[index] = true;
//	    // Assign the dfs number
//	    dfsnum[index] = dfsCounter;
//	    back[index] = dfsCounter;
//	    dfsCounter++;
//
//	    // Iterate over all friends of the node
//	    for (Friend friend = g.members[index].first; friend != null; friend = friend.next) {
//	        int friendIndex = friend.fnum;
//	        // Check if the friend has not been visited
//	        if (!visited[friendIndex]) {
//	            // Perform DFS on the friend
//	            dfs(g, friendIndex, visited, dfsnum, back, connectors, dfsCounter);
//	            // Update the back number
//	            back[index] = Math.min(back[index], back[friendIndex]);
//	            // Check if the node is a connector
//	            if (dfsnum[index] <= back[friendIndex] && index!= 0 && !connectors.contains(g.members[index].name)) {
//	                connectors.add(g.members[index].name);
//	            }
//	        } else {
//	            // Update the back number
//	            back[index] = Math.min(back[index], dfsnum[friendIndex]);
//	        }
//	    }
//	}
//	public static ArrayList<String> connectors(Graph g) {
//	    ArrayList<String> connectors = new ArrayList<>();
//	    int[] dfsnum = new int[g.members.length];
//	    int[] low = new int[g.members.length];
//	    boolean[] visited = new boolean[g.members.length];
//	    int dfsCounter = 0;
//
//	    for (int i = 0; i < g.members.length; i++) {
//	        if (!visited[i]) {
//	            dfs(g, i, visited, dfsnum, low, connectors, dfsCounter, -1);
//	        }
//	    }
//
//	    if (connectors.isEmpty()) {
//	        return null;
//	    }
//
//	    return connectors;
//	}
//
//	private static void dfs(Graph g, int index, boolean[] visited, int[] dfsnum, int[] low, ArrayList<String> connectors, int dfsCounter, int parent) {
//	    visited[index] = true;
//	    dfsnum[index] = dfsCounter;
//	    low[index] = dfsCounter;
//	    dfsCounter++;
//
//	    int children = 0;
//	    for (Friend friend = g.members[index].first; friend!= null; friend = friend.next) {
//	        int friendIndex = friend.fnum;
//	        if (!visited[friendIndex]) {
//	            children++;
//	            dfs(g, friendIndex, visited, dfsnum, low, connectors, dfsCounter, index);
//	            low[index] = Math.min(low[index], low[friendIndex]);
//	            if ((parent!= -1 && low[friendIndex] >= dfsnum[index]) || (parent == -1 && children > 1)) {
//	                connectors.add(g.members[index].name);
//	            }
//	        } else if (friendIndex!= parent) {
//	            low[index] = Math.min(low[index], dfsnum[friendIndex]);
//	        }
//	    }
//	}
	public static ArrayList<String> connectors(Graph g) {
	    ArrayList<String> connectors = new ArrayList<>();
	    int[] dfsnum = new int[g.members.length];
	    int[] low = new int[g.members.length];
	    boolean[] visited = new boolean[g.members.length];
	    int dfsCounter = 0;

	    for (int i = 0; i < g.members.length; i++) {
	        if (!visited[i]) {
	            dfs(g, i, visited, dfsnum, low, connectors, dfsCounter, -1);
	        }
	    }

	    if (connectors.isEmpty()) {
	        return null;
	    }

	    return connectors;
	}

	private static void dfs(Graph g, int index, boolean[] visited, int[] dfsnum, int[] low, ArrayList<String> connectors, int dfsCounter, int parent) {
	    visited[index] = true;
	    dfsnum[index] = dfsCounter;
	    low[index] = dfsCounter;
	    dfsCounter++;

	    int children = 0;
	    for (Friend friend = g.members[index].first; friend!= null; friend = friend.next) {
	        int friendIndex = friend.fnum;
	        if (!visited[friendIndex]) {
	            children++;
	            dfs(g, friendIndex, visited, dfsnum, low, connectors, dfsCounter, index);
	            low[index] = Math.min(low[index], low[friendIndex]);
	            if ((parent!= -1 && low[friendIndex] >= dfsnum[index]) || (parent == -1 && children > 1)) {
	                if (!connectors.contains(g.members[index].name)) {
	                    connectors.add(g.members[index].name);
	                }
	            }
	        } else if (friendIndex!= parent) {
	            low[index] = Math.min(low[index], dfsnum[friendIndex]);
	        }
	    }
	}
	        
	
}

