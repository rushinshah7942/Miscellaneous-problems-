/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

public class Solution 
{
    public boolean containsNearbyDuplicate(int[] nums, int k) 
    {
        
        if(nums == null || nums.length <2)
            return false;
        
        HashMap<Integer, Integer> hp = new HashMap<Integer,Integer>();
        
        for(int i=0;i<nums.length;i++)
        {
            if(hp.containsKey(nums[i]))
            {
                int j = hp.get(nums[i]);
                if(i-j <=k)
                    return true;
            }
            hp.put(nums[i],i);
        }
        
        return false;
    }
} 