/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

public class Solution 
{
    public boolean wordPattern(String pattern, String str) 
    {
        String[] words = str.split(" ");
    
        if (words.length != pattern.length())
            return false;
        
        Map map = new HashMap();
        
        // Look closely at the use of Integer, not int
        // allows comparing with just != because there's no autoboxing-same-value-to-different-objects-problem anymore
        // put method call returns the previous value associated with key, or null if there was no mapping for key.
        for (Integer i=0; i<words.length; ++i)
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i)) // put returns value or null  
                return false;
        
        return true;    
    }
}