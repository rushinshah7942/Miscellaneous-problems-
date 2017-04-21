/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

*/

/*
The time complexity consists of 2 parts: the heapify process and the while loop.

We put (m + n) elements into the heap during the heapify process, so it's O(m + n) run time.

During the while loop, every cell is put into and take out of the heap at most once, and we are doing so in a BFS style, meaning that there is m + n elements in the heap at the worst case. So it is O(m * n * log(m + n)) in the worst case.

So the run time complexity would be =  O(m * n * log(m + n)).
The space complexity is obviously  = O(m * n) because of the visited array.
-------------------------------------------------------------

One tricky case that this solution handles very well is :

9 9 9 9 9 9 8 9 9 9 9
9 0 0 0 0 0 1 0 0 0 9
9 0 0 0 0 0 0 0 0 0 9
9 0 0 0 0 0 0 0 0 0 9
9 9 9 9 9 9 9 9 9 9 9

After you process 8, the downward 1 will be replaced as 8, instead of 1 as height.
---------------------------
Psuedo-code
--------------------------
For every point on the border set the water level to the point height
For every point not on the border set the water level to infinity
Put every point on the border into the set of active points
While the set of active points is not empty:
    Select the active point P with minimum level
    Remove P from the set of active points
    For every point Q adjacent to P:
        Level(Q) = max(Height(Q), min(Level(Q), Level(P)))
        If Level(Q) was changed:
            Add Q to the set of active points

*/

public class Solution {

    public class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.height - b.height; // sort height-wise into PriorityQueue
            }
        });
        
        int m = heights.length;
        int n = heights[0].length;
        
		boolean[][] visited = new boolean[m][n];

        // Initially, add all the Cells which are on borders to the queue.
        // columns
		for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }
		// rows
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }
		/*
		But it still works putting these cells into the queue twice though it causes extra workload. 
		When the same cell is polled the second time, its visited neighbors would not be visited one more time.
		*/

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{
									{-1, 0}, {1, 0}, {0, -1}, {0, 1}
								  };
        
		int res = 0;
        while (!queue.isEmpty()) {
        
			Cell cell = queue.poll(); // gets shortest cell
			
            for (int[] dir : dirs) {
                
				int row = cell.row + dir[0]; // gets the 4 neighbors
                int col = cell.col + dir[1];
				
				// boundary validation & check if its not visited already to avoid going into infinite loop
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res +=  Math.max(0, cell.height - heights[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height))); // copy the max height
					// imagine i.e. 8 1 0 ....
					// so 1st column will store 7 (for 1), but 2nd column should store 8 (for 0)
					// Hence, store that cell with changed height of maximum of its neighbors
                }
            }
        }
        
        return res;
    }
}