/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/
public class Solution 
{
    public int mySqrt(int x) 
    {
        /*
        if(x == 0)
            return 0;
        
        if(x == 1)
            return 1;
 
		Simple solution - O(sqrt(n))
         ---> 31 ms
        long i;
        long num = (long)x;
        for(i=1;i<num;i++)
        {
            if(i*i > num)
            {
                break;
            }
        }
        return (int)i-1;
        */
        
		
		// n
        // 3ms version 
        // O(log n) version
        
         if(x <= 1) 
            return x;
        
        int left = 1, right = x;
        
        while(left < right) 
        {
            int mid = left + (right - left) / 2;
			
            // x / mid to avoid potential integer overflow and using long variable insteadokm 
            if(mid <= x / mid) 
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid;
            }
        }

        return left - 1; // left-1 is important, get this by solving few examples
    }
}