/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

*/

// Time -> O(n*n)
// Space -> O(n*n)

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        dfs(board,0,result);
        return result;
    }
    public void dfs(char[][] board, int col, List<List<String>> result){
        if(col == board.length){
            result.add(construct(board)); // add one of the solutions
            return;
        }
        
        for(int i = 0; i < board.length; i++) {
            // check row by row if current queen is safe
            if(validate(board, i, col)) {
                board[i][col] = 'Q'; // safe
                dfs(board, col + 1, result); // place next queen to next column
                board[i][col] = '.'; // change back to find next solution
            }
        }
    }
    
    // For 135 degree, they are in a line whose slope is -1. So (y-j)/(x-i) = -1 -> y + x = i + j. 
    // For the horizontally one, x = i. 
    // And for the 45 degree one, the line slope is 1, so (y-j)/(x-i) = 1 -> y + i = x + j.
                
    
    private boolean validate(char[][] board, int x, int y) {
    
        // only check on left side of current column, as we have not placed any queen right, and we are from left to right
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
                // there is already a queen there
                // or 
                // there is queen on 3 places i.e. on 135 degree, horizontally left and 45 degree
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                    return false;
            }
        }
        
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> result = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]); // go row by row
            result.add(s);
        }
        return result;
    }
}