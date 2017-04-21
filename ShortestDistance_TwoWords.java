/*
LeetCode – Shortest Word Distance (Java)
 
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

*/
public int shortestDistance(String[] words, String word1, String word2) {
   int m=-1;
   int n=-1;
 
   int min = Integer.MAX_VALUE;
 
   for(int i=0; i<words.length; i++){
        String s = words[i];
        if(word1.equals(s)){
            m = i;
            if(n!=-1)
                min = Math.min(min, m-n);
        }else if(word2.equals(s)){
            n = i;
            if(m!=-1)
                min = Math.min(min, n-m);
        }
   }
 
   return min;
}

/*
his is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
*/

public class WordDistance {
    HashMap<String, ArrayList<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<String, ArrayList<Integer>>();
        for(int i=0; i<words.length; i++){
            if(map.containsKey(words[i])){
                map.get(words[i]).add(i);
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
	/*
	->   O(n^2)
	
    public int shortest(String word1, String word2) {
 
        ArrayList<Integer> l1 = map.get(word1);
        ArrayList<Integer> l2 = map.get(word2);
 
        int result = Integer.MAX_VALUE;
        for(int i1: l1){
            for(int i2: l2){
                result = Math.min(result, Math.abs(i1-i2));
            }
        }
        return result;
    }*/
	
	// Merge sort Logic 
	// O(m + n)
	public int shortest(String word1, String word2) {
	 
		ArrayList<Integer> l1 = map.get(word1);
		ArrayList<Integer> l2 = map.get(word2);
	 
		int result = Integer.MAX_VALUE;
		int i=0; 
		int j=0;
		while(i<l1.size() && j<l2.size()){
			result = Math.min(result, Math.abs(l1.get(i)-l2.get(j)));
			if(l1.get(i)<l2.get(j)){
				i++;
			}else{
				j++;
			}     
		}
	 
		return result;
	}
	
}

/*
This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.
*/

//Shortest Word Distance I:

public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index = -1;
        int min = words.length;
        for (int i = 0; i < words.length; i++) {
            
			if (words[i].equals(word1) || words[i].equals(word2)) {
            
				if (index != -1 && !words[index].equals(words[i])) { // check that we are not doing for same word
                    min = Math.min(i - index, min); 
                }
                index = i;
            }
        }
        return min;
    }
}

//Shortest Word Distance III:

public class Solution {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int index = -1;
		int min = words.length;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1) || words[i].equals(word2)) {
				if (index != -1 &&  (word1.equals(word2) || !words[index].equals(words[i]) ) ) {
					// two words are same || if we are not checking for same word again
					min = Math.min(i - index, min);
				}
				index = i;
			}
		}
		return min;
	}
}
	
// Another solution
	
public int shortestWordDistance(String[] words, String word1, String word2) {
    long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
    boolean same = word1.equals(word2);
    for (int i=0; i<words.length; i++) {
        if (words[i].equals(word1)) {
            if (same) {
                i1 = i2;
                i2 = i;
            } else {
                i1 = i;
            }
        } else if (words[i].equals(word2)) {
            i2 = i;
        }
        dist = Math.min(dist, Math.abs(i1 - i2));
    }
    return (int) dist;
}	