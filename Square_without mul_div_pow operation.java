/*
Calculate square of a number without using *, / and pow().

Examples:

Input: n = 5
Output: 25

Input: 7
Output: 49

Input: n = 12
Output: 144
*/

// O(n)
int square(int n)
{
   // handle negative input
   if (n<0) 
	   n = -n;
 
   // Initialize result
   int res = n;
 
   // Add n to res n-1 times
   for (int i=1; i<n; i++)
       res += n;
 
   return res;
}

/*

If n is even, it can be written as
  n = 2*x 
  n^2 = (2*x)^2 = 4*x^2
  
If n is odd, it can be written as 
  n = 2*x + 1
  n^2 = (2*x + 1)^2 = 4*x^2 + 4*x + 1
  
*/
// O(Logn) time using bitwise operators
int square(int n)
{
    // Base case
    if (n==0) 
		return 0;
 
    // Handle negative number
    if (n < 0) 
		n = -n;
 
    // Get floor(n/2) using right shift
    int x = n >> 1; // n = 2*x
 
    // If n is odd
    if (n & 1)
        return ( (square(x) << 2) + (x << 2) + 1);
    else // If n is even
        return (square(x) << 2); // 4* x^2
}

