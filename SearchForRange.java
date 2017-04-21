/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

// Divide and Conquer - Easy

public int[] searchRange(int[] nums, int target) 
{
    return helper(nums, target, 0, nums.length - 1);
}
private int[] helper(int[] nums, int target, int lo, int hi) 
{
	if (nums[lo] == target && nums[hi] == target) 
		return new int[]{lo, hi};
	
	if (nums[lo] <= target && nums[hi] >= target) 
	{
		int mid = lo + (hi - lo) / 2;

		int[] left = helper(nums, target, lo, mid);
		int[] right = helper(nums, target, mid + 1, hi);

		if (left[0] == -1) 
			return right;
		if (right[0] == -1) 
			return left;

		return new int[]{left[0], right[1]};
	}
	return new int[]{-1, -1};
}

// Two binary searches 

public  int[] searchRange(int[] nums, int target) {
	int hi = nums.length - 1;
	int low = 0;

	int[] rs = new int[2];
   // base case
	if(nums == null || nums.length == 0)
		return new int[]{-1, -1 };
	
   //left side
	while(low < hi){
		// mid for left
		int mid = low + (hi - low) /2;
		if(target > nums[mid]){
			low = mid + 1;
		}else{
			hi = mid;
		}
	}
	
	if(target == nums[low]){
		rs[0] = low;
	}else{
		rs[0] = -1;
	}
   
	//set right side
	hi = nums.length - 1;
	while(low < hi){
		int mid = (low + (hi - low) /2 ) + 1;
		
		if(target < nums[mid]){
			hi = mid - 1;
		}else{
			low = mid;
		}
	}   
	if(target == nums[hi]){
		rs[1] = hi;
	}else{
		rs[1] = -1;
	}
	
	return rs;
}
