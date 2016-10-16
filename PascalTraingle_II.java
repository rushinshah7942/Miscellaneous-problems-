/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

// first do with Brtue force space i.e. storing all the combinations before k and then analze below solution.

public class Solution 
{
	
    public List<Integer> getRow(int rowIndex) 
    {
        List<Integer> ret = new ArrayList<Integer>();
	    ret.add(1);
	    for (int i = 1; i <= rowIndex; i++) 
	    {
		    for (int j = i - 1; j >= 1; j--) {
			    int tmp = ret.get(j - 1) + ret.get(j);
			    ret.set(j, tmp);
		    }
		    ret.add(1);
	    }
	    return ret;        
    }
}