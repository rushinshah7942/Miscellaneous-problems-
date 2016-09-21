/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/
//Explanation
//-----------------
// to find number of zeros, we need to find how many numbers are factors of 2 and 5
// as 10 = 2*5 
// and result will be dependent on minimum number of multiples of either 2 or 5
// Since, multiples of 2 are more than 5, we need to consider only multiples of 5

/*
Here we expand

  2147483647!
=2 * 3 * ...* 5 ... *10 ... 15* ... * 25 ... * 50 ... * 125 ... * 250...
=2 * 3 * ...* 5 ... * (5^1*2)...(5^1*3)...*(5^2*1)...*(5^2*2)...*(5^3*1)...*(5^3*2)... (Equation 1)
We just count the number of 5 in Equation 1.

Multiple of 5 provides one 5, multiple of 25 provides two 5 and so on.

Note the duplication: multiple of 25 is also multiple of 5, so multiple of 25 only provides one extra 5.

Here is the basic solution:

return n/5 + n/25 + n/125 + n/625 + n/3125+...;
*/

public class Solution 
{
    public int trailingZeroes(int n) 
    {
		int ans=0;
		while (n>=5) 
		{
			n/=5;
			ans+=n;
		}
		return ans;
    }
}