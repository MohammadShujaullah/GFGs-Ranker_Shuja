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
class Node {
    int key1,weight;
    public Node(int key1,int weight){
        this.key1=key1;
        this.weight=weight;
        
    }
}

class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Code Here.
        boolean isMST[] =new boolean[V];
        
 
        
        PriorityQueue<Node>pq= new PriorityQueue<>(Comparator.comparingInt(l->l.weight));
        // queue is based on according to weight
        pq.add(new Node(0,0));
        
        int sum=0;
        
        
        while(!pq.isEmpty()){
            
            Node pairs=pq.poll();
            int a=pairs.key1;
            int d=pairs.weight;
            if(isMST[a]){
                continue;
            }
            isMST[a]=true;  // add to our mst
            
            sum+=d;
            
            for(int []m:adj.get(a)){
                int neighbor=m[0];
                int neighbor_weight=m[1];
                if(!isMST[neighbor]){
                    pq.add(new Node(neighbor,neighbor_weight));
                    
                    
                }
                
            }
            
            
        }
        
        return sum;
        
        
        
    }
}