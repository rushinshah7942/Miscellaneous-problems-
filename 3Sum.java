/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

 ** Note: The solution set must not contain duplicate triplets. **

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

// Brute-force approach O(n^3)


// Optimized Solution : O(n^2)

public class Solution 
{
    public List<List<Integer>> threeSum(int[] nums) 
    {       
		// extra space of HashSet	
        HashSet hashset = new HashSet();
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(nums==null || nums.length<3)
            return result;
        
        Arrays.sort(nums); // O(n logn)
        
		// use three pointers
        for(int i=0;i<nums.length-2;i++)
        {
            int j=i+1; 
            int k = nums.length-1;

			// now check all posibilities for value of i, using pointers j and k
            if(i==0 || nums[i] > nums[i-1]) 
            {
				// Avoid immediate duplicates
				// i==0 checking first, so second condition won't be checked for i=0
				// to avoid immediate duplicate triplets, we check for nums[i] > nums[i-1]
				
                while(j < k)
                {
                    int sum = nums[i]+nums[j]+nums[k];

					if(sum == 0)
                    {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                    
						/*
						// duplicate checking && extra space
                        if(!hashset.contains(temp))
                        {
                            hashset.add(temp);
                            result.add(temp);
                        }
						*/
						
						
						//No extra space
						// -> remove hashset logic
						 
						// and add
						 
						while(j<k && nums[j]==nums[j-1])
							j++;
						while(j<k && nums[k]==nums[k+1])
							k--;
						
						
						
                        j++;
                        k--;
                    }
                    else if(sum>0)
                    {
                        k--;
                    }
                    else if(sum<0)
                    {
                        j++;
                    }
                }
            }
        }
        return result;
        
    }
}

