/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct.
*/

public boolean containsDups(int[] nums)
{
	if(nums==null || nums.length==0)
            return false;
         
    Set<Integer> hs = new HashSet<Integer>();
    for(int val : nums)
    {
		// return if first time encounter duplicate
        if(!hs.add(val))
            return true;
    }    
    return false;
}