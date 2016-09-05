/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6

*/


// Brute-Force 
// O(n^3)
public int maxSubArray(int[] nums) 
    {
        if(nums.length < 1)
            return 0;
        
        if(nums.length == 1)
        {
            return nums[0];
        }   
        int maxsum = Integer.MIN_VALUE;
        int len = nums.length;
        
        // Brute-Force  o(n^3)
        for(int i=0;i<len;i++)
        {
            for(int j=i;j<len;j++)
            {
                int sum = 0;
                for(int k = i ;k <= j ;k++)
                {
                    sum += nums[k];
                }
                if(sum > maxsum)
                    maxsum = sum;
            }
        }
        return maxsum;
    }
	
// Dynamic Programming
// O(n) time and O(n) space	
	public int maxSubArray(int[] A)
    {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++)
        {
            dp[i] = Math.max(A[i] + dp[i - 1] , A[i]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }	

// O(n) time 
// Improved space complexity to O(1)

	public int maxSubArray(int[] nums)
	{
		int max = nums[0];
        int currentSum = 0;
        for (int i : nums) 
		{
            currentSum += i;
            max = Math.max(max, currentSum);
			// important step
			if (currentSum < 0) 
			{
                currentSum = 0;
            }
        }      
        return max;	
	} 