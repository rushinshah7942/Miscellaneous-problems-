/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
*/

public class Solution 
{
    public String reverseWords(String s) 
    {
        /*
            \\s - matches single whitespace character
            \\s+ - matches sequence of one or more whitespace characters.
        */
        
        String[] parts = s.trim().split("\\s+");
    
        // using StringBuilder will be optimum in case of larger string text 
        // as String object is immutable, for adding each word to String will create many temp objects
        // and later garbage collected
        
    
        //String out = "";
        StringBuilder sb = new StringBuilder();
        
        for (int i = parts.length - 1; i > 0; i--) 
        {
            sb.append(parts[i]).append(" ");
          //  out += parts[i] + " ";
        }
        sb.append(parts[0]);
        //return out + parts[0];    
        return sb.toString();
    }
}