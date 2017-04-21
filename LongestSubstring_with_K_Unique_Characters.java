/*
Find the longest substring with k unique characters in a given string. Examples:

"aabbcc", k = 1
Max substring can be any one from {"aa" , "bb" , "cc"}.

"aabbcc", k = 2
Max substring can be any one from {"aabb" , "bbcc"}.

"aabbcc", k = 3

There are substrings with exactly 3 unique characters
{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
Max is "aabbcc" with length 6.

"aaabbb", k = 3
There are only two unique characters, thus show error message. 

*/

/*
Brute-force Approach
------------------------
If the length of string is n, then there can be n*(n+1)/2 possible substrings. A simple way is to generate all the substring and check each one whether it has exactly k unique characters or not. If we apply this brute force, it would take O(n2) to generate all substrings and O(n) to do a check on each one. Thus overall it would go O(n3).
*/


// Best Solution
// ----------------------------
// A more generic solution as follows, can be solution for Unicode string:
// Time O(n)
// Space O(n)
public int lengthOfLongestSubstringKDistinct(String s, int k) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0;
    int best = 0;
    for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        map.put(c, map.getOrDefault(c, 0) + 1);
        while (map.size() > k) {
            char leftChar = s.charAt(left); // get the left (first) character
            if (map.containsKey(leftChar)) {
                map.put(leftChar, map.get(leftChar) - 1);                     
                if (map.get(leftChar) == 0) { 
                    map.remove(leftChar); // when count reaches 0
                }
            }
            left++;
        }
        best = Math.max(best, i - left + 1);
    }
    return best;
} 
/*
Time : O(n)

The problem can be solved in O(n). Idea is to maintain a window and add elements to the window till it contains less or equal k, update our result if required while doing so. If unique elements exceeds than required in window, start removing the elements from left side.

*/

static String findLongestSubstring(String s) {
	int k = 2;
	int[] count = new int[256];
	int num = 0, i = 0,  res = 0;
	String output ="";

	for (int j = 0; j < s.length(); j++) {
		if (count[s.charAt(j)]++ == 0) // only when new character comes while scanning string
			num++;
		if (num > k) {
			while (--count[s.charAt(i++)] > 0);
			num--;
		}
		if(res<j-i+1 && num == k){
			res = j-i+1;
			output = s.substring(i,j+1);
		}
	  
		
	}
	return output;
}
