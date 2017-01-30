/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        
        if(board == null || board.length < 0)
            return false;
  
        if(word == null || word.length() < 1)
            return true;
            
        char[] ch = word.toCharArray();
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                // start with 0th index and check if it reaches end of string
                if(helperDFS(board,ch,0,i,j))
                    return true;
            }
        }
        return false;
    }
    public boolean helperDFS(char[][] board, char[] ch, int index, int i, int j){
        if(index == ch.length)
            return true;
            
        // checking border conditions
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
            
        // at any time, if current char is not same of board position, return false    
        if(board[i][j] != ch[index])
            return false;
            
        char temp = board[i][j];
        board[i][j] = '#';   // mark used character 
        
        // pretty standard DFS logic
        boolean check = helperDFS(board,ch,index+1,i,j-1) ||
                        helperDFS(board,ch,index+1,i,j+1) ||
                        helperDFS(board,ch,index+1,i-1,j) ||
                        helperDFS(board,ch,index+1,i+1,j);
                        
        // now reset the character to check other possibilities for different row and/or column
        board[i][j] = temp;
        
        return check;
    }
}