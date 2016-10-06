/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

*/

public class Solution 
{
    public boolean isPowerOfFour(int num) 
    {
        /*
          while(num>0)
          {
            if(num==1)
            {
                return true;
            }
 
            if(num%4!=0)
            {
                return false;
            }
            else
            {
                num=num/4;
            }
        }
        return false;
        */
        
          if(num==0) return false;
 
   int pow = (int) (Math.log(num) / Math.log(4));
   System.out.println("pow:" +pow);
   if(num==Math.pow(4, pow))
   {
       return true;
   }
   else
    {
       return false;
    }
    }
}