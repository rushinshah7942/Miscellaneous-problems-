/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.



Solution:
=============
One solution is to split the shiftArr to 2 sub-arrays:

the sub-array ([9, 12, 17] on the example)
the other sub-array ([2, 4, 5] on the example).
Once the array is split we can apply binary search only on the relevant sub-array.
The relevant sub-array (of length [n]) would satisfy: subArr[0] ≤ num ≤ subArr[n-1].

To make the split we need the index of the first number in arr (the non-shifted array) in shiftArr (2 in the example).
The number on this index is the only one that is smaller than its left neighbor shiftArr[i] ≤ shiftArr[i-1] (2 < 17 on the example).

To find this index we use a modified binary search:
On each step we check if the current shiftArr is smaller than it's left neighbor. If it does - we've found the index we need.
Otherwise, we determine what half of the array to focus on by comparing shiftArr[mid] to the first number of shiftArr[0]. This comparison will tell us if the current mid index is part of the shifted sub-array or the other sub-array.

Runtime Complexity:
1. since we decrease our input size in half on each iteration step, the runtime complexity of binary search is O(log n).
2. doing 2 binary searches (1 modified and 1 typical), the runtime complexity is O(log n).

*/

public class Solution {
	public int search(int[] nums, int target) {
		int pivot = findPivot(nums);
		if(pivot == 0) 
			return binarySearch(nums, 0, nums.length - 1, target);
		
		int rightSide = binarySearch(nums, pivot, nums.length - 1, target);
		if(rightSide != -1) 
			return rightSide;
		
		int leftSide = binarySearch(nums, 0, pivot - 1, target);
		return leftSide;
	}

	private int findPivot(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while(left < right) {
			
			if(nums[left] < nums[right]) 
				return left;
			
			int mid = left + (right - left) / 2;
			
			if(nums[left] < nums[mid]) {
				left = mid + 1;
			} else if(nums[left] > nums[mid] && nums[mid] < nums[right]) {
				right = mid;
			} else {
				return right;
			}
		}
		return left;
	}

	private int binarySearch(int[] nums, int start, int end, int target) {
		if(start > end) 
			return -1;
		int mid = start + (end - start) / 2;
		if(target == nums[mid]) 
			return mid;
		if(target < nums[mid]) 
			return binarySearch(nums, start, mid - 1, target);
		
		return binarySearch(nums, mid + 1, end, target);
	}

}
// Python
function shiftedArrSearch(shiftArr, num):
   originalFirst = getOrigFirst(shiftArr)
   if num >= shiftArr[0]:
      arr = shiftArr.subArr(0, originalFirst-1)
      return binarySerach(arr, num)
   else:
      n = length(shiftArr)
      arr = shiftArr.subArr(originalFirst-1, n-1)
      return binarySearch(arr, num)

function getOrigFirst(arr):
   begin = 0
   end = length(arr)
   while (begin <= end):
      mid = round((begin+end)/2)
      if (mid == 0 or arr[mid] < arr[mid-1]):
         return mid
      if arr[mid] > arr[0]:
         begin = mid + 1
      else:
         end = mid - 1
   return 0
   
   
// Single Binary Search solution
public int search(int[] A, int target) {
    if (A.length == 0) 
		return -1;
    
	int L = 0, R = A.length-1;
   
    if (target < A[L] && target > A[R]) 
		return -1;
    
    while (L < R) {
        int M = (L + R)/2;
        
		if (A[M] <= A[R]) {
            if (target > A[M] && target <= A[R]) {
                L = M+1;
            } else {
                R = M;
            }
            
        } 
		else {
            if (target <= A[M] && target >= A[L]) {
                    R = M;
            } else {
                L = M+1;
            }
        }
    }
    if (A[L] == target) 
		return L;
    else 
		return -1;
}   
   