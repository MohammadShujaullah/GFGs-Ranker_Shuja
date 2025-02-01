//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            int vertices = Integer.parseInt(read.readLine());
            int edges = Integer.parseInt(read.readLine());

            for (int i = 0; i < vertices; i++) adj.add(i, new ArrayList<Integer>());

            int p = 0;
            for (int i = 1; i <= edges; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
            }

            ArrayList<Integer> res = new Solution().topologicalSort(adj);

            if (check(adj, vertices, res) == true)
                System.out.println("1");
            else
                System.out.println("0");
            System.out.println("~");
        }
    }

    static boolean check(ArrayList<ArrayList<Integer>> adj, int V,
                         ArrayList<Integer> res) {

        if (V != res.size()) return false;

        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res.get(i)] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}

// } Driver Code Ends


class Solution {
    
    void DFS( Map<Integer,ArrayList<Integer>>mp,int u,boolean visited[], Stack<Integer>st){
        visited[u]=true;
        
        for(int v:mp.get(u)){
            if(!visited[v]){
                DFS(mp,v,visited,st);
            }
        }
        st.push(u);
    }
    // Function to return list containing vertices in Topological order.
    public ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        // Your code here
             Map<Integer,ArrayList<Integer>>mp= new HashMap<>();
        
        int m=adj.size();
        
        for(int i=0;i<m;i++){
        
                mp.put(i,adj.get(i));
                
        
            
        }
        
        ArrayList<Integer>result= new ArrayList<>();
        
         boolean visited[]= new boolean[mp.size()];
         Stack<Integer>st= new Stack<>();
         
         
         for(int i=0;i<mp.size();i++){
             if(!visited[i]){
                 DFS(mp,i,visited,st);
             }
         }
         while(!st.isEmpty()){
             result.add(st.pop());
         }
         return result;
    }
}