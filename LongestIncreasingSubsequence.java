/*
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order. 

For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.

*/
/*

Optimal Substructure:
--------------------------
Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.

Then, L(i) can be recursively written as:
L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
L(i) = 1, if no such j exists.

To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.

Thus, we see the LIS problem satisfies the optimal substructure property as the main problem can be solved using solutions to subproblems.


Overlapping Subproblems:
---------------------------
Considering the above implementation, following is recursion tree for an array of size 4. ls(n) gives us the length of LIS for arr[].

              ls(4)
        /        |     \
      ls(3)    ls(2)   ls(1)
     /   \        /
   ls(2) ls(1) ls(1)
   /
ls(1)

*/


// this just returns the length of longest increasing subsequence
public class Solution {
    public int lengthOfLIS(int[] arr) {
	  
		int ls[] = new int[arr.length];
		int i,j,max = 0;

		for ( i = 0; i < arr.length; i++ )
			ls[i] = 1;

		/* Compute optimized LIS values in bottom up manner */
		for ( i = 1; i < arr.length; i++ )
		  for ( j = 0; j < i; j++ ) 
			if ( arr[j] < arr[i] && ls[i] < ls[j] + 1) // for duplicates keep arr[j] <= arr[i]
				ls[i] = ls[j] + 1;

		/* Scan the ls array, and pick maximum of all LIS values */
		for ( i = 0; i < arr.length; i++ )
		  if ( max < ls[i] )
			 max = ls[i];

		return max;            
    }
}


/*
If you actually need to construct all of them, you are out of luck -- there can be exponentially many.

For instance, the sequence
-----------------------
2,1,4,3,...,2k,2k−1,...,2n,2n−12,1,4,3,...,2k,2k−1,...,2n,2n−1
contains exactly 2n2n longest increasing subsequences (each of length n).

*/

// To print one of the solutions
public int longestSubsequenceWithActualSolution(int arr[]){
	int T[] = new int[arr.length];
	int actualSolution[] = new int[arr.length];
	for(int i=0; i < arr.length; i++){
		T[i] = 1;
		actualSolution[i] = i;
	}
	
	for(int i=1; i < arr.length; i++){
		for(int j=0; j < i; j++){
			if(arr[i] > arr[j]){
				if(T[j] + 1 > T[i]){
					T[i] = T[j] + 1;
					//set the actualSolution to point to guy before me
					actualSolution[i] = j;
				}
			}
		}
	}
	
	//find the index of max number in T 
	int maxIndex = 0;
	for(int i=0; i < T.length; i++){
		if(T[i] > T[maxIndex]){
			maxIndex = i;
		}
	}
	
	// Prints one of the solutions
	//lets print the actual solution
	int t = maxIndex;
	int newT = maxIndex;
	do{
		t = newT;
		System.out.print(arr[t] + " ");
		newT = actualSolution[t];
	}while(t != newT);

	System.out.println();

	return T[maxIndex];
}