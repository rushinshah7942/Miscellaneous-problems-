/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.


6/2 = 3;

6 2 
3 
*/

public class Solution 
{
    public int divide(int dividend, int divisor) 
    {        
        // num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n

        // logic
        // 32 ~ 3 * 10 = 3 * [1*(2^3) + 0*(2^2) + 1*(2^1) + 0*(2^0)]
        // 32 ~ 3 * 10 = 3 * [1*(2^3) + 1*(2^1)]
        // 1. countShift-1 = 3
		// 2. countShift-1 = 1
		
        if(divisor == 0)
            return Integer.MAX_VALUE;
        
        if(divisor == -1 && dividend  == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
            
        // long to avoid overflow while processing
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        
        
		// e.g. ldividend = 32, ldivisor = 3
		// We need to get the maximum mupltiplier x where x is => 2^maximum
		// we mupltuply x * ldivisor and subtract from ldivisor
		// do, this until ldividend >= ldivisor
		
		int result = 0; 		
        while(ldividend >= ldivisor)
        {
            int countShift = 0;
            while(ldividend >= (ldivisor<<countShift))
            {
                countShift++; // keep multiplying ldivisor with 2 
            }
            result += 1 << (countShift-1); // (countShift-1) with the maximum power of 2 
            ldividend -= (ldivisor << (countShift-1));
            
		}    
        // The outer loop reduces n by at least half each iteration. So It has O(log N) iterations.
        // The inner loop has at most log N iterations.
		// So, the overall TIME complexity is O( (log N) ^ 2)
		
        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        }
        else{
            return -result;
        }
    }
}