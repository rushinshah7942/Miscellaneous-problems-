/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
*/

public class Solution 
{
    public List<Integer> majorityElement(int[] nums) 
    {
        if (nums == null || nums.length == 0)
		    return new ArrayList<Integer>();
	    
	    List<Integer> result = new ArrayList<Integer>();
        int count1 = 0;
        int count2 = 0;
        int major1 = nums[0], major2 = nums[0];
        
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] == major1)
                count1++;
            else if(nums[i] == major2)
                count2++;
            else if(count1 == 0)
            {
                major1 = nums[i];
                count1=1;
            }
            else if(count2== 0)
            {
                major2 = nums[i];
                count2=1;
            }
            else
            {
                count1--; count2--;
            }
        }
        count1 = 0;
	    count2 = 0;
	    for (int i = 0; i < nums.length; i++) 
	    {
		    if (nums[i] == major1)
		    	count1++;
		    else if (nums[i] == major2)
		    	count2++;
	    }
	    //System.out.println("major1 " + major1 + " major2 " + major2);
	    if (count1 > nums.length / 3)
		    result.add(major1);
	    if (count2 > nums.length / 3)
		    result.add(major2);
		    
	    return result;
        
    }
}

// Higher Challenge:
// In case of n/3, there can be 2 majorities. Hence, we keep 2 counts
// In case of n/4, there can be 3 majorities. Hence, we need 3 counts
// In case of n/k, there can be k-1 majorities, Hence, we need k-1 counts
// We can use array for majorities and count1

public class Solution 
{
    public List<Integer> majorityElement(int[] nums) 
	{
        int n = nums.length, k = 3;  //let's say k=3
        List<Integer> result = new ArrayList<Integer>();
        
		if (n==0 || k<2) 
			return result;
        
		int[] majors = new int[k-1];
        int[] counts = new int[k-1];
        
		for (int num: nums) 
		{
			// works as if..else if..else
            boolean settled = false;
			
            for (int i=0; i<k-1; i++) 
			{
                if (majors[i]==num) 
				{
                    counts[i]++;
                    settled = true;
                    break;
                } 
            }
            
			if (settled) 
				continue;
            
			for (int i=0; i<k-1; i++) 
			{
                if (counts[i]==0) 
				{
                    counts[i] = 1;
                    majors[i] = num;
                    settled = true;
                    break;
                } 
            }
            
			if (settled) 
				continue;
			
            for (int i=0; i<k-1; i++) 
				counts[i] = (counts[i] > 0) ? (counts[i]-1) : 0;
        }
        Arrays.fill(counts, 0);
		
        for (int num: nums) 
		{
            for (int i=0;i<k-1; i++) 
			{
                if (majors[i]==num) 
				{
                    counts[i]++;
                    break;
                }
            }
        }
        for (int i=0; i<k-1; i++) 
			if (counts[i]>n/k) 
				result.add(majors[i]);
        
		return result;
    }
}