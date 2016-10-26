/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

*/

public class Solution
{
    public boolean isIsomorphic(String s, String t) 
    {
        if(s==null && t==null)
            return true;
        if(s==null || t==null)
            return false;
        
        // if strings have only alphabetic then, define 26 size array
        // Clarify in interview
        int[] m1 = new int[256]; 
        int[] m2 = new int[256]; 
        for (int i = 0; i < 256; i++) 
        {
            m1[i] = m2[i] = -1;
        }
        int n = s.length();
        for (int i = 0; i < n; ++i) 
        {
            //System.out.println("before m1:" + m1[s.charAt(i)]+ " m2:" + m2[t.charAt(i)]);
            if (m1[s.charAt(i)] != m2[t.charAt(i)])
                return false;
            
            m1[s.charAt(i)] = i;
            m2[t.charAt(i)] = i;
            //System.out.println("after m1:" + m1[s.charAt(i)]+ " m2:" + m2[t.charAt(i)]);
        }
        return true;
    }
}