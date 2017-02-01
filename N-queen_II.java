/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/


public class Solution {
    int count=0;
    public int totalNQueens(int n) {
		
		// start with column 0
        dfs(new int[n],0,n);
        return count;
    }
    public void dfs(int[] pos,int step,int n) {
        if(step==n) {
            count++;
            return;
        }
        for(int i=0;i<n;i++) {
			
			// for given step(column), check row by row (i) 
            pos[step]=i; 
            if(isvalid(pos,step)) 
				dfs(pos,step+1,n);
        }
    }
    public boolean isvalid(int[] pos, int step) {
        for(int i=0;i<step;i++) {
            if(pos[i] == pos[step]||(Math.abs(pos[i]-pos[step])) == (step-i)) 
				return false;
        }
        return true;
    } 
}