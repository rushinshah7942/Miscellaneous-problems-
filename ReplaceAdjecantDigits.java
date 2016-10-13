/*
You are given an integer X. You must choose two adjacent digits and replace them with the larger of these two digits.

For example, from the integer X = 233614, you can obtain:
33614 (by replacing 23 with 3);
23614 (by replacing 33 with 3 or 36 with 6);
23364 (by replacing 61 with 6 or 14 with 4);

You want to find the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two. In the above example, the smallest such number is 23364.

Write a function:

class Solution {public int solution (int X);}
that, given a positive integer X, returns the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two.

For example, given X = 233614, the function should return 23364, as explained above.

Assume that:

X is an integer within the range [10..1,000,000,000].
*/


// Only focus on Correctness of the code, not the performance

class Solution {
    public int solution(int X) 
    {
        // write your code in Java SE 8
        String strNum = String.valueOf(X);
        int max = Integer.MIN_VALUE;
        int len = strNum.length();
        
        for(int i=0;i<len-1;i++)
        {
            double val1 = Character.getNumericValue(strNum.charAt(i));
            double val2 = Character.getNumericValue(strNum.charAt(i+1));
            
            int avgVal = (int)Math.round((val1+val2)/2);
            
            String part1 = strNum.substring(0,i);
            String part2 = strNum.substring(i+2,len);
            
            String compareVal = part1 + avgVal + part2;
            
            int compareInt = Integer.parseInt(compareVal);
            System.out.println("val "  + compareInt);
            if(compareInt > max)
                max = compareInt;
        }
        return max;
    }
}