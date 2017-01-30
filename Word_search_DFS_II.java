/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z.


Hint:
-----------------------
You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie

*/
 
//The Trie is formed by all the words in given words. Then during the DFS, for each current formed word, I check if it is in the Trie.

public class Solution {
    
    class TrieNode{
        String word;
        TrieNode[] children = new TrieNode[26];
    }
    
    public TrieNode buildTree(String[] words){
        TrieNode root = new TrieNode();
        
        for(String w : words){
            TrieNode node = root;
            char[] ch = w.toCharArray();
        
            for(char c : ch){
                int index = c - 'a';
                if(node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];    
            }
            node.word = w; // assign word at the end and go to the next word
        
        }
        return root;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        TrieNode root = buildTree(words); // build Trie tree from given words
        
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				helperDFS(board, i, j, root, result);
			}
		}
		return result;
    }
    
    
    public void helperDFS(char[][] board, int i, int j, TrieNode root, List<String> result){
        
        char c = board[i][j];
        
        if(c == '#' || root.children[c-'a'] == null) // same letter or word does not start from that letter
            return;
            
        // go to the next node in Trie
        root = root.children[c-'a'];
        
        if(root.word != null){
            // found our word
            result.add(root.word);
            root.word = null; // to avoid duplicate
        }
        board[i][j] = '#'; // to avoid using same letter
        
        if(i > 0) 
            helperDFS(board, i - 1, j ,root, result);
        if(j > 0)
            helperDFS(board, i , j - 1,root, result);
        if(i < board.length-1)
            helperDFS(board, i + 1, j ,root, result);
        if(j < board[0].length - 1)
            helperDFS(board, i , j + 1 ,root, result);
	
	    board[i][j] = c; // change back to original	
    }
}