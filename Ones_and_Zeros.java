/*
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.

Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

*/

// Time Complexity: O(kl + kmn), where k is the length of input string array and l is the average length of a string within the array.

public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        
        // dp[i][j] = the max number of strings that can be formed with i 0's and j 1's
        for (String s : strs) {
            
            // for each string count 1s and 0s
            int[] count = count(s);
            
            for (int i = m; i >= count[0]; i--) 
                for (int j = n; j >= count[1]; j--) 
                    dp[i][j] = Math.max( 1 +  dp[ i-count[0] ][ j-count[1] ] ,  dp[i][j]);
        
        }
        return dp[m][n];
    }
    
    public int[] count(String str) {
        int[] res = new int[2];
        for (int i=0;i<str.length();i++)
            res[str.charAt(i) - '0']++;
        return res;
    }
}
/*

memo[i][j] = max(memo[i][j], memo[i - numZeroes][j - numOnes] + 1); This line says:

1. There are two possible ways to form the max number of strings with i 0's and j 1's regarding s: we either form s or skip it.
2. If we skip s, memo[i][j] shouldn't change.
3. Otherwise, we form s with numZeroes 0's and numOnes 1's, which leaves us i - numZeroes 0's and j - numOnes 1's to work with for all previous strings. How many strings can we form with i - numZeroes 0's and j - numOnes 1's? It's memo[i - numZeroes][j - numOnes] which was calculated in previous rounds, so just add 1 to that.
4. We choose to form s or skip it based on which of 2 and 3 gives us a larger memo[i][j]

*/