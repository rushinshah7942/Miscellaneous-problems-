/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

*/

public class Solution 
{
    public int divide(int dividend, int divisor) 
    {
        
        // num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n

        // logic
        // 32 ~ 3*10 = 3*[1*(2^3) + 0*(2^2) + 1*(2^1) + 0*(2^0)]
        
        if(divisor == 0)
            return Integer.MAX_VALUE;
        
        if(divisor == -1 && dividend  == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
            
        // long to avoid overflow while processing
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        
        
        int result = 0;
        
        while(ldividend >= ldivisor)
        {
            int countShift = 0;
            while(ldividend >= (ldivisor<<countShift))
            {
                countShift++;
            }
            result += 1 << (countShift-1);
            ldividend -= (ldivisor << (countShift-1));
            
        }    
        
        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0))
        {
            return result;
        }
        else
        {
            return -result;
        }
        
    }
}