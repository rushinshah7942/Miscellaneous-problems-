/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

/*

This problem requires to maintain a window of size k of the previous values that can be queried for value ranges. The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result in time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple queries can be executed both of time complexity O(lg K)

Here is the whole solution using TreeMap.

*/

// Time : O(N * log k)		
// Space : O(k)
// Works well with negtive or duplicates numbers as well

public class Solution 
{
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) 
    {
		if (k < 1 || t < 0)
			return false;
	 
		TreeSet<Integer> set = new TreeSet<Integer>();
	 
		for (int i = 0; i < nums.length; i++) 
		{
			int c = nums[i];
			// satisfies t condition
			if ((set.floor(c) != null && c <= set.floor(c) + t)      || 
				(set.ceiling(c) != null && c >= set.ceiling(c) -t))
				return true; // O(log k)
	 
			set.add(c);  // O(log k) , k is the size of the set that we maintain
	 
			// satisfies k condition
			if (i >= k)
				set.remove(nums[i - k]); // remove elements outside window of k
		}
	 
		return false;
    }
}

// O(N) solution in Java using buckets
// Bucketing means we map a range of values to the a bucket. For example, if the bucket size is 3, we consider 0, 1, 2 all map to the same // bucket. However, if t == 3, (0, 3) is a considered duplicates but does not map to the same bucket. This is fine since we are checking // // the buckets immediately before and after as well.

// So, as a rule of thumb, just make sure the size of the bucket is reasonable such that 
// elements having the same bucket is immediately considered duplicates or duplicates must lie within adjacent buckets. 
// So this actually gives us a range of possible bucket size, i.e. t and t + 1. 
// We just choose it to be t and a bucket mapping to be num / t.

// ONLY IF, NEGATIVE NUMBERS ARE NOT ALLOWED

/*
In this case, the elements are assigned to a bucket by a simple division of a bucket size. Hence, it will, for example, assign both -2 and +2 to bucket 0 if the bucket size is > 2. If we want that elements having the same bucket 0 are immediately considered duplicates, such assignment would be undesirable. Hence, a repositioning is used to cope with this. And starting from MIN_VALUE is just because of the constraints on the input range.
*/

// Time O(n)  Space O(k)
public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) 
{   
	        if (k < 1 || t < 0) 
				return false;
	        
			Map<Long, Long> map = new HashMap<>(); // key is bucket and value is element 

	        for (int i = 0; i < nums.length; i++) 
	        {
	            long remappedNum = (long) nums[i];
	            long bucket = remappedNum / ((long) t);
	            
				//System.out.println("num: " + nums[i] + " remapped: " + remappedNum + " bucket:" + bucket);
	            
				if (map.containsKey(bucket) || 
	               (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t) || 
	               (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) // adjacent buckets
	                  return true;
	            
	            if (map.entrySet().size() >= k) 
				{
	                long lastBucket = ((long) nums[i - k]) / ((long) t); // get bucket index
	                map.remove(lastBucket); // remove that bucket from map
	            }
				
	            map.put(bucket, remappedNum);
	        }
	        return false;
	    }

// A simple num / t just shrinks everything towards 0. Therefore, we can just reposition every element to start from Integer.MIN_VALUE.
// Actually, we can use t + 1 as the bucket size to get rid of the case when t == 0. It simplifies the code.
		
public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) 
	{
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) 
		{
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                        || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                            return true;
            if (map.entrySet().size() >= k) 
			{
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }		