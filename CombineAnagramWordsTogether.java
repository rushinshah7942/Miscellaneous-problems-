/*
Given an array of words, print all anagrams together. For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”}, then output may be “cat tac act dog god”.
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// O(n*logn * m)
public class PrintAnagramsTogether
{
	public static void main(String[] args){
		String[] str = {"cat", "dog", "tac", "god", "act"};
		String[] result  = printTogether(str);
		System.out.println("all anagram words combined together");
		for(String s : result){
			System.out.print(s + " ");
		}
	}

	private static String[] printTogether(String[] str) {
		
		String[] ans = sortAllWords(str);
		// sorted each word till now
		HashMap<String,String> map = new HashMap<>();
		
		int i=0;
		for(String s : ans){
			if(map.containsKey(s)){
				map.put(s,map.get(s)+","+str[i++]);
			}
			else{
				map.put(s, str[i++]);
			}
		}
		System.out.println("hashmap" + map);
		i=0;
		for(Map.Entry<String,String> entry : map.entrySet()){
			String all = entry.getValue();
			String[] vals =  all.split(",");
			for(String s :  vals){
				ans[i++] = s;
			}
		}
		return ans;
	}
	
	// sorts each alphabets in the words of a string array
	public static String[] sortAllWords(String[] str){
		
		String[] result = new String[str.length];
		int i= 0;
		
		for(String s : str){
			char[] temp = s.toCharArray();
			Arrays.sort(temp);
			String tmp = new String(temp);
			result[i++] = tmp;
		}
		
		return result;
	}
}


// Another solution

public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, ArrayList<String>> map = new HashMap<>();
    
	for(String str: strs){
		
        // below is the main logic
		// If two strings are anagram to each other, their sorted sequence is the same.
		char[] arr = new char[26];
        for(int i=0; i<str.length(); i++){
            arr[str.charAt(i)-'a']++;
        }
        String ns = new String(arr);
 
        if(map.containsKey(ns)){
            map.get(ns).add(str);
        }else{
            ArrayList<String> al = new ArrayList<String>();
            al.add(str);
            map.put(ns, al);
        }
    }
    result.addAll(map.values());
 
    return result;
}

/*
Time Complexity
---------------
If the average length of verbs is m and array length is n, then the time is O(n*m).

If you sort, each word and combine into hashmap appending by semi-colon will require O(m* n* log n). 
*/