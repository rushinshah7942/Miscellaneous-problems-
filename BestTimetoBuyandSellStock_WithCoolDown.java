/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

*/

public class Solution 
{
    public int maxProfit(int[] prices) 
    {
        if (prices == null || prices.length < 2) 
            return 0;
        
        int sold = 0, bought = -prices[0], rest = 0;
        for (int i=0; i<prices.length; ++i)
        {
            int prvSold = sold;
            sold = bought + prices[i];
            bought = Math.max(bought, rest-prices[i]);
            rest = Math.max(rest, prvSold);
        }
        return Math.max(sold, rest);
    }
    /*
    public int maxProfit(int[] prices) {
    int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
    for (int price : prices) {
        prev_buy = buy;
        buy = Math.max(prev_sell - price, prev_buy);
        prev_sell = sell;
        sell = Math.max(prev_buy + price, prev_sell);
    }
    return sell;
}
    */
}