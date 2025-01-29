//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++) list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new Solution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

class Solution {
    
    private boolean isCycleDFS( HashMap<Integer,ArrayList<Integer>>mp,int u, boolean visited[],boolean inRecursion[]){
        visited[u]=true;
        inRecursion[u]=true;
        
        for(int v:mp.get(u)){
            if(visited[v]==false && isCycleDFS(mp,v,visited,inRecursion) ){
                return true;
            }
            else if(inRecursion[v]==true){
                return true;
            }
        }
        inRecursion[u]=false;  // when you are returning , you have to put false again, means that the node is not in the same DFS recursion
        return false;
    }
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        HashMap<Integer,ArrayList<Integer>>mp= new HashMap<>();
        
        for(int i=0;i<V;i++){
            mp.put(i,adj.get(i));
            
        }
        
        boolean visited[] = new boolean[V];
        boolean inRecursion[]= new boolean[V];
        
        // traverse on all nodes either ,it is connected or not
        
        for(int i=0;i<mp.size();i++){
            if(!visited[i]  && isCycleDFS(mp,i,visited,inRecursion)){
                return true;
            }
        }
        return false;
    }
}