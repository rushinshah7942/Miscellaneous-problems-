/*
Implement pow(x, n).
*/

public class Solution 
{
    public double myPow(double x, int n) 
    {
        if(n < 0)
            return 1 / power(x,-n);
        else
            return power(x,n);
    }
    public double power(double x, int n)
    {
        if(n == 0)
            return 1;
            
        double v = power(x,n/2);
        
        if(n%2 ==0)
            return v*v;
        else
            return v*v*x;
    }
}

// recursive solution

public class Solution 
{
    public double pow(double x, int n) 
	{
        if(n == 0)
            return 1;
        if(n<0)
		{
            n = -n;
            x = 1/x;
        }
        return (n%2 == 0) ? pow(x*x, n/2) : x*pow(x*x, n/2);
    }
}