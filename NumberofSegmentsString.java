/*
Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5

*/

public class Solution {
    public int countSegments(String s) {
        
        if(s == null || s.trim().length() < 1)
            return 0;
            
        String[] val = s.trim().split("\\s+");
        
        return val.length;
        
    }
}

// without using trim()

public int countSegments(String s) {
    int res=0;
    for(int i=0; i<s.length(); i++)
        if(s.charAt(i)!=' ' && (i==0 || s.charAt(i-1)==' '))
            res++;        
    return res;
}

Time complexity:  O(n)
Space complexity: O(1)