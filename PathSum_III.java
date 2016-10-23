/*
ou are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution 
{
    // Brute-Force solution
    // for each node, find all paths starting from it and return only when sum matches or it reaches the leaf
    public int pathSum(TreeNode root, int sum) 
    {
        if(root == null)        
            return 0;
            
        int current = helper(root,sum);
        int left = pathSum(root.left,sum);
        int right = pathSum(root.right,sum);
        
        return current + left + right;
    }
    public int helper(TreeNode root, int sum)
    {
        int ans = 0;
        
        if(root == null)
            return ans;
            
        if(root.val == sum)
            ans++;
            
        ans += helper(root.left,sum-root.val);
        ans += helper(root.right,sum-root.val);
        
        return ans;
    }
}