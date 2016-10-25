/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
*/

public class Solution 
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid) 
    {
        int width = obstacleGrid[0].length;
	    int[] dp = new int[width];
	    dp[0] = 1;
	    for (int[] row : obstacleGrid) 
	    {
	        for (int j = 0; j < width; j++) 
	        {
	            if (row[j] == 1)
	                dp[j] = 0;
	            else if (j > 0)
	                dp[j] += dp[j - 1];
	        }
	        //for(int ll = 0;ll<dp.length;ll++)
	        //	System.out.print(dp[ll] + " ");
	        
	        //System.out.println();
	    }
	    return dp[width - 1];    
    }
}