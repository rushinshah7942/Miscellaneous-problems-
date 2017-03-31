/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
*/



// My solution: To check if a number is ugly, divide the number by greatest divisible powers of 2, 3 and 5, if the number becomes 1 then it is an ugly number otherwise not.

public class Solution 
{
    public boolean isUgly(int num) 
    {
        if(num == 0) // base condition
            return false;
            
        if(num == 1) // main return condition
            return true;
        
        if(num%2 == 0)
        {
            num = num/2;
            return isUgly(num);
        }
        if(num%3 == 0)
        {
            num = num/3;
            return isUgly(num);
        }
        if(num%5 == 0)
        {
            num = num/5;
            return isUgly(num);
        }
        return false;
		
		
		/*
		if(num==1) 
			return true;
		
		if(num==0) 
			return false;
		
		while(num%2==0) num=num/2;
		while(num%3==0) num=num/3;
		while(num%5==0) num=num/5;
		
		return num==1;
		*/		
    }
}