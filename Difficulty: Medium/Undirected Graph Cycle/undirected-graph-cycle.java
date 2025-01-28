//{ Driver Code Starts
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
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isCycle(adj);
            if (ans)
                System.out.println("1");
            else
                System.out.println("0");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    
    
    private boolean isCycle(Map<Integer,ArrayList<Integer>>mp,int start,boolean visted [],int parent){
        
        visted[start]=true;
        
        // now checking the cyle in the each node's ArrayList
        for(int neigbhour:mp.get(start)){
            if(neigbhour==parent){
                continue;
            }
            if(visted[neigbhour]==true){
                return true;
            }
            if(isCycle(mp,neigbhour,visted,start)){
                return true;
            }
        }
        return false;
    }
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        
        //storing the adj as map in Unorder map
        Map<Integer,ArrayList<Integer>>mp=new HashMap<>();
        
        for(int i=0;i<adj.size();i++){
            mp.put(i,adj.get(i));
            
        }
        
        // creating the visited array
        boolean visted []= new boolean [mp.size()];
        
        for(int i=0;i<mp.size();i++){
            if(visted[i]==false && isCycle(mp,i,visted,-1)){ // intially the 0 has -1 as parent
                return true;
            } 
            
        }
        return false;
    }
}