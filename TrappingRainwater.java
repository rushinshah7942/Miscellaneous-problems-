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
                s.push(i++); // we add index, not the number , as we need to find distance between bars
				// that information will be lost if we do not store index
            }
            else {
                int bottom = s.pop();
				// The main idea is : if we want to find out how much water on a bar(bottom), we need to find out the left larger bar's 
				// index (il), and right larger bar's index(ir), so that the water is 
				// (min(height[il],height[ir])-height[bottom])*(ir-il-1), use min since only 
				// the lower boundary can hold water, and we also need to handle the edge case that there is no il.
                maxBotWater = 
					s.isEmpty()? 0 : (Math.min(height[s.peek()],height[i]) - height[bottom]) * (i-s.peek()-1);
					// e.g. two cases
					// (a) 1 0 2
					// (b) 2 0 1 
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
}

// Time O(N)
// Space O(1)
public int trap(int[] height){
    int a=0;
    int b=height.length-1;

    int max=0;
    int leftmax=0;
    int rightmax=0;

    while(a<=b){
        
		leftmax=Math.max(leftmax,height[a]);
        rightmax=Math.max(rightmax,height[b]);
        
		if(leftmax<rightmax){
            max += (leftmax-height[a]);       // leftmax is smaller than rightmax, so the (leftmax-height[a]) water can be stored
            a++;
        }
        else{
            max+=(rightmax-height[b]);
            b--;
        }
    }
    return max;
}

/*
Your input

[0,1,0,2,1,0,1,3,2,1,2,1]

Your stdout

a:0 b: 11 leftmax: 0 rightmax: 0
a:1 b: 11 leftmax: 0 rightmax: 1
a:1 b: 10 leftmax: 1 rightmax: 1
a:2 b: 10 leftmax: 1 rightmax: 2
a:3 b: 10 leftmax: 1 rightmax: 2
a:3 b: 9 leftmax: 2 rightmax: 2
a:3 b: 8 leftmax: 2 rightmax: 2
a:3 b: 7 leftmax: 2 rightmax: 2
a:4 b: 7 leftmax: 2 rightmax: 3
a:5 b: 7 leftmax: 2 rightmax: 3
a:6 b: 7 leftmax: 2 rightmax: 3
a:7 b: 7 leftmax: 2 rightmax: 3
*/
