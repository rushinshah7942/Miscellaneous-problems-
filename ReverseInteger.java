/*
	Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

*/

public class ReverseInteger 
{
    public int reverse(int x) 
    {
        int flag = 0;
        if(x<0)
        {
            flag = 1; // negative
            x = x * (-1);
        }
        
        // keeping long to keep reverse of int to overflow before checking overflow
        long reverse = 0;
        
        while(x>0)
        {
            reverse = reverse * 10;
            reverse += (x%10);
            x =x/10;    
        }
        
        // overflow condition
        if(reverse > Integer.MAX_VALUE)
            return 0;
        
        if(flag == 1)
            reverse  = reverse * (-1);
            
        return (int)reverse;
    }
}