/*

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

*/

/*

The key here is to use swapping to keep constant space and also make use of the length of the array, which means there can be at most n positive integers. So each time we encounter an valid integer, find its correct position and swap. Otherwise we continue.

*/

public class Solution {
    public int firstMissingPositive(int[] A) {
        int i = 0;
        
		while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) // (correct position ||  negative number || bigger number than length)
				i++;
            else if(A[A[i]-1] != A[i]) // find its correct position ,and it's not 
				swap(A, i, A[i]-1); // then swap
            else 
				i++;
        }
        i = 0;
        
		// negative number will be scattered in between correct positions
		while(i < A.length && A[i] == i+1) // loop till elements are in their correct position, A[] = [1 -1 3 4]  
			i++;
		
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
// what if all numbers are larger than the array length?
// ans - 1

// what if there are duplicates ???
// then they will behave same as negative numbers 
// they will be scattered throught correct positioned numbers 
// e.g. 1 1 2 4
// afrer processing, A[] = [1 2 1 4]
// 1 should be correct position for (i+1) element