//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String s[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                s = br.readLine().trim().split(" ");
                edges[i][0] = Integer.parseInt(s[0]);
                edges[i][1] = Integer.parseInt(s[1]);
                edges[i][2] = Integer.parseInt(s[2]);
            }

            List<Integer> list = new Solution().shortestPath(n, m, edges);

            ot.println(list.get(0));
            if (list.get(0) != -1 && !check(list, edges)) ot.println(-1);
        }
        ot.close();
    }

    static boolean check(List<Integer> list, int edges[][]) {
        Set<Integer> hs = new HashSet<>();
        Map<Integer, Map<Integer, Integer>> hm = new HashMap<>();
        for (int i = 1; i < list.size(); i++) hs.add(list.get(i));
        for (int x[] : edges) {
            if (hs.contains(x[0]) || hs.contains(x[1])) {
                if (!hm.containsKey(x[0])) hm.put(x[0], new HashMap<>());
                if (!hm.containsKey(x[1])) hm.put(x[1], new HashMap<>());
                hm.get(x[0]).put(x[1], x[2]);
                hm.get(x[1]).put(x[0], x[2]);
            }
        }
        int sum = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            if (!hm.containsKey(list.get(i)) ||
                !hm.get(list.get(i)).containsKey(list.get(i + 1)))
                return false;
            sum += hm.get(list.get(i)).get(list.get(i + 1));
        }
        return sum == list.get(0);
    }
}

// } Driver Code Ends


 class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        // Adjacency List using HashMap
        HashMap<Integer, List<int[]>> mp = new HashMap<>();
        
        for (int[] edge : edges) {
            mp.putIfAbsent(edge[0], new ArrayList<>());
            mp.putIfAbsent(edge[1], new ArrayList<>());
            
            mp.get(edge[0]).add(new int[]{edge[1], edge[2]});
            mp.get(edge[1]).add(new int[]{edge[0], edge[2]}); // Undirected Graph
        }

        // Distance array (initialized to infinity)
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[1] = 0;  // Distance of source (1) is always 0
        
        // Parent tracking for path reconstruction
        int[] parents = new int[n + 1];
        Arrays.fill(parents, -1);
        parents[1] = 1;

        // Min-Heap (Priority Queue) for Dijkstra's Algorithm
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> pq = 
            new PriorityQueue<>(Comparator.comparingInt(AbstractMap.SimpleEntry::getKey));
        
        pq.add(new AbstractMap.SimpleEntry<>(0, 1));

        while (!pq.isEmpty()) {
            AbstractMap.SimpleEntry<Integer, Integer> p = pq.poll();
            int distance = p.getKey();
            int node = p.getValue();

            if (!mp.containsKey(node)) continue; // Skip if node has no edges

            for (int[] v : mp.get(node)) {
                int adjNode = v[0], weight = v[1];

                if (distance + weight < result[adjNode]) {
                    result[adjNode] = distance + weight;
                    parents[adjNode] = node;
                    pq.add(new AbstractMap.SimpleEntry<>(result[adjNode], adjNode));
                }
            }
        }

        // If destination is unreachable, return [-1]
        if (result[n] == Integer.MAX_VALUE) return new ArrayList<>(Collections.singletonList(-1));

        // Reconstruct the path from n to 1
        ArrayList<Integer> path = new ArrayList<>();
        int x = n;
        while (x != 1) {
            path.add(x);
            x = parents[x];
        }
        path.add(1);
        Collections.reverse(path);

        // Add the total weight at the beginning
        path.add(0, result[n]);

        return path;
    }
}