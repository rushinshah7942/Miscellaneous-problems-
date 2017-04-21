/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters.

*/

// Below solution makes 2-pass of a given string
public int firstUniqChar(String s) {
	int count [] = new int[26];

	for(int i = 0; i < s.length(); i ++)
		count [s.charAt(i) - 'a'] ++;
	
	for(int i = 0; i < s.length(); i ++)
		if(count [s.charAt(i) - 'a'] == 1)
			return i;
		
	return -1;

}


// Optimized : Just one pass of a given string
// We can augment the count array by storing not just counts but also the index of the first time you encountered the character 


class CountIndex{
	int frequency ;
	int index ;
}
public class Solution{
	public int firstUniqChar(String s) {
		
		CountIndex[] count = new CountIndex[26];

		for(int i=0;i<26;i++){
			count[i] = new CountIndex();
		}
		
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			int ind = c - 'a';
			int freq = count[ind].frequency;
			if(freq == 0){
				count[ind].index = i;
			}
			count[ind].frequency = freq + 1;
		}	
		boolean flag = false;
		int firstPos = Integer.MAX_VALUE;
		
		// second pass is not over the string
		// only the count array
		for(int i=0;i<26;i++){
			if(count[i].frequency == 1){ // only for unique characters
				if(count[i].index < firstPos){
					firstPos = count[i].index;
					flag = true;
				}
			}
		}
		if(flag == true)
			return firstPos;
		else
			return -1;
	}
}