/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/


// slight modification of 3Sum problem
public class Solution 
{
    public int threeSumClosest(int[] nums, int target) 
    {
        int sum;
        int result = 0;
        int diff = Integer.MAX_VALUE;
        
        Arrays.sort(nums);
        
        for(int i=0;i<nums.length-2;i++)
        {
            int j=i+1;
            int k= nums.length-1;
            
            while(j<k)
            {
                sum = nums[i]+ nums[j]+ nums[k];
                int diff_mod = Math.abs(target-sum);
                if(diff_mod < diff)
                {
                    diff = diff_mod;
                    result = sum;
                }
                if(sum > target)
                {
                    k--;
                }
                else
                {
                    j++;
                }
            }
        }
        return result;
    }
}