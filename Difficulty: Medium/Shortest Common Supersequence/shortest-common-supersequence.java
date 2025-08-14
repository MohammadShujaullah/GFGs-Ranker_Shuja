// User function Template for Java

import java.util.*;
class Solution {
    // Function to find length of shortest common supersequence of two strings.
    static int t[][];
    
     static int  m;
     static int  n;

    private static int solve(String s1, String s2,int i,int j){
        if(i==0 || j==0){
            return (i+j);
        }
        
        if(t[i][j]!=-1){
            return t[i][j];
        }
        

        if(s1.charAt(i-1)==s2.charAt(j-1)){
            return   t[i][j]= 1+solve(s1,s2,i-1,j-1);

        }
        
            return   t[i][j]= 1+Math.min(solve(s1,s2,i-1,j),solve(s1,s2,i,j-1));

        
    }
    public static int shortestCommonSupersequence(String s1, String s2) {
        
        // Your code here
        m = s1.length();
        n = s2.length();
        
        t=new int [m+1][n+1];
        for(int row[]:t){
            Arrays.fill(row,-1);
            
        }
       return  solve(s1, s2, m, n);
        
    }
}