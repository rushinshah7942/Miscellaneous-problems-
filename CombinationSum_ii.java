/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
*/

// O(k * 2 ^ n), where k is the average length of all the combinations and n is the size of nums

public class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums); // O(n*logn)
		backtrack(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
		if(remain < 0) 
			return;
		else if(remain == 0) 
			list.add(new ArrayList<>(tempList)); // create new ArrayList and copy tempList
		else{
			for(int i = start; i < nums.length; i++){
				if(i > start && nums[i] == nums[i-1]) 
					continue; // skip duplicates *
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, remain - nums[i], i + 1); // i+1 as we can not use same element again
				tempList.remove(tempList.size() - 1); 
			}
		}
	} 
}

// * 
// for example, [1,1,1,2], we want a sum of 4, we have add [1, 1(second), 2] to the result set, then back from the recursion, when   
// i >cur, that is, i point to the third 1, this time we skip the third 1, just to avoid another [1,1(third), 2] added to the result set.

// we finding combinations through recursion, not in the loop, so in loop avoid the duplication by i > start && nums[i] == nums[i-1] line.