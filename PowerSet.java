/*
Given a set of distinct integers, S, return all possible subsets.

Note: 1) Elements in a subset must be in non-descending order. 2) The solution set must not contain duplicate subsets.

For example, given S = [1,2,3], the method returns:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public ArrayList<arraylist<integer>> subsets(int[] S) 
{
	ArrayList<List<Integer>> ans = new ArrayList<ArrayList<Integer>>();
	ans.add(new ArrayList<integer>());
	
	Arrays.sort(S);
	for (int i = 0; i < S.length; i ++) 
	{
		int curSize = ans.size();
		for (int j = 0; j < curSize; j ++) 
		{
			ArrayList<integer> cur = new ArrayList<integer>(ans.get(j));
			cur.add(S[i]);
			ans.add(cur);
		}
	}
	return ans;
}