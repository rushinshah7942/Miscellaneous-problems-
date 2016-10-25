/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
*/

public class Solution 
{
    public int uniquePaths(int m, int n) 
    {
        int[][] dp = new int[m][n];
        
        dp[0][0] = 1;
        
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==0 && j!=0)
                    dp[i][j] = dp[i][j-1];
                else if(i!=0 && j==0)
                    dp[i][j] = dp[i-1][j];
                else if(i==0 && j==0)
                    dp[i][j] = dp[i][j];
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}


// a little optimization of the space, 1-D array
// when (m==0 || n== 0), there will be no way to go down or right, so return 0.

public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
}