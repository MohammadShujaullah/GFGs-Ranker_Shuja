//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class GFG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        
        while (tc-- > 0) {
            int V = scanner.nextInt();
            int E = scanner.nextInt();
            
            List<Integer>[] adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < E; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            
            // String x=scanner.nextLine();
            // scanner.nextLine();
            
            Solution obj = new Solution();
            int ans = obj.isEulerCircuit(V, adj);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


class Solution{
    
    private void DFS(List<Integer>[] adj,int u, boolean visited[],int V){
        visited[u]=true;
        
    
            for(int v:adj[u]){
                if(visited[v]==false){
                    DFS(adj,v,visited,V);
                }
            }
        
    }
    
    private boolean isConnected(int V,List<Integer>[] adj ){
        
        
    // START WITH A NODE WHOSE EGREE OF VERTEX IS NOT ZERO
       int nonZeroDegreeVertex=-1;
       
       for(int i=0;i<V;i++){
           if(adj[i].size()>0){
               nonZeroDegreeVertex=i;
               break;
           }
       }
       
       boolean visited []=new boolean[V];
       //start the dfs  from nonZeroDegreeVertex
       DFS(adj,nonZeroDegreeVertex,visited,V);
       
       
       // cheack if all non zero degree wale vertices are visted or not 
       for(int i=0;i<V;i++){
           if(visited[i]==false && adj[i].size()>0){
               return false;
           }
       }
       return true;
       
       
        
        
    }
    public int isEulerCircuit(int V, List<Integer>[] adj) 
    {
        // CHECK IF ALL NON-DEGREE WALE VERTICES ARE CONNECTED OR NOT 
        
        if(!isConnected( V,adj)){
            return 0;
        }
        
        
        int oddDegree=0;
        // count odd degree wale vertices 
        for(int i=0;i<V;i++){
           if( adj[i].size()%2!=0){   // odd 
               oddDegree++;
               
           }
        }
        
        
        
        if(oddDegree>2){
            return 0;      // NON 
        }
         else if(oddDegree==2){
            return 1;        // EULERIAN PATH
        }
        
            return 2;    // EULERIAN CIRCUIT
        
        
    }
}