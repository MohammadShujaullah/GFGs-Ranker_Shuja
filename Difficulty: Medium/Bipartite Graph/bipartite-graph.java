//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] S = br.readLine().trim().split(" ");
            int V = Integer.parseInt(S[0]);
            S = br.readLine().trim().split(" ");
            int E = Integer.parseInt(S[0]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isBipartite(adj);
            if (ans)
                System.out.println("true");
            else
                System.out.println("false");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    private boolean DFS(Map<Integer, ArrayList<Integer>> mp, int curr, int color[], int currcolor) {
        color[curr] = currcolor;

        for (int v : mp.get(curr)) {
            if (color[v] == color[curr]) { // Conflict detected
                return false;
            } 
            else if (color[v] == -1) { // If not colored, assign opposite color and call DFS
                color[v] = 1 - color[curr]; 
                if (!DFS(mp, v, color, color[v])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        Map<Integer, ArrayList<Integer>> mp = new HashMap<>();

        // Convert adjacency list to HashMap
        for (int i = 0; i < adj.size(); i++) {
            mp.put(i, adj.get(i));
        }

        int color[] = new int[mp.size()];
        Arrays.fill(color, -1); // Initialize all nodes as uncolored

        // Run DFS for every component
        for (int i = 0; i < mp.size(); i++) {
            if (color[i] == -1) {
                if (!DFS(mp, i, color, 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
