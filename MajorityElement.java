/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/

// Reference: http://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html

public class Solution 
{
    public int majorityElement(int[] nums) 
    {
        int major = nums[0];
        int count = 1;
        
        for(int i=1;i<nums.length;i++)
        {
            if(count == 0)
            {
                count++;
                major = nums[i];
            }
            else if(major == nums[i])
                count++;
            else
                count--;
        }
        return major;
    }
}