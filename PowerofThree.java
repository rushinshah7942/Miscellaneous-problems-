/*
Given an integer, write a function to determine if it is a power of three.
*/

public class Solution 
{
    public boolean isPowerOfThree(int n) 
    {
        if(n==0)
            return false;
            
        return n == Math.pow(3,Math.round(Math.log(n)/Math.log(3)));
    }
}

public class Solution 
{
	public boolean isPowerOfThree(int n) 
	{
		// 1162261467 is 3^19,  3^20 is bigger than int  
		return ( n>0 &&  1162261467%n==0);
	}
}