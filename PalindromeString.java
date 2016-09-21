/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class Solution 
{
    public boolean isPalindrome(String s) 
    {
        if(s == null || s.length() == 1)
            return true;
        
        String str = s.toLowerCase();
        
        int left = 0;
        int right = str.length()-1;
        
        while(left <= right)
        {
            while(!((str.charAt(left) <= 'z' && str.charAt(left) >= 'a') || 
					(str.charAt(left) >= '0' && str.charAt(left) <= '9')))
            {
				// if pointers collide
                if(left >= right)
                    break;
                    
                left++;
            }
            while(!((str.charAt(right) <= 'z' && str.charAt(right) >= 'a') || 
			        (str.charAt(right) >= '0' && str.charAt(right) <= '9')))
            {
				// if pointers collide
                if(left >= right)
                    break;
                    
                right--;
            }
            if(left<=right)
            {
                if(str.charAt(left) != str.charAt(right))
                    return false;
            }
            else
                break;
                
            left++;
            right--;
        }
        return true;
        
    }
}