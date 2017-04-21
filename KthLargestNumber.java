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

// O(N) best & average case / O(N^2) worst case running time + O(1) memory
// Discard half each time: n+(n/2)+(n/4)..1 = n + (n-1) = O(2n-1) = O(n), because n/2+n/4+n/8+..1=n-1.

// smart approach for this problem is to use the selection algorithm (based on the partion method - the same one as used in quicksort).

public class Solution {
	public int findKthLargest(int[] nums, int k) {
		if (k < 1 || nums == null) {
			return 0;
		}
		return getKth(nums.length - k +1, nums, 0, nums.length - 1);
	}
	public int getKth(int k, int[] nums, int start, int end) { 
		int pivot = nums[end];
		int left = start;
		int right = end;
 
		while (true) {
			while (nums[left] < pivot && left < right) {
				left++;
			}
			while (nums[right] >= pivot && right > left) {
				right--;
			}
			if (left == right) { // elements at this point -> left side is less then pivot and right side is more than pivot
				break; // so we break and swap left and end 
			}
			swap(nums, left, right); // or, we swap left and right, and then try again, to create two halves 
		}
		swap(nums, left, end);
 
		if (k == left + 1) {
			return pivot; // gotcha!
		} else if (k < left + 1) {
			// kth largest will be in left side of left index
			return getKth(k, nums, start, left - 1);
		} else {
			// kth largest will be in right side of left index
			return getKth(k, nums, left + 1, end);
		}
	}
	
	public void swap(int[] nums, int n1, int n2) {
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




class KthSmallst
{
    // This function returns k'th smallest element in arr[l..r]
    // using QuickSort based method.  ASSUMPTION: ALL ELEMENTS
    // IN ARR[] ARE DISTINCT
    int kthSmallest(int arr[], int l, int r, int k)
    {
        // If k is smaller than number of elements in array
        if (k > 0 && k <= r - l + 1)
        {
            // Partition the array around a random element and
            // get position of pivot element in sorted array
            int pos = randomPartition(arr, l, r);
 
            // If position is same as k
            if (pos-l == k-1)
                return arr[pos];
 
            // If position is more, recur for left subarray
            if (pos-l > k-1)
                return kthSmallest(arr, l, pos-1, k);
 
            // Else recur for right subarray
            return kthSmallest(arr, pos+1, r, k-pos+l-1);
        }
 
        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }
 
    // Utility method to swap arr[i] and arr[j]
    void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    // Standard partition process of QuickSort().  It considers
    // the last element as pivot and moves all smaller element 
    // to left of it and greater elements to right. This function
    // is used by randomPartition()
    int partition(int arr[], int l, int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }
 
    // Picks a random pivot element between l and r and 
    // partitions arr[l..r] arount the randomly picked 
    // element using partition()
    int randomPartition(int arr[], int l, int r)
    {
        int n = r-l+1;
        int pivot = (int)(Math.random()) % n;
        swap(arr, l + pivot, r);
        return partition(arr, l, r);
    }
 
    // Driver method to test above
    public static void main(String args[])
    {
        KthSmallst ob = new KthSmallst();
        int arr[] = {12, 3, 5, 7, 4, 19, 26};
        int n = arr.length,k = 3;
        System.out.println("K'th smallest element is "+
                           ob.kthSmallest(arr, 0, n-1, k));
    }
}