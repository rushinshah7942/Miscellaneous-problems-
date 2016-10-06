/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
*/


/*
For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4

partition the array in blocks of size w=4. The last block may have less then w.
2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|

Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56

Similarly calculate max in future by traversing from end to start.
right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
sliding_max = 4, 6, 6, 8, 9, 10, 12, 56

*/
public class Solution 
{
    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        if(nums == null || k <=0)
            return new int[0];
            
        int[] left_max = new int[nums.length];
        int[] right_max = new int[nums.length];
        
        left_max[0] = nums[0];
        right_max[nums.length-1] = nums[nums.length-1];
        
        for(int i= 1;i<nums.length;i++)
        {
            left_max[i] = (i%k==0 ? nums[i] : Math.max(left_max[i-1],nums[i]));
            
            int j = nums.length - i -1;
            right_max[j] = (j%k ==0 ? nums[j] : Math.max(right_max[j+1], nums[j]));
            
        }           
        int[] sliding = new int[nums.length- k +1];
        
        for(int i=0,j=0;i<=nums.length-k;i++)
        {
            sliding[j++] = Math.max(right_max[i],left_max[k+i-1]);
        }
        return sliding;
    }
}




// With Deque - Much Faster 


// poll() - Retrieves and removes the head of the queue represented by this deque
// pollFirst() - same as poll()
// pollLast() 
// peekFirst()
// peekLast()
public class Solution 
{
    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        if(nums==null||nums.length==0)
            return new int[0];
 
        int[] result = new int[nums.length-k+1];
 
        LinkedList<Integer> deque = new LinkedList<Integer>();
        for(int i=0; i<nums.length; i++)
        {
            if(!deque.isEmpty()&&deque.peekFirst()==i-k) 
                deque.poll();
 
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i])
            {
                deque.removeLast();
            }    
 
            deque.offer(i);
 
            if(i+1>=k)
                result[i+1-k]=nums[deque.peek()];
        }
 
        return result;
    }
}