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
	   
	    for(int p:prices)
	    {
	    	maxProfit2 = Math.max(maxProfit2, p-lowestBuyPrice2);
	    	lowestBuyPrice2 = Math.min(lowestBuyPrice2, p-maxProfit1);
	    	maxProfit1 = Math.max(maxProfit1, p-lowestBuyPrice1);
	    	lowestBuyPrice1 = Math.min(lowestBuyPrice1, p);
	    }
	    return maxProfit2;        
    }
}