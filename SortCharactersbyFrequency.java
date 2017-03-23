/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

// Using BucketSort
// Time : O(n)

public class Solution {
    public String frequencySort(String s) {
        char[] letters = s.toCharArray();
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        List<Character>[] buckets = new ArrayList[s.length()+1];
        
        for(char ch : letters){
            if(frequencyMap.containsKey(ch)){
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            }
            else
                frequencyMap.put(ch,1);
        }
        
        for(Map.Entry<Character,Integer> entry : frequencyMap.entrySet()){
            int frequency = entry.getValue();
            if(buckets[frequency] == null){
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(entry.getKey());
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = buckets.length -1 ; i >= 0 ; i--){
            if(buckets[i] != null){
                for(Character val : buckets[i]){
                    for(int size = 0 ; size < frequencyMap.get(val) ; size++)
                        sb.append(val);
                }    
            }
        }
        
        return sb.toString();
        
    }
}