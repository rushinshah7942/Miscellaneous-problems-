/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

*/

public class Solution {

    public Solution(int[] nums) {
        
    }
    
    public int pick(int target) {
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
 
 
 
public class Solution {

	int[] nums;
    Random rnd;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }
    
    public int pick(int target) {
        int result = -1;
        int count = 0;
 
		for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
            if (rnd.nextInt(++count) == 0)
                result = i;
        }
        
        return result;
    }
}

// Explanation

public int pick(int target) {
	int result = -1;
	int count = 0; // to record how many targets in the array

	for (int i = 0; i < nums.length; i++) {
		if (nums[i] != target)
			continue;
		/*
		For the nth target, ++count is n. Then the probability that rnd.nextInt(++count)==0 is 1/n. Thus, the probability that return nth target is 1/n.
		For (n-1)th target, the probability of returning it is (n-1)/n * 1/(n-1)= 1/n.
		*/
		if (rnd.nextInt(++count) == 0)
			result = i;
	}
	
	return result;
}