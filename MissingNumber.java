/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

// Solution-1
// If given array is sorted, then do binary search
public int missingNumber(int[] nums) { //binary search
    Arrays.sort(nums);
    int left = 0, right = nums.length, mid= (left + right)/2;
    
	while(left<right){
        mid = (left + right)/2;
    
		if(nums[mid]>mid) // index-based binary-search
			right = mid; // there are already larger numbers on right side, check in left-side
        else 
			left = mid+1;
    }
    return left; // return index where element could fit in
}

// Solution-2
// Knowing n ^ n = 0 and n ^ 0 = n this would be very simple 
public class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
}

// Solution-3
// Math problem
// Sum upto n values = n(n+1)/2;

public int missingNumber(int[] nums) {
	
	int n = nums.length;
	int sum = n*(n + 1)/2;
	
	for(int i : nums)
		sum-= i;
	
	return sum;
}