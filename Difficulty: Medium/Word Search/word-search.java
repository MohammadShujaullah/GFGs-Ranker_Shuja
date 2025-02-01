//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt(); // Number of test cases
        while (tc-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] mat = new char[n][m];

            // Reading the matrix
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    mat[i][j] = sc.next().charAt(0);
                }
            }

            String word = sc.next();
            Solution obj = new Solution();
            boolean ans = obj.isWordExist(mat, word);
            if (ans)
                System.out.println("true");
            else
                System.out.println("false");

            System.out.println("~");
        }
        sc.close();
    }
}
// } Driver Code Ends


class Solution {
     int l, m, n;
    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public boolean find(char[][] board, int i, int j, String word, int idx) {
        if (idx >= l)
            return true;

        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(idx))
            return false;

        char temp = board[i][j];
        board[i][j] = '$';

        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];

            if (find(board, i_, j_, word, idx + 1))
                return true;
        }

        board[i][j] = temp;
        return false;
    }
    public boolean isWordExist(char[][] mat, String word) {
        // Code here
         m = mat.length;
          n = mat[0].length;
         l = word.length();
        if (m * n < 1)
            return false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == word.charAt(0) && find(mat, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    
    }
}