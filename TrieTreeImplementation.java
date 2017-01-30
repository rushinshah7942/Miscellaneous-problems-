/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

*/


public class Trie {

    /** Initialize your data structure here. */
    public Trie() {
        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
  
/*
-----------------
Solution         |
----------------- 
*/

class TrieNode{
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26]; // maximum children of root can be 26 i.e. from a-z
}

public class Trie {

    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(); // empty node as root    
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        
        TrieNode node = root; // get the root
        
        char[] ch = word.toCharArray();
        
        for(char c : ch){
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a']; // go to the next node in tree
        }
        node.isWord = true; // mark that word as true
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] ch = word.toCharArray();
        TrieNode node = root;
        
        for(char c : ch)
        {
            if(node.children[c-'a'] == null)
                return false;
            
            node = node.children[c-'a'];
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] ch = prefix.toCharArray();
        
        for(char c : ch)
        {
            if(node.children[c-'a'] == null)
                return false;
            
            node = node.children[c-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
 
 
/*

- Trie is an efficient information retrieval data structure. Using trie, search complexities can be brought to optimal limit (key length). 
- If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree. 
- Using trie, we can search the key in O(M) time. However the penalty is on trie storage requirements.

----------
Big-O	 |
----------
insert and search -> O(key_length)
space -> O(N * key_length * alphabet_size)

*/ 