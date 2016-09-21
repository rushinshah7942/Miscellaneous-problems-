/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

//(1)
//O(N lg N) running time + O(1) memory
//The simplest approach is to sort the entire input array and then access the element by it's index (which is O(1)) operation.

public int findKthLargest(int[] nums, int k) 
{
    int N = nums.length;
    Arrays.sort(nums);
    
	return nums[N - k]; // (N-k)
}

// (2)
//O(N lg K) running time + O(K) memory
//Use a min oriented priority queue that will store the K-th largest values. 
//The algorithm iterates over the whole input and maintains the size of priority queue.

public int findKthLargest(int[] nums, int k) 
{

	// An unbounded priority queue based on a priority heap. 
	// The elements of the priority queue are ordered according to their natural ordering.
	PriorityQueue<Integer> pq = new PriorityQueue<>();
    
	for(int val : nums) 
	{
		// Inserts the specified element into this priority queue
        pq.offer(val);

        if(pq.size() > k) 
		{
			// Retrieves and removes the head of this queue
            pq.poll();
        }
    }
    return pq.peek();
}

// (3)
// O(N) best case / O(N^2) worst case running time + O(1) memory
// smart approach for this problem is to use the selection algorithm (based on the partion method - the same one as used in quicksort).

public class Solution 
{
	public int findKthLargest(int[] nums, int k) 
	{
		if (k < 1 || nums == null) 
		{
			return 0;
		}
		return getKth(nums.length - k +1, nums, 0, nums.length - 1);
	}
	public int getKth(int k, int[] nums, int start, int end) 
	{ 
		int pivot = nums[end];
		int left = start;
		int right = end;
 
		while (true) 
		{
			while (nums[left] < pivot && left < right) 
			{
				left++;
			}
			while (nums[right] >= pivot && right > left) 
			{
				right--;
			}
			if (left == right) 
			{
				break;
			}
			swap(nums, left, right);
		}
		swap(nums, left, end);
 
		if (k == left + 1) 
		{
			return pivot;
		} 
		else if (k < left + 1) 
		{
			return getKth(k, nums, start, left - 1);
		} 
		else 
		{
			return getKth(k, nums, left + 1, end);
		}
	}
	
	public void swap(int[] nums, int n1, int n2) 
	{
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
	}
}


/*
Worst case - Example - Explanation
Quicksort works by taking a pivot, then putting all the elements lower than that pivot on one side and 
all the higher elements on the other; it then recursively sorts the two sub groups in the same way 
(all the way down until everything is sorted.) Now if you pick the worst pivot each time 
(the highest or lowest element in the list) you'll only have one group to sort, with everything in that 
group other than the original pivot that you picked. This in essence gives you n groups that 
each need to be iterated through n times, hence the O(n^2) complexity.

1) Array is already sorted in same order.
2) Array is already sorted in reverse order.
3) All elements are same (special case of case 1 and 2)
*/