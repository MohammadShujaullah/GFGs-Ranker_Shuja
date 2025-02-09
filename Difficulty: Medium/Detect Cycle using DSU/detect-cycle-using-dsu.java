//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            int ans = obj.detectCycle(V, adj);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


class Solution
{
    
     // Iterative Path Compression
    private int find(int i, int[] parents) {
        while (i != parents[i]) {
            parents[i] = parents[parents[i]]; // Path compression
            i = parents[i];
        }
        return i;
    }
    
    // Union by Rank
    private void union(int x, int y, int[] parents, int[] rank) {
        int parent_x = find(x, parents);
        int parent_y = find(y, parents);
        
        if (parent_x == parent_y) return; // Already connected
        
        if (rank[parent_x] > rank[parent_y]) {
            parents[parent_y] = parent_x;
        } else if (rank[parent_x] < rank[parent_y]) {
            parents[parent_x] = parent_y;
        } else {
            parents[parent_x] = parent_y;
            rank[parent_y]++;
        }
    }
    
    
    
    //Function to detect cycle using DSU in an undirected graph.
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        // Map<Integer,ArrayList<Integer>>mp= new HashMap<>();
        // for(int i=0;i<V;i++){
        //     mp.put(i,adj.get(i));
            
        // }
        // parents
        
          int[] parents = new int[V];
        int[] rank = new int[V];

        // Initialize parents and ranks
        for (int i = 0; i < V; i++) {
            parents[i] = i;
            rank[i] = 0;
        }

        HashSet<String> visitedEdges = new HashSet<>();

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                // Avoid processing the same edge twice (since it's an undirected graph)
                String edgeKey = Math.min(u, v) + "," + Math.max(u, v);
                if (visitedEdges.contains(edgeKey)) continue;
                visitedEdges.add(edgeKey);

                int parent_u = find(u, parents);
                int parent_v = find(v, parents);

                if (parent_u == parent_v) {
                    return 1; // Cycle detected
                }

                union(u, v, parents, rank);
            }
        }

        return 0; // No cycle found
    }
}