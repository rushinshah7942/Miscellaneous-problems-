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
        if(i < 0 || i >= grid.length)
        {
            return;
        }
        if(j < 0 || j >= grid[i].length)
        {
            return;
        }
        
        //island edge detect
        if(grid[i][j] == '0')
        {
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