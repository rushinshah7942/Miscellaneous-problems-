/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

*/

public class Solution {
    public int longestPalindrome(String s) {
        int[] lowercase = new int[26];
		int[] uppercase = new int[26];
		int res = 0;
		for (int i = 0; i < s.length(); i++){
			char temp = s.charAt(i);
			if (temp >= 97) lowercase[temp-'a']++;
			else uppercase[temp-'A']++;
		}
		for (int i = 0; i < 26; i++){
			res+=(lowercase[i]/2)*2;
			res+=(uppercase[i]/2)*2;
		}
		return res == s.length() ? res : res+1;
    }
}