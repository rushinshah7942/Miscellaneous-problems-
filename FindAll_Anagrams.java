/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
    
		List<Integer> list = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0) 
			return list;
		
		int[] hash = new int[256]; //character hash
		
		//record each character in p to hash
		for (char c : p.toCharArray()) {
			hash[c]++;
		}
		//two points, initialize count to p's length
		int left = 0, right = 0, count = p.length();
		
		while (right < s.length()) {
			
			if (hash[s.charAt(right)] >= 1) {
				count--; // found one of the characters of p's anagram
			}
			
			hash[s.charAt(right)]--;
			right++;
			
			if (count == 0) { // found one of the anagrams in String s
				list.add(left); // left is startting index of anagram
			}
			
			// crossed sliding window size of p.length()
			if (right - left == p.length() ) {
			   
				// only increase the count if character is in p
				if (hash[s.charAt(left)] >= 0) {
					count++;
				}
				hash[s.charAt(left)]++; // reset the hash, we are gonna move left by 1
				left++;			
			}
		}
		return list;
    }
}