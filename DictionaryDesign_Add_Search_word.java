/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

*/

public class WordDictionary {

    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

 
 
//---------
// Solution| 
//---------

public class WordDictionary {
    
	class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word = "";
    }
    
    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
	// Using backtrack to check each character of word to search.
    private boolean match(char[] ch, int index, TrieNode node) {
        
		if (index == ch.length) 
			return !node.word.equals(""); // or you can use "node.isWord" logic    
        
		// if character matches then go to next node usin backtracking logic
		if (ch[index] != '.') {
            return node.children[ch[index] - 'a'] != null && match(ch, index + 1, node.children[ch[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) { // check for all children and even one of them is true, just return true
                    if (match(ch, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}