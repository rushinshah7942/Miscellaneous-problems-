/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

public class Solution 
{
    public int maxProfit(int[] prices) 
    {
        int maxProfit1 = 0; 
	    int maxProfit2 = 0; 
	    int lowestBuyPrice1 = Integer.MAX_VALUE;
	    int lowestBuyPrice2 = Integer.MAX_VALUE;
	   
	   
		// 7 1 5 3 6 8
		
	    for(int p:prices)
	    {
			
	    	maxProfit2 = Math.max(maxProfit2, p-lowestBuyPrice2); 
	    	lowestBuyPrice2 = Math.min(lowestBuyPrice2, p-maxProfit1);  // keep subtracting maxProfit1 from every prices,
			// that way we can keep track of second minimum low value
	    
			maxProfit1 = Math.max(maxProfit1, p-lowestBuyPrice1);
	    	lowestBuyPrice1 = Math.min(lowestBuyPrice1, p);
			
	    }
	    return maxProfit2;        
    }
}

/*

Execution
----------------
7: maxProfit2: 0 lowPrice2: 7 maxProfit1: 0 lowPrice1: 7
1: maxProfit2: 0 lowPrice2: 1 maxProfit1: 0 lowPrice1: 1
5: maxProfit2: 4 lowPrice2: 1 maxProfit1: 4 lowPrice1: 1
3: maxProfit2: 4 lowPrice2: -1 maxProfit1: 4 lowPrice1: 1
6: maxProfit2: 7 lowPrice2: -1 maxProfit1: 5 lowPrice1: 1
8: maxProfit2: 9 lowPrice2: -1 maxProfit1: 7 lowPrice1: 1

*/


// Follow-up: what if K most transactions are allowed, then !!!
// Dynamic approach

// Time complexity is O(kn), space complexity can be O(n) because this DP only uses the result from last step. 
// But for cleaness this solution still used O(kn) space complexity to preserve similarity to the equations in the comments.

// Reference: https://www.youtube.com/watch?v=oDhu5uGq_ic


//* Time complexity - O(number of transactions * number of days)
//* Space complexity - O(number of transcations * number of days)
public int maxProfit(int[] prices){
	int n = prices.length, num = 2;
	if (n <= 1) 
		return 0;
	
	int[][] dp = new int[num+1][n];
	
	for (int k = 1; k <= num; k++) {
		int maxDiff = dp[k - 1][0] - prices[0];
		for (int j = 1; j < n; j++) {
			dp[k][j] = Math.max(dp[k][j - 1], prices[j] + maxDiff);
			maxDiff = Math.max(maxDiff, dp[k - 1][j] - prices[j]);
		}
	}
	return dp[num][n - 1];    
}
	
/**
 * This is slow method but easier to understand.
 * Time complexity is O(k * number of days ^ 2)
 * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
 */
public int maxProfitSlowSolution(int prices[], int K) {
	if (K == 0 || prices.length == 0) {
		return 0;
	}
	int T[][] = new int[K+1][prices.length];

	for (int i = 1; i < T.length; i++) {
		for (int j = 1; j < T[0].length; j++) {
			int maxVal = 0;
			for (int m = 0; m < j; m++) {
				maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i-1][m]);
			}
			T[i][j] = Math.max(T[i][j-1], maxVal);
		}
	}
	printActualSolution(T, prices);
	return T[K][prices.length - 1];
}	