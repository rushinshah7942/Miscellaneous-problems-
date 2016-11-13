/*

STANDARD PROBLEM WHICH IS ASKED IN INTERVIEWS
------------------------------------------------------

Given an array of integers, find the nearest(e.g. rightmost element on left side) smaller number for every element such that the smaller element is on left side.

Examples:

Input:  arr[] = {1, 6, 4, 10, 2, 5}
Output:         {_, 1, 1,  4, 1, 2}
First element ('1') has no element on left side. For 6, 
there is only one smaller element on left side '1'. 
For 10, there are three smaller elements on left side (1,
6 and 4), nearest among the three elements is 4.

Input: arr[] = {1, 3, 0, 2, 5}
Output:        {_, 1, _, 0, 2}
*/

/*
The idea is to use a stack. Stack is used to maintain a subsequence of the values that have been processed so far and are smaller than any later value that has already been processed.

Below is stack based algorithm

Let input sequence be 'arr[]' and size of array be 'n'

1) Create a new empty stack S

2) For every element 'arr[i]' in the input sequence 'arr[]',
   where 'i' goes from 0 to n-1.
    a) while S is nonempty and the top element of 
       S is greater than or equal to 'arr[i]':
           pop S
    
    b) if S is empty:
           'arr[i]' has no preceding smaller value
    c) else:
           the nearest smaller value to 'arr[i]' is 
           the top element of S

    d) push 'arr[i]' onto S
*/


public int[] smallerElementLeft(int[] arr){
	
	Stack<Integer> stack = new Stack<>();
	int[] result  = new int[arr.length];
	
	for(int i=0;i<arr.length;i++){
		while(!stack.isEmpty() && stack.peek() >= arr[i])
			stack.pop();
		
		if(stack.isEmpty())
			result[i] = -1; // -1 indicates that there is no smaller element on left side
		else
			result[i] = arr[i];
		
		stack.push(arr[i]);
	}
	return result;
}