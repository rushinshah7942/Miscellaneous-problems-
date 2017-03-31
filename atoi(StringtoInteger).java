/*

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Ask below requirements to Interviewer : clear up

Requirements for atoi:
-------------------------
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

*/

public class Solution 
{
    public int myAtoi(String str) 
    {
        if(str == null || str.length()<1)
            return 0;
            
        str = str.trim(); // remove whitespaces
        
        int flag = 0; // 0 being positive, 1 being negative
        
		int i=0;
        if(str.charAt(0) == '-')
        { 
            flag = 1; // negative
            i++;
        }
        else if(str.charAt(0) == '+')
        {
            i++; // positive
        }
        
		double result = 0;
		
        while(i<str.length() &&  str.charAt(i) >='0' && str.charAt(i) <='9')
        {
            result = (result * 10) + (str.charAt(i) - '0'); //auto-boxing, here we want is casting not parsing for numerical value
			// http://stackoverflow.com/questions/7479599/why-cant-i-typecast-from-char-to-int
            i++;
        }
		
        if(flag == 1)
            result = result * (-1);
            
        return (int)result;    
    }
}