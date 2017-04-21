/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/

public class Solution {
    public List<List<String>> partition(String s) {
       List<List<String>> res = new ArrayList<List<String>>();
       List<String> list = new ArrayList<String>();
       dfs(s,0,list,res);
       return res;
    }
   
	// If the input is "aab", check if [0,0] "a" is palindrome. then check [0,1] "aa", then [0,2] "aab".
	// While checking [0,0], the rest of string is "ab", use ab as input to make a recursive call.
    public void dfs(String s, int pos, List<String> list, List<List<String>> res){
        if(pos==s.length()) 
			res.add(new ArrayList<String>(list));
        else{
            for(int i=pos;i<s.length();i++){
                if(isPal(s,pos,i)){
                    list.add(s.substring(pos,i+1));
                    dfs(s,i+1,list,res);
                    list.remove(list.size()-1);
                }
            }
        }
    }
    
    public boolean isPal(String s, int low, int high){
        while(low<high){ 
			if(s.charAt(low++)!=s.charAt(high--)) 
				return false;
		}
        return true;
    }
}