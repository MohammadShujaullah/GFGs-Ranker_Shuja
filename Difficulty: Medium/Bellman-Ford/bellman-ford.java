//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class DriverClass {
    public static void main(String args[]) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            // ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            int[][] edges = new int[E][3];

            int i = 0;
            for (i = 0; i < E; i++) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                // ArrayList<Integer> t1 = new ArrayList<>();
                // t1.add(u);
                // t1.add(v);
                // t1.add(w);
                // edges.add(t1);
                edges[i][0] = u;
                edges[i][1] = v;
                edges[i][2] = w;
            }

            int S = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            int[] ptr = ob.bellmanFord(V, edges, S);

            for (i = 0; i < ptr.length; i++) System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


// User function Template for Java

/*   Function to implement Bellman Ford
 *   edges: 2D array which represents the graph
 *   src: source vertex
 *   V: number of vertices
 */


class Solution {
    
    // Bellman-Ford Algorithm (For Directed Graphs)
    static int[] bellmanFord(int V, int[][] edges, int src) {

        int distance[] = new int[V];
        Arrays.fill(distance, 100000000);
        distance[src] = 0;

        // Relax all edges (V-1) times
        for (int i = 0; i < V - 1; i++) {  // Fix: start from 0
            for (int edge[] : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                long newDist = (long) distance[u] + weight; // Fix: prevent overflow
                if (distance[u] != 100000000 && newDist < distance[v]) {
                    distance[v] = (int) newDist;
                }
            }
        }

        // Detect negative weight cycle
        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            long newDist = (long) distance[u] + weight; // Fix: prevent overflow
            if (distance[u] != 100000000 && newDist < distance[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }

        return distance;
    }
}
