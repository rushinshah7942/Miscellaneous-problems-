/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.
*/

public class Solution 
{
    public int maxArea(int[] height) 
    {
        int left =0;
        int right = height.length-1;
        
        int max = 0,area;
        
        while(left < right)
        {
            int left_val = height[left];
            int right_val =height[right];
            
            if(left_val <= right_val)
            {
                //multiply with lowest value to make container not slant
                area = (right - left) * left_val;
                
                // this speeds up a lot  
                // omit any smaller left height value to get maximum area
                while (height[++left] < left_val);
            }
            else
            {
                area = (right - left) * right_val;
                while (height[--right] <= right_val);
            }
            if(area > max)
            {
                max = area;
            }
        }
        return max;
    }
}