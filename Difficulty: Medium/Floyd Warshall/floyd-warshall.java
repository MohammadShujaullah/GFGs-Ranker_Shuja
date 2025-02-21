//{ Driver Code Starts
// Initial template for JAVA

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().trim().split(" ");
                for (int j = 0; j < n; j++) matrix[i][j] = Integer.parseInt(s[j]);
            }
            Solution obj = new Solution();
            obj.shortestDistance(matrix);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function template for JAVA

class Solution {
    public void shortestDistance(int[][] mat) {
        // Code here
      // first of all you have to convert the no edge wale ko -1 se infinite kr do
      int n=mat.length;
      
      
      for(int i=0;i<n;i++){
          for(int j=0;j<n;j++){
              if(mat[i][j]==-1){
                  mat[i][j]=100000;
              }
          }
      }
      
      for(int via=0;via<n;via++){
          for(int i=0;i<n;i++){
              for(int j=0;j<n;j++){
                  mat[i][j]=Math.min(mat[i][j],mat[i][via]+mat[via][j]);
              }
          }
      }
      
      for(int i=0;i<n;i++){
          for(int j=0;j<n;j++)
         {if( mat[i][j]==100000){
             mat[i][j]=-1;
         }}
      }
      
      
    }
}