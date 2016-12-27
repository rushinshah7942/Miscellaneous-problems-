/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/

public class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
		
		// First time number appear -> save it in "ones"
		// Second time -> clear "ones" but save it in "twos" for later check
		// Third time -> try to save in "ones" but value saved in "twos" clear it.

            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        
		}
        
		return ones;
    }
}

