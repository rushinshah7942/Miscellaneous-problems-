/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class Solution {
    public int climbStairs(int n) 
    {
        /*
        
        // with different values of n, show that this problem is fibonacci series
        
        if(n==0)
            return 0;
            
        if(n==1)
            return 1;
            
        if(n==2)
            return 2;
        
        else
            return climbStairs(n-1) + climbStairs(n-2);
        */

        // interative version of fibonaaci series
        
        if(n==0)
            return 0;
            
        if(n==1)
            return 1;
            
        if(n==2)
            return 2;
    
        int a = 1;
        int b =  2;
        int c;
        for(int i=3;i<=n;i++)
        {
            c = a+b;
            a = b;
            b = c;
        }
        return b;
    }
}