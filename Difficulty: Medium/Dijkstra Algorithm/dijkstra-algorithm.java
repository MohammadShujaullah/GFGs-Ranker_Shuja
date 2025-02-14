//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class DriverClass {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<iPair>> adj = new ArrayList<ArrayList<iPair>>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<iPair>());
            }

            int i = 0;
            while (i++ < E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                iPair t1 = new iPair(v, w);
                iPair t2 = new iPair(u, w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }

            int src = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            ArrayList<Integer> res = ob.dijkstra(adj, src);

            for (i = 0; i < V; i++) System.out.print(res.get(i) + " ");
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


/*
class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
*/

// User function Template for Java
 

class Solution {
      // Custom pair class that implements Comparable
    static class Pair implements Comparable<Pair> {
        int distance, node;
        Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.distance == other.distance)
                return Integer.compare(this.node, other.node);
            return Integer.compare(this.distance, other.distance);
        }
    }

  

    // Function to find the shortest distance of all vertices from source vertex `src`
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        int n = adj.size(); // Number of vertices
        int[] result = new int[n]; // Store the shortest distance

        // Initialize distances with infinity
        Arrays.fill(result, Integer.MAX_VALUE);
        result[src] = 0;

        // Min-heap (TreeSet) to store (distance, node)
        TreeSet<Pair> t = new TreeSet<>();
        t.add(new Pair(0, src)); // (distance, node)

        while (!t.isEmpty()) {
            Pair p = t.pollFirst(); // Extract min distance node
            int distance = p.distance;
            int node = p.node;

            // Traverse all adjacent nodes
            for (iPair v : adj.get(node)) {
                int adjnode = v.first;
                int wl = v.second;

                if (distance + wl < result[adjnode]) { // Relaxation step
                    t.remove(new Pair(result[adjnode], adjnode)); // Remove old entry if exists
                    result[adjnode] = distance + wl;
                    t.add(new Pair(result[adjnode], adjnode));
                }
            }
        }

        // Convert int[] to ArrayList<Integer>
        ArrayList<Integer> ans = new ArrayList<>();
        for (int x : result) ans.add(x);
        return ans;
        
    }
    
}
