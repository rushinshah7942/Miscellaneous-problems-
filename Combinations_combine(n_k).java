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

// n = 4, k = 2
public List<List<Integer>> combine(int n, int k) {
    if (n == 0 || k == 0 || k > n) 
		return Collections.emptyList();
    
	List<List<Integer>> res = new ArrayList<>();

    for (int i = 1; i <= n + 1 - k; i++) 
		res.add(Arrays.asList(i)); // [1] [2] [3]
    
	for (int i = 2; i <= k; i++) { // will keep increasing size in amount of i upto k
        
		List<List<Integer>> tmp = new ArrayList<>();
        
		for (List<Integer> list : res) {
			
			// get the first element of list and start with adding from next int
			for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
                List<Integer> newList = new ArrayList<>(list); // get that list
                newList.add(m); // add m for all ranges
                tmp.add(newList);
            }
        }
		
        res = tmp;
    }
    return res;
}

/*
Execution for n = 5 and k = 3:
========================================
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

public static List<List<Integer>> combine(int n, int k) {
	List<List<Integer>> combs = new ArrayList<>();
	combine(combs, new ArrayList<Integer>(), 1, n, k);
	return combs;
}

public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
	if(k==0) {
		combs.add(new ArrayList<Integer>(comb));
		return;
	}
	for(int i=start;i<=n;i++) {
		comb.add(i); // using the same comb , so we are passing down the reference to comb, one copy of comb through-out
		combine(combs, comb, i+1, n, k-1);
		comb.remove(comb.size()-1);
	}
}

/*

When we assign a object in java, only the object reference is copied. In this is if we don't do combs.add(new ArrayList(comb), comb reference is added inside combs. In each basecase, comb is being modified. and in the last case, comb becomes empty. So, if we use combs.add(comb) instead of combs.add(new ArrayList(comb), in result will all be empty.

Your input

4
2
Your stdout

comb:[1, 2]
comb before removing last element: [1, 2]
comb:[1, 3]
comb before removing last element: [1, 3]
comb:[1, 4]
comb before removing last element: [1, 4]
comb before removing last element: [1]
comb:[2, 3]
comb before removing last element: [2, 3]
comb:[2, 4]
comb before removing last element: [2, 4]
comb before removing last element: [2]
comb:[3, 4]
comb before removing last element: [3, 4]
comb before removing last element: [3]
comb before removing last element: [4]

*/


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