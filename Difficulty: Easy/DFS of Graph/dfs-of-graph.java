//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer> ans = obj.dfsOfGraph(adj);
            for (int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + " ");
            System.out.println();

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    
    private void DFS( Map<Integer,ArrayList<Integer>>mp,int u,boolean visited[], ArrayList<Integer>result){
        if(visited[u]==true){
            return;
        }
        visited[u]=true;
        result.add(u);
        
        int i=0;
        for(int m:mp.get(u)){
            if(!visited[m]){
                DFS(mp,m,visited,result);
            }
            i++;
        }
    }
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Map<Integer,ArrayList<Integer>>mp= new HashMap<>();
        
        int m=adj.size();
        
        for(int i=0;i<m;i++){
        
                mp.put(i,adj.get(i));
                
        
            
        }
        
        ArrayList<Integer>result= new ArrayList<>();
        boolean visited[]= new boolean[mp.size()];
        
        DFS(mp,0,visited,result);
        return result;
        
    }
}