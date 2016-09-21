/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/
public class Solution 
{
    public int lengthOfLastWord(String s) 
    {
        if(s == null || s.equals(" "))
            return 0;
        
        if(s.trim().length() == 0)
            return 0;
            
        String[] temp = s.split(" ");
        
        if(temp.length == 1)
        {
            // no last word
            return s.trim().length();
        }
        
        return temp[temp.length-1].length(); 
    }
}