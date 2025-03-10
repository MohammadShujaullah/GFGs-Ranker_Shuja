//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends

// User function Template for Java

class Solution {
      private void DFSOfadjReverse(ArrayList<ArrayList<Integer>> adjReverse,int u, boolean visited[]){
        visited[u]=true;
        
        for(int v:adjReverse.get(u)){
            if(!visited[v]){
                DFSOfadjReverse(adjReverse,v,visited);
            }
        }
    
    }
    
    private void DFS(ArrayList<ArrayList<Integer>> adj,int u, boolean visited[],Stack<Integer>st){
        visited[u]=true;
        
        for(int v:adj.get(u)){
            if(!visited[v]){
                DFS(adj,v,visited,st);
            }
        }
        st.push(u);     //last main push the papa
    }
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        
        // step 1 *****
        
        //topological sorting 
        boolean visited[]= new boolean[adj.size()];
        Stack<Integer>st= new Stack<>();
        
        for(int i=0;i<adj.size();i++){
            if(!visited[i]){
                DFS(adj,i,visited,st);
                
            }
        }
        
        //step2*******
        // reversing the graph's edges, in new List
        
        ArrayList<ArrayList<Integer>> adjReverse= new ArrayList<>();
        for(int i=0;i<adj.size();i++){
            adjReverse.add(new ArrayList<>());
            
        }
        
        for(int u=0;u<adj.size();u++){
            for(int v:adj.get(u)){
                adjReverse.get(v).add(u);
                
            }
        }
        
        //step3*****
       //    DFS on stack's element  of adjReverse
       
       Arrays.fill(visited,false);
       int totalscc=0;
       
       while(!st.isEmpty()){
           
           int m=st.pop();
           
               if(!visited[m]){
                   DFSOfadjReverse(adjReverse,m,visited);
                   totalscc++;
                   
                   
               }
           
           
       }
       
       
       
    
        return totalscc;    
        
    }
}


//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
            }

            Solution obj = new Solution();
            System.out.println(obj.kosaraju(adj));

            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends