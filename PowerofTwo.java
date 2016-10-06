/*
Given an integer, write a function to determine if it is a power of two.
*/

//Iterative
//O(log n)
public boolean isPowerOfTwo(int n) 
{
	if(n==0) 
		return false;
	//check if n can be divided by 2. If yes, divide n by 2 and check it repeatedly.
	while(n%2==0) 
		n/=2;
	
	return (n==1);
}

// Recursive
// O(log n)

public boolean isPowerOfTwo(int n) 
{
	return n>0 && (n==1 || (n%2==0 && isPowerOfTwo(n/2)));
}	

// Bit operation
// O(1)
public boolean isPowerOfTwo(int n)
{
	return n>0 && ((n & (n-1)) == 0);
}
