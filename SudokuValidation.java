/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

*/

public boolean isValidSudoku(char[][] board) 
{
    for(int i = 0; i<9; i++)
	{
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Character> columns = new HashSet<Character>();
        HashSet<Character> cube = new HashSet<Character>();
        
		for (int j = 0; j < 9;j++)
		{
            if(board[i][j]!='.' && !rows.add(board[i][j])) // hashset add() returns 'true' if set didn't contain the specified element
                return false;

            if(board[j][i]!='.' && !columns.add(board[j][i]))
                return false;
        
			int RowIndex = 3*(i/3); // horizontal traversal for block, rowIndex takes values 0 3 6
            int ColIndex = 3*(i%3);  // vertical traversal for block, colIndex takes values 0 3 6
			
            // for good explanation of rowIndex and colIndex
			// https://discuss.leetcode.com/topic/9748/shared-my-concise-java-code/6
			
			if( board[RowIndex + j/3][ColIndex + j%3]!='.' && 
				!cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                return false;
        }
    }
    return true;
}



///------------------------


public boolean isValidSudoku(char[][] board) {
	
   boolean[][] row = new boolean[9][9];
   boolean[][] column = new boolean[9][9];
   boolean[][] block = new boolean[9][9];
   
   for(int i = 0;i<9;i++){
	   for(int j=0;j<9;j++){
			int c = board[i][j] - '1';       
			if(board[i][j]=='.'){
				continue;
			}
			if(row[i][c]||column[j][c]||block[i - i % 3 + j / 3][c]){
				return false;
			}
			row[i][c] = column[j][c] = block[i - i % 3 + j / 3][c] = true;
	   }
   }
   return true;
}
