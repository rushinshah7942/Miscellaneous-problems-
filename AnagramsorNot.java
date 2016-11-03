/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.
*/

// if unicode characters are not included
public boolean isAnagram(String s, String t) {
	if(s.length() != t.length()) 
		return false;
	int [] a = new int [26];
	for(Character c : s.toCharArray()) 
		a[c - 'a']++;
	for(Character c : t.toCharArray()) {
		if(a[c -'a'] == 0) return false;
		a[c - 'a']--;
	}
	return true;
}

// if unicode characters are included
public class Solution 
{
    // handles unicode characters
    public boolean isAnagram(String s, String t) {
        
        if(s == null && t == null)
            return true;       
        if(s == null || t == null)
            return false;
        if(s.length() != t.length())
            return false;
        
            
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c:s.toCharArray()){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);    
            }
            else{
                map.put(c,1);
            }
        }
        
        for(char c : t.toCharArray()){
            if(map.containsKey(c)){
                if(map.get(c) == 0)
                    return false;
                    
                map.put(c,map.get(c)-1);
            }
            else
                return false;
        }
        return true;
    }
}