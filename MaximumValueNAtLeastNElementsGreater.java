/*
Given an array of positive integers, find maximum possible value K such that the array has at-least K elements that are greater than or equal to K. The array is unsorted and may contain duplicate values.
*/

/*
Examples :

Input: [2, 3, 4, 5, 6, 7]
Output: 4
Explanation : 4 elements [4, 5, 6, 7] 
            are greater than equal to 4

Input: [1, 2, 3, 4]
Output: 2
Explanation : 3 elements [2, 3, 4] are 
               greater than equal to 2
*/
/*
SOLUTION
************

1) The idea is to construct axillary array of size n + 1, and use that array to find count of greater elements in input array. Let the auxiliary array be freq[]. We initialize all elements of this array as 0.
2) We process all input elements.
        a) If an element arr[i] is less than n, then we increment its frequency, i.e., we do freq[arr[i]]++.
        b) Else we increment freq[n].

3) After step 2 we have two things.
        a) Frequencies of elements for elements smaller than n stored in freq[0..n-1].
        b) Count of elements greater than n stored in freq[n].

Finally, we process the freq[] array backwards to find the output by keeping sum of the values processed so far.
*/

// Reference: http://www.geeksforgeeks.org/maximum-value-k-such-that-array-has-at-least-k-elements-that-are-k/

public int findMaximum(int[] arr){
	int[] freq = new int[arr.length+1];
	
	for (int i = 0; i < n; i++){
        // If element is smaller than n, update its frequency
        if (arr[i] < n)
            freq[arr[i]]++;
        // Else increment count of elements greater than n.
        else
            freq[n]++;
    }
	int sum = 0;
    // scan auxillary array backwards
    for (int i = n; i > 0; i--){
        sum += freq[i];
 
        // if sum is greater than current index, current index is the answer
        if (sum >= i)
            return i;
    }
}