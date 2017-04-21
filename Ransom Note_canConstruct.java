/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/

public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
		// frequency map
		int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}

/*
As the magazine could be much longer than the RansomNote, shortcutting the loop on the magazine will result in much better running complexity.

First build the HashMap for the RansomNote, then loop on the magazine, and return true as soon as you removed all the characters from the Map.

*/

public boolean canConstruct(String ransomNote, String magazine) {
	if(ransomNote.length() > magazine.length()) 
		return false;
	if(ransomNote.isEmpty() && magazine.isEmpty())  
		return true;
	
	Map<Character,Integer> charsCount = new HashMap<>();
	
	for(char c: ransomNote.toCharArray()) {
		if(charsCount.containsKey(c)) {
			charsCount.put(c, charsCount.get(c)+1);
		} else {
			charsCount.put(c, 1);
		}
	}
	for(char c: magazine.toCharArray()) {
		if(charsCount.containsKey(c)) {
			int count = charsCount.get(c);
			count--;
			if(count == 0) 
				charsCount.remove(c);
			else 
				charsCount.put(c, count);
		}
		if(charsCount.keySet().size()==0) {
			return true; // keep checking
		}
	}
	return false;
}