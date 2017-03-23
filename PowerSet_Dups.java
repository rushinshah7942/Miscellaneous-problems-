/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution 
{
    public List<List<Integer>> subsetsWithDup(int[] nums) 
    {
		// always do sort before
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); 
        
        int start = 0; 
        int count = 0;

        for(int i=0;i<nums.length;i++)
        {
			// If we want to insert an element which is a dup, we can only insert it after the newly inserted elements from last step.
            // Hence, if it's duplicate then start with previous count and add new combination
            start = (i > 0 && nums[i] == nums[i-1]) ? count : 0;  // count in previous step

            count = result.size();
            for(int j = start;j < count;j++)
            {
                List<Integer> temp = new ArrayList<>(result.get(j));
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }
}