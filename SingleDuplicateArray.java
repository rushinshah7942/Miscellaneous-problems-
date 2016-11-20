/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

/*
This solution is based on binary search.

At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the middle) and count all the numbers equal to or less than mid. Then if the count is more than mid, the search space will be [1 mid] otherwise [mid+1 n]. I do this until search space is only one number.

Let's say n=10 and I select mid=5. Then I count all the numbers in the array which are less than equal mid. If the there are more than 5 numbers that are less than 5, then by Pigeonhole Principle (https://en.wikipedia.org/wiki/Pigeonhole_principle) one of them has occurred more than once. So I shrink the search space from [1 10] to [1 5]. Otherwise the duplicate number is in the second half so for the next step the search space would be [6 10].
*/

// Reference:
// https://discuss.leetcode.com/topic/25580/two-solutions-with-explanation-o-nlog-n-and-o-n-time-o-1-space-without-changing-the-input-array

// Time O(n*logn)
// Space O(1)

public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length == 0 || nums == null)
			return 0;
		
		int low = 1, high = nums.length - 1, mid;
		
		while (low < high) {
			mid = low + (high - low) / 2;
			int count = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] <= mid)
					count++;
			}
			if (count > mid)
				high = mid;
			else
				low = mid + 1;
		}
		return low;
    }
}


// Time O(N)
// Space O(1)

public int findDuplicate(int[] nums) {
    
	// store index1 -> value1 -> index2 -> value2
	// here index2 will be nums[value1]
	
	int slow = 0, fast = 0;
    do{
        slow = nums[slow]; // node.next
        fast = nums[nums[fast]]; // node.next.next
    }while(slow != fast);
	
    slow = 0;
    while(slow != fast){
        slow = nums[slow];
        fast = nums[fast];
    }
    return slow;
}