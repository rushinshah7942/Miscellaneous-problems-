/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

// The idea is to try to put every number at the beginning of the array, and then do the same thing for the rest of the array.
// O(n^n)
// for every element, it goes to recursion

public class Solution {
	
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
		LinkedList<Integer> list = new LinkedList<Integer>();
        for (int num : nums) 
			list.add(num);
		
        permute(list, 0, res);
        return res;
    }
    private void permute(LinkedList<Integer> nums, int start, List<List<Integer>> res){
        if (start == nums.size() - 1){
            res.add(new LinkedList<Integer>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++){
			
            if (i > start && nums.get(i) == nums.get(i - 1)) 
				continue;

            nums.add(start, nums.get(i));
            nums.remove(i + 1);

            permute(nums, start + 1, res);

            nums.add(i + 1, nums.get(start));
            nums.remove(start);
        }
    }
}

// for every element, it goes into recursion 
// Hence, it's O(n^n) time complextiy

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
		if(nums==null || nums.length==0) 
			return res;
        
		boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        
		Arrays.sort(nums);
        
		dfs(nums, used, list, res);
        
		return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if(list.size()==nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]) 
				continue;
            if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) 
				continue;
            
			used[i]=true;

            list.add(nums[i]);
            
			dfs(nums,used,list,res);
            
			used[i]=false;
            list.remove(list.size()-1); // remove last added value
        }
    }
}