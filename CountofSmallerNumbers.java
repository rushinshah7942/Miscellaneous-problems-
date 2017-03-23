/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/

/*

Traverse from the back to the beginning of the array, maintain an sorted array of numbers have been visited. Use findIndex() to find the first element in the sorted array which is larger or equal to target number. For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], findIndex() returns 1, which is the index where 2 should be inserted and is also the number smaller than 2. Then we insert 2 into the sorted array to form [1,2,3,6].

*/

public class Solution 
{
    public List<Integer> countSmaller(int[] nums) 
    {
		Integer[] ans = new Integer[nums.length];
		List<Integer> sorted = new ArrayList<Integer>();
		
		for (int i = nums.length - 1; i >= 0; i--) {
			int index = findIndex(sorted, nums[i]); // does binary search, hence O(N*logN)
			ans[i] = index;
			sorted.add(index, nums[i]); 
			// plus amortized O(N) which we can avoid if there is not much of doubling of araylist and copying elements
		}
		return Arrays.asList(ans);
	}
	private int findIndex(List<Integer> sorted, int target) {
		if (sorted.size() == 0) 
			return 0;
		
		int start = 0;
		int end = sorted.size() - 1;
		
		if (sorted.get(end) < target) 
			return end + 1;
		if (sorted.get(start) >= target) 
			return 0;
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (sorted.get(mid) < target){
				start = mid + 1;
			}else {
				end = mid;
			}
		}
		
		if (sorted.get(start) >= target) 
			return start;
		
		return end;
	}
}

// Using BST 
// Faster 


public class Solution {
	public List<Integer> countSmaller(int[] nums) {

		if(nums.length == 0)
			return new ArrayList<Integer>();
		
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list1 = new ArrayList<Integer>();
		
		TreeNode root = new TreeNode(nums[nums.length-1]);
		root.val=1;
		list.add(0);
		
		for(int i = nums.length-2;i >= 0;i--){
			list.add(get(root,nums[i],0));
		}
		
		for(int i = nums.length-1;i >= 0;i--)
			list1.add(list.get(i));
		
		return list1;
	}
	public int get(TreeNode root,int val,int val){
		if(root.val >= val)
		{
			root.val = root.val+1;
			if(root.left == null)
			{
				TreeNode node = new TreeNode(val);
				node.val = 1;
				root.left = node;
				return val;
			}else{
				return get(root.left,val,val);
			}
		}
		else{
			val += root.val;
			if(root.right == null){
				TreeNode node = new TreeNode(val);
				node.val = 1;
				root.right = node;
				return val;
			}else{
				return get(root.right,val,val);
			}
		}
	}
}