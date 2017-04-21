/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

// DFS
// Time Complexity : we visit the whole grid twice. So it is O(m*n)

public class Solution 
{
    public int numIslands(char[][] grid) 
    {
        int count = 0;
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == '1')
                {
                    disappear(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }
    
    //Use for disapearing an island
    public void disappear(int i, int j, char[][] grid)
    {
        //array edge detect
        if(i < 0 || i >= grid.length){
            return;
        }
        if(j < 0 || j >= grid[i].length){
            return;
        }
        
        //island edge detect
        if(grid[i][j] == '0'){
            return;
        }
        
        //disapear this cell
        grid[i][j] = '0';
        //disapear other cell in the same island
        disappear(i + 1, j, grid);
        disappear(i - 1, j, grid);
        disappear(i, j + 1, grid);
        disappear(i, j - 1, grid);
    } 
}

// BFS approach
// Time O(m *n)
public class Solution {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int islands = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    islands++;
                    BFS(grid,i,j);
                }
            }
        }
        return islands;
    }
    private void BFS(char[][] grid, int x, int y){
        grid[x][y] = '0';
        Queue<Point> q = new LinkedList<Point>();
        q.offer(new Point(x,y));
        while(q.size()>0){
            int size = q.size();
            Point p = q.poll();
            for(int i=0;i<size;i++){
                for(int[] dir:dirs){
                    int x1 = p.x+dir[0];
                    int y1 = p.y+dir[1];
                    if(x1>=0 && y1>=0 && x1< grid.length && y1<grid[0].length && grid[x1][y1]=='1'){
                        grid[x1][y1] = '0';
                        q.offer(new Point(x1,y1));
                    }
                }
            }
        }
    }
}
class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}


// Psuedo-code

function islandCounter(M):
   islands = 0
   if (M == null OR M.length == 0):
      return 0
   m = M.length # number of rows
   n = M[0].length # number of columns

   for i from 0 to m-1
      for j from 0 to n-1
         if (M[i][j] == 1):
            markIsland(M, m, n, i, j)
            islands++

			
function markIsland(M, m, n, i, j):
   q = new Queue
   q.push([i,j])
   while (!q.isEmpty()):
      item = q.pop()
      x = item[0]
      y = item[1] 
      if (M[x][y] == 1):
         M[x][y] = 2
         pushIfValid(q, m, n, x-1, y)
         pushIfValid(q, m, n, x, y-1)
         pushIfValid(q, m, n, x+1, y)
         pushIfValid(q, m, n, x, y+1)

function pushIfValid(q, m, n, x, y):
   if (x>=0 AND x<m AND y>=0 AND y<n):
      q.push([x,y])