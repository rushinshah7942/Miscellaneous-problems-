/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/
public class Solution {
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
			// if number is on correct position
			// or if negative or zero number
			// or if greater than size of array
			// just skip
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) 
                i++;
            else if(A[A[i]-1] != A[i]) // if not at correct position, swap 
                swap(A, i, A[i]-1);
            else 
				i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}