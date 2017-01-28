/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/


// worst case O(m+n)

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1)
            return false;
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(target > matrix[i][col-1])
                    break; // go to next row
                else if(target == matrix[i][j])
                    return true;
            }
        }
        return false;
    }
}

// we can take advantge of sorted matrix and do binary search 
// n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
// an array convert to n * m matrix => a[x] => matrix[x / m][x % m];

// O(log(m*n))
// as there would be m*n elements to be processed in new array

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		
		int start = 0, n = matrix.length, m = matrix[0].length;
		int end = m * n - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			if (matrix[mid / m][mid % m] == target) {
				return true;
			} 
			if (matrix[mid / m][mid % m] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
	}
}