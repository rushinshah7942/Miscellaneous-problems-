/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.


*/

public class Solution {
    public int findMin(int[] nums) {
		int l = 0, r = nums.length-1;
		while (l < r) {

			int mid = (l + r) / 2;
			if (nums[mid] < nums[r]) {
				r = mid;
			} 
			else if (nums[mid] > nums[r]){
				l = mid + 1;
			} 
			else{  
				r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
			}
		}
		return nums[l];
	}
}