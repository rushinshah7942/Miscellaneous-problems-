/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
*/

// O(k * 2 ^ n), where k is the average length of all the combinations and n is the size of nums

public class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }
	
	// backtracking solution, very easy 
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        
		// these are base-conditions to break out from recursion
		if(remain < 0) 
            return;
		else if(remain == 0) 
            list.add(new ArrayList<>(tempList)); // always add like this, as we don't want to add reference to tempList
		else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // NOT i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    } 
}

// Iterative Solution :  Dynamic Programming
/*
The main idea reminds an approach for solving coins/knapsack problem - to store the result for all i < target and create the solution from them. For that for each t from 1 to our target we try every candidate which is less or equal to t in ascending order. For each candidate "c" we run through all combinations for target t-c starting with the value greater or equal than c to avoid duplicates and store only ordered combinations.
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t (like weights in Knap-sack problem)
            List<List<Integer>> newList = new ArrayList(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) 
					newList.add(Arrays.asList(cands[j]));
                // if current candidate is less than the target use prev results
                else {
					for (List<Integer> l : dp.get(i-cands[j]-1)) {
						if (cands[j] <= l.get(0)) { 
							List cl = new ArrayList<>();
							cl.add(cands[j]); cl.addAll(l);
							newList.add(cl);
						}
					}
				}
            }
			// go for next target in the loop
            dp.add(newList);
        }
        return dp.get(t-1); // get our target one
    }
}