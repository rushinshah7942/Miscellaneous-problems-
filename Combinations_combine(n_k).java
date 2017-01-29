/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

*/

// Brute-force approach
// Time -> O(k * n)

public List<List<Integer>> combine(int n, int k) {
    if (n == 0 || k == 0 || k > n) 
		return Collections.emptyList();
    
	List<List<Integer>> res = new ArrayList<List<Integer>>();
    for (int i = 1; i <= n + 1 - k; i++) 
		res.add(Arrays.asList(i));
    
	for (int i = 2; i <= k; i++) { // will keep increasing size in amount of i upto k
        List<List<Integer>> tmp = new ArrayList<List<Integer>>();
        for (List<Integer> list : res) {
			// take first element of list and start with adding from next int
            for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
                List<Integer> newList = new ArrayList<Integer>(list);
                newList.add(m);
                tmp.add(newList);
            }
        }
        res = tmp;
    }
    return res;
}

/*
Execution:
============
initially: [[1], [2], [3]]
For i = 2
----------------
temp list: [1]
m: from 2 to 4
after adding elements: [[1, 2], [1, 3], [1, 4]]
temp list: [2]
m: from 3 to 4
after adding elements: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4]]
temp list: [3]
m: from 4 to 4
after adding elements: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]

For i = 3
------------------
temp list: [1, 2]
m: from 3 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5]]
temp list: [1, 3]
m: from 4 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5]]
temp list: [1, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5]]
temp list: [2, 3]
m: from 4 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5]]
temp list: [2, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5], [2, 4, 5]]
temp list: [3, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5], [2, 4, 5], [3, 4, 5]]

*/


/*
for n = 5 and k = 4


initially: [[1], [2]]

For i = 2

temp list: [1]
m: from 2 to 3
after adding elements: [[1, 2], [1, 3]]
temp list: [2]
m: from 3 to 3
after adding elements: [[1, 2], [1, 3], [2, 3]]

For i = 3

temp list: [1, 2]
m: from 3 to 4
after adding elements: [[1, 2, 3], [1, 2, 4]]
temp list: [1, 3]
m: from 4 to 4
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 3, 4]]
temp list: [2, 3]
m: from 4 to 4
after adding elements: [[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]

For i = 4

temp list: [1, 2, 3]
m: from 4 to 5
after adding elements: [[1, 2, 3, 4], [1, 2, 3, 5]]
temp list: [1, 2, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5]]
temp list: [1, 3, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5], [1, 3, 4, 5]]
temp list: [2, 3, 4]
m: from 5 to 5
after adding elements: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5], [1, 3, 4, 5], [2, 3, 4, 5]]

*/

// backtracking solution

// 1. the valid range for the 1st position is [1, n-k+1]
// 2. the valid range for the i-th position is [number we selected for previous position+1, n-k+1+i]

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<Integer>(), k, 1, n-k+1); // point 1
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, int kLeft, int from, int to) {
        if (kLeft == 0) { 
			ans.add(new ArrayList<Integer>(list)); return; 
		}
        for (int i=from; i<=to; ++i) {
            list.add(i);
            dfs(ans, list, kLeft-1, i+1, to+1); // point 2
            list.remove(list.size()-1);
        }
    }
}

/*

Execution Flow:

====

ans: [] list:[]
adding from:1 to:2
----------
====

ans: [] list:[1]
adding from:2 to:3
--------------------
====

ans: [] list:[1, 2]
adding from:3 to:4
------------------------------
====

ans: [] list:[1, 2, 3]
adding from:4 to:5
--------------------------------------------------------------------------------------------------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5]] list:[1, 2, 4]
adding from:5 to:5
------------------------------------------------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5]] list:[1, 3]
adding from:4 to:4
------------------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5]] list:[1, 3, 4]
adding from:5 to:5
--------------------------------------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5], [1, 3, 4, 5]] list:[2]
adding from:3 to:3
--------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5], [1, 3, 4, 5]] list:[2, 3]
adding from:4 to:4
------------------------------
====

ans: [[1, 2, 3, 4], [1, 2, 3, 5], [1, 2, 4, 5], [1, 3, 4, 5]] list:[2, 3, 4]
adding from:5 to:5
----------------------------------------

*/