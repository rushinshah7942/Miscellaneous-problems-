/*

Determine whether an integer is a palindrome. Do this without extra space.

*/

// Easiest: Reverse the integer and then compare it with original

// Note: No extra space here means do not convert the integer to string, 
// since string will be a copy of the integer and take extra space. 
// The space take by div, left, and right can be ignored.



public class Solution 
{
    public boolean isPalindrome(int x) 
    {
        
		if (x < 0)
			return false;

		// this approach will come out early of the loop if the left and right pointers do not match
		
		// initialize how many zeros
		int div = 1;
		while (x / div >= 10) 
		{
			div *= 10;
		}
 
		while (x != 0) 
		{
			int left = x / div;
			int right = x % 10;
 
			if (left != right)
				return false;
 
			x = (x % div) / 10;
			div /= 100;
		}
 
		return true;
    }
	
	/*
		// Another approach
		int n = x;
		long reverse = 0;
		
		while(x > 0)
		{
		    reverse *= 10;
		    reverse += (x%10);
		    x = x/10;
		}
		
		if(reverse == n)
		    return true;
		else
		    return false;
		    
	*/
}