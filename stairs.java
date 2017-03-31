/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class Solution {
    public int climbStairs(int n) 
    {
        /*
        
        // with different values of n, show that this problem is fibonacci series
        // top-down - recursive version - O(2^n) 
		// and with memoization O(n) time and O(n) space

        if(n==0)
            return 0;
            
        if(n==1)
            return 1;
            
        if(n==2)
            return 2;
        
        else
            return climbStairs(n-1) + climbStairs(n-2);
        */

        // iterative version of fibonaaci series
        // tabulation (bottom-up) version
		// time O(n) and space O(1)
		
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


class stairs
{
    // A simple recursive program to find n'th fibonacci number
    static int fib(int n)
    {
       if (n <= 1)
          return n;
       return fib(n-1) + fib(n-2);
    }
     
    // Returns number of ways to reach s'th stair
    static int countWays(int s)
    {
        return fib(s + 1);
    }
 
 
    /* Driver program to test above function */
    public static void main (String args[])
    {
          int s = 4;
          System.out.println("Number of ways = "+ countWays(s));
    }
}

/*
Generalization of the above problem
----------------------------------------
- How to count number of ways if the person can climb up to n stairs for a given value m? 
- For example if m is 4, the person can climb 1 stair or 2 stairs or 3 stairs or 4 stairs at a time.

We can write the recurrence as following.

   ways(n, m) = ways(n-1, m) + ways(n-2, m) + ... ways(n-m, m) 
   
*/
  
// Time : Exponential O(m^n)
   
class stairs
{
    // A recursive function used by countWays
    static int countWaysUtil(int n, int m)
    {
        if (n <= 1)
            return n;
  
		int res = 0;
        for (int i = 1; i<=m && i<=n; i++)
            res += countWaysUtil(n-i, m);
        
		return res;
    }
  
    // Returns number of ways to reach s'th stair
    static int countWays(int s, int m)
    {
        return countWaysUtil(s+1, m);
    }
 
    /* Driver program to test above function */
    public static void main (String args[])
    {
          int s = 4,m = 2;
          System.out.println("Number of ways = "+ countWays(s,m));
    }
}   


// It can be optimized to O(mn) by using dynamic programming
// to count number of ways to reach n't stair when
// a person can climb 1, 2, ..m stairs at a time

class stairs
{
    public int countWaysUtil(int n, int m)
	{
		int res[n];
		res[0] = 1; res[1] = 1;
	
		for (int i=2; i<n; i++)
		{
		   res[i] = 0;
		   for (int j=1; j<=m && j<=i; j++)
			 res[i] += res[i-j];
		}
		return res[n-1];
	}
	 
	// Returns number of ways to reach s'th stair
	public int countWays(int s, int m)
	{
		return countWaysUtil(s+1, m);
	}
	 
	// Driver program to test above functions
	public static void main ()
	{
		int s = 4, m = 2;
		printf("Nuber of ways = %d", countWays(s, m));
	}
}