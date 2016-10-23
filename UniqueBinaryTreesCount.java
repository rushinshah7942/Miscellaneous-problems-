/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

public class Solution 
{
    public int numTrees(int n) 
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        map.put(1,1);
        return numTrees(n, map);
    }
    
    private int numTrees(int n, Map<Integer, Integer> map){
        // check memory
        if(map.containsKey(n)) return map.get(n);
        // recursion
        int sum = 0;
        for(int i = 1;i <= n;i++)
            sum += numTrees(i-1, map) * numTrees(n-i, map);
        map.put(n, sum);
        return sum;
    }
}
    
}