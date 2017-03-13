/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
    
		if (nums == null) 
			return 0;
		
		int first = 0, last = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		
		while (last < nums.length){
			
			sum += nums[last++]; // this statement is processed only when sum is smaller then s.
			
			//first will never be greater than last here, since when first meet last-1 (since last is increased in the last step), sum will reach zero.
			while (sum >= s) 
			{
				min = Math.min(min, last-first);
				sum -= nums[first++];
				if(sum == 0) 
					return 1; //This means nums[first-1] = s, thus the shortest length is 1.
			}
		}
		return min == Integer.MAX_VALUE?  0 : min;      
    }
}