/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

public class Solution {
    public boolean isHappy(int n) 
    {
        int num = n;
        
        if(num == 0)
            return false;
        if(num == 1)
            return true;
            
        Set<Integer> hs = new HashSet<Integer>();
        
        while(num != 1)
        {
            String str = String.valueOf(num);
            int sum = 0;
            
            for(int i=0;i<str.length();i++)
            {
                sum += Math.pow(Integer.parseInt(String.valueOf(str.charAt(i))),2);
            }
            if(hs.contains(sum))
                return false;
            
            hs.add(sum);
            num = sum;
        }
        return true;
    }
}