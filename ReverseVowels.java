/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/

public class Solution 
{
    public String reverseVowels(String s) 
    {
        if(s == null || s.trim().length() < 2)
            return s;
        
        List<Character> ls = new ArrayList<Character>();
        HashSet<Character> hs = new HashSet<Character>();
        hs.add('a');
        hs.add('e');
        hs.add('i');
        hs.add('o');
        hs.add('u');
        hs.add('A');
        hs.add('E');
        hs.add('I');
        hs.add('O');
        hs.add('U');
        
        // add all vowels to ArrayList
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(hs.contains(ch))
            {
                // amortized run time of o(n) in total
                ls.add(ch);
            }
        }
        
        // Now whenever vowel is found in input, start adding vowels from ArrayList in reverser order
        int cntr = ls.size()-1;
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(hs.contains(ch))
            {
                // HashSet contains - constant time
                // arraylist get is constant time
                sb.append(ls.get(cntr--));
            }
            else
                sb.append(ch);
        }
        
        return sb.toString();
    }
}