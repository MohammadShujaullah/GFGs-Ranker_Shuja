//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            int graph[][] = new int[N][N];
            for(int i = 0;i < N;i++){
                String a[] = in.readLine().trim().split("\\s+");
                for(int j = 0;j < N;j++)
                    graph[i][j] = Integer.parseInt(a[j]);
            }
            
            Solution ob = new Solution();
            System.out.println(ob.eulerPath(N, graph));
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int eulerPath(int N, int graph[][]){
        // code here
        HashMap<Integer,ArrayList<Integer>>mp= new HashMap<>();
        
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                
                mp.putIfAbsent(i,new ArrayList<>());
                
                if(graph[i][j]==1){
                    mp.get(i).add(j);
                    
                }

            }
        }
        
        int oddDegree=0;
        
        for(int i=0;i<mp.size();i++){
            int u=mp.get(i).size();
            if(u%2!=0){
                oddDegree++;
                
            }
        }
        
        if(oddDegree>2){
            return 0;
            
        }
        return 1;
    }
}