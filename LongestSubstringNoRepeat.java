/*
	Given a string, find the length of the longest substring without repeating characters.
	
	Examples:

		Given "abcabcbb", the answer is "abc", which the length is 3.
		Given "bbbbb", the answer is "b", with the length of 1.
		Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/


/* 
	In the worst case: ababababababa
*/
public class Solution 
{
    public int lengthOfLongestSubstring(String s) 
    {
        int maxLength = 0;
        HashMap<Character, Integer> hp = new HashMap<Character, Integer>();
        
        char[] array = s.toCharArray();
        
        for(int i=0;i<array.length;i++)
        {
            if(!hp.containsKey(array[i]))
            {
                hp.put(array[i],i);
            }
            else
            {
				// when found repeating character, compare maxlength with hashmap size and then clear hashmap
                maxLength = Math.max(maxLength,hp.size());
                // important step
				// e.g. dvdf --> we need to start from -v-df
				i = hp.get(array[i]);
                hp.clear();
            }
        }
        return Math.max(maxLength,hp.size());
    }
}
