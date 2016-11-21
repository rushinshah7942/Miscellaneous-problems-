/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

// Time O(N)
// Space O(N) using stack
public class Solution {
    public int trap(int[] height) {
        if (height==null) 
            return 0;
		
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;        
        
		while (i < height.length){
            if (s.isEmpty() || height[i]<=height[s.peek()]){
                s.push(i++);
            }
            else {
                int bottom = s.pop();
				// The main idea is : if we want to find out how much water on a bar(bottom), we need to find out the left larger bar's 
				// index (il), and right larger bar's index(ir), so that the water is (min(A[il],A[ir])-A[bottom])*(ir-il-1), use min since only // the lower boundary can hold water, and we also need to handle the edge case that there is no il.
                maxBotWater = 
					s.isEmpty()? 0 : (Math.min(height[s.peek()],height[i]) - height[bottom]) * (i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
}

// Time O(N)
// Space O(1)
public int trap(int[] A){
    int a=0;
    int b=A.length-1;
    int max=0;
    int leftmax=0;
    int rightmax=0;
    while(a<=b){
        leftmax=Math.max(leftmax,A[a]);
        rightmax=Math.max(rightmax,A[b]);
        if(leftmax<rightmax){
            max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
            a++;
        }
        else{
            max+=(rightmax-A[b]);
            b--;
        }
    }
    return max;
}
