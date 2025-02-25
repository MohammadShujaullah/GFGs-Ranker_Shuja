//{ Driver Code Starts


import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int V = Integer.parseInt(br.readLine().trim());
            int E = Integer.parseInt(br.readLine().trim());
            List<List<int[]>> list = new ArrayList<>();
            for (int i = 0; i < V; i++) list.add(new ArrayList<>());
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                list.get(a).add(new int[] {b, c});
                list.get(b).add(new int[] {a, c});
            }
            ot.println(new Solution().spanningTree(V, E, list));

            ot.println("~");
        }
        ot.close();
    }
}
// } Driver Code Ends


// User function Template for Java


class Solution {
    // DSU Code
    private int[] parent;
    private int[] rank;

    private int find(int x) {
        if (x == parent[x]) 
            return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);

        if (xParent == yParent) 
            return;

        if (rank[xParent] > rank[yParent]) {
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            parent[xParent] = yParent;
        } else {
            parent[xParent] = yParent;
            rank[yParent]++;
        }
    }

    private int kruskal(List<int[]> edges, int V) {
        int sum = 0;
        int edgesConnected = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            int parentU = find(u);
            int parentV = find(v);

            if (parentU != parentV) {
                union(u, v);
                sum += wt;
                edgesConnected++;
            }
        }

        // Check if we formed a spanning tree
        if (edgesConnected != V - 1) {
            System.out.println("It's not a MST");
        }

        return sum;
    }

    public int spanningTree(int V,int E, List<List<int[]>> adj) {  // <-- Fixed parameter type
        parent = new int[V];
        rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int[] temp : adj.get(i)) {  // <-- Fixed List access
                int u = i;
                int v = temp[0];
                int d = temp[1];
                edges.add(new int[]{u, v, d});
            }
        }

        edges.sort(Comparator.comparingInt(a -> a[2]));

        return kruskal(edges, V);
    }
}
