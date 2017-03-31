/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

*/


/*
[2,4,-2,3,-3]

max 3 
min -48
res 8
lastmax 8
*/

public int maxProduct(int[] A) {
   
   if(A == null || A.length == 0)
		return 0;
   
   int min;
   int res = A[0], max = min = A[0]; // max, min means max and min product among the subarrays whose last element is A[i].
   for (int i = 1; i < A.length; i++) {
	   if (A[i] > 0) {
		   max = Math.max(max * A[i], A[i]);
		   min = Math.min(min * A[i], A[i]);			   
	   }
	   else {
		   int lastMax = max; 
		   // for negative number, we need to keep looking at min and check if after multiplying with it gives max value or not
		   max = Math.max(min * A[i], A[i]);
		   min = Math.min(lastMax * A[i], A[i]);			   			   
	   }
	   res = Math.max(res, max);
   }
   return res;
}

// given limit of product

2