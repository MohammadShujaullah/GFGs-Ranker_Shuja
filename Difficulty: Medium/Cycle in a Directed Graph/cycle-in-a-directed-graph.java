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
    


    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        HashMap<Integer,ArrayList<Integer>>mp= new HashMap<>();
        
        for(int i=0;i<V;i++){
            mp.put(i,adj.get(i));
            
        }
        // we have to define indegree array
       
       int indegree[]= new int [mp.size()];
       
       Queue<Integer>q= new LinkedList<>();
       
        // step1 //we have to populate the indegree
       for(int i=0;i<mp.size();i++){
           for(int u:mp.get(i)){
               indegree[u]++;
           }
       }
       
       
       // we have to take a cout 
       int count=0;
       
       //step2// we have to push the node in queue whose indegree is 0
       for(int i=0;i<mp.size();i++){
           if(indegree[i]==0){
               q.add(i);
               count++;
               
           }
       }
       
       
       
       //step3/// BFS
       while(!q.isEmpty()){
           int u=q.remove();
           
           for(int v:mp.get(u)){
               indegree[v]--;
               if(indegree[v]==0){
                   q.add(v);
                   count++;
               }
           }
       }
       
       
       
       
       
       
       if(count==mp.size()){  // if count == no. of all node means a cycle is exist , so return false
           return false;
       }
        return true;
    }
}