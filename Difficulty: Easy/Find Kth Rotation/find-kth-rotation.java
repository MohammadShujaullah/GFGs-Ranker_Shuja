//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    // Driver code
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            List<Integer> arr = new ArrayList<>();
            String input1 = sc.nextLine();
            Scanner ss1 = new Scanner(input1);
            while (ss1.hasNextInt()) {
                arr.add(ss1.nextInt());
            }
            Solution ob = new Solution();
            int res = ob.findKRotation(arr);
            System.out.println(res);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    public int findKRotation(List<Integer> arr) {
        // Code here
           int n = arr.size();
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr.get(mid) > arr.get(r)) { // means answer is lie on the rigth side of the mid,
                l = mid + 1; // array is sorted in rotatted so,
            } else {
                r = mid; // array is sorted normally
            }

        }
        return r;
    }
}