/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/

// https://en.wikipedia.org/wiki/Digital_root

public class Solution 
{
    public int addDigits(int num) 
    {
        /*
        // with loop
        String str = String.valueOf(num);
        
        if(str.length() == 1)
            return num;

        int sum = 0;            
        while(str.length() != 1)
        {
            for(int i=0;i<str.length();i++)
            {
                sum += Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            str = String.valueOf(sum);
            sum = 0;
        }
        return Integer.parseInt(str);*/
        
        if(num<10)
            return num;
        else if(num%9 ==0)
            return 9;
        else
            return num%9;
    }
}