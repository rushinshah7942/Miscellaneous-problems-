/*
Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found

*/

// Brute-Force
// consider all sub-arrays one by one and check for sum
// O(n^2)

// O(n) solution
// Initialize a variable curr_sum as first element. curr_sum indicates the sum of current subarray. 
// Start from the second element and add all elements one by one to the curr_sum. 
// If curr_sum becomes equal to sum, then print the solution. 
// If curr_sum exceeds the sum, then remove trailing elemnents while curr_sum is greater than sum
	
	int subArraySum(int arr[], int n, int sum) 
    {
        int curr_sum = arr[0], start = 0, i;
 
        for (i = 1; i <= n; i++) 
        {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }
             
            // If curr_sum becomes equal to sum, then return true
            if (curr_sum == sum) 
            {
                int p = i-1;
                //System.out.println("Sum found between indexes " + start+ " and " + p);
                return 1;
            }
             
            // Add this element to curr_sum
            if (i < n)
				curr_sum = curr_sum + arr[i];
        }
 
        System.out.println("No subarray found");
        return 0;
    }

// We can prove it by counting the number of operations performed on every element of arr[] in worst case. 
// There are at most 2 operations performed on every element: (a) the element is added to the curr_sum (b) the element is subtracted from curr_sum. 
// So the 'upper bound' on number of operations is 2n which is O(n).	

