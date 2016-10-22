/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/

// BRUTE_FORCE solution O(k*N) in worst case and O(1) space - 137 ms
public class Solution 
{
    public String removeKdigits(String A, int k) 
    {
        if (A.length() == k) {
            return "0";
        }
        
        for (int i = 0; i < k; i++) 
        {
            for (int j = 0; j < A.length(); j++) 
            {
                if (j == A.length() - 1 || A.charAt(j + 1) < A.charAt(j)) 
                {
                    A = remove(A, j);
                    break;
                }
            }
        }       
        int i = 0;
		
		// remove leading zeros
        while (i < A.length() - 1 && A.charAt(i) == '0') {
            i++;
        }
		
        if(!A.substring(i, A.length()).equals(""))
            return A.substring(i, A.length());
        else
            return "0";
    }
    private String remove(String A, int pos) 
	{
        return A.substring(0, pos) + A.substring(pos + 1, A.length());
    }
}


// O(N) time and O(N) space solution using Stack - 13 ms
public class Solution 
{
    public String removeKdigits(String num, int k) 
	{
        int digits = num.length() - k;
        char[] stack = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        for (int i = 0; i < num.length(); ++i) 
		{
            char c = num.charAt(i);
            while (top > 0 && stack[top-1] > c && k > 0) 
			{
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }
        // find the index of first non-zero digit
        int i = 0;
        while (i < digits && stack[i] == '0') i++;
        return i == digits? "0": new String(stack, i, digits - i);
    }
}

