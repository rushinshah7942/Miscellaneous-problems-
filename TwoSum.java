/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

*/

// O(N) time and space solution

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> hp = new HashMap<Integer, Integer>(); // value and its index
        int[] result = new int[2];
        
        for(int i=0;i<nums.length;i++)
        {
            int temp = target - nums[i];

            if(hp.get(temp) == null)
            {
                hp.put(nums[i],i);
            }
            else
            {
                //found the pairs
                result[0] = hp.get(temp);
                result[1] = i;
                break;
            }
        }
        return result;   
    }
}


// Without using HashMap
// Time O(nlogn)
// Space O(1)

public int[] twoSum(int[] nums, int target){
	int start = 0;
	int end = nums.length-1;
	
	int[] result = new int[2];
	// Sort the array
	Arrays.sort(nums);
	
	while(start<end){
		int temp = nums[start] + nums[end];

		if(temp < target){
			start++;
		}else if(temp > target){
			end--;
		}else{
			result[0] = start;
			result[1] = end;
			break;
		}
	}
	return result;
}