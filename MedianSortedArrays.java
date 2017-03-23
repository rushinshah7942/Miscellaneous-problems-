/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5

*/

// Reference: http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
//-----------------------------------------------------------------------------------------
// TWO ARRAYS WITH SAME SIZE
// If both the arrays are of same size, then while merging keep count and when count reaches to size of one array, we stop.
// We take the average of elements at n and n-1. 
// Time complexity O(size of one array) = O(n)

/* This function returns median of ar1[] and ar2[].
   Assumptions in this function:
   Both ar1[] and ar2[] are sorted arrays
   Both have n elements */
public int getMedian(int[] ar1, int[] ar2, int n)
{
    int i = 0;  /* Current index for array ar1[] */
    int j = 0; /* Current index for array ar2[] */
    int m1 = -1, m2 = -1;
 
    /* Since there are 2n elements, median will be average
     of elements at index n-1 and n in the array obtained after
     merging ar1 and ar2 */
	 
    for (int count = 0; count <= n; count++)
    {
        /*Below is to handle case where all elements of ar1[] are
          smaller than smallest(or first) element of ar2[]*/
        if (i == n)
        {
            m1 = m2;
            m2 = ar2[0]; // we take first element of ar2 as our second median m2
            break;
        }
 
        /*Below is to handle case where all elements of ar2[] are
          smaller than smallest(or first) element of ar1[]*/
        else if (j == n)
        {
            m1 = m2;
            m2 = ar1[0]; // we take first element of ar1 as our second median m2
            break;
        }

		
        if (ar1[i] < ar2[j])
        {
            m1 = m2;  /* Store the prev median */
            m2 = ar1[i];
            i++;
        }
        else
        {
            m1 = m2;  /* Store the prev median */
            m2 = ar2[j];
            j++;
        }
    }
 
    return (m1 + m2)/2;
}

//---------------------------------------------------------------------------------------
// OPTIMIZED
// TWO ARRAYS WITH SAME SIZE
// optimized O(log n) solution
/*
1) Calculate the medians m1 and m2 of the input arrays ar1[] 
   and ar2[] respectively.
2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)
3) If m1 is greater than m2, then median is present in one 
   of the below two subarrays.
    a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
    b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
4) If m2 is greater than m1, then median is present in one    
   of the below two subarrays.
   a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
   b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
5) Repeat the above process until size of both the subarrays 
   becomes 2.
6) If size of the two arrays is 2 then use below formula to get 
  the median.
    Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
*/

/* This function returns median of ar1[] and ar2[].
   Assumptions in this function:
   Both ar1[] and ar2[] are sorted arrays
   Both have n elements */

// in Java, we need to pass start and end indexes of each array    
private static float getMedian2(int[] a1, int s1,int e1, int[] a2,int s2, int e2){
	
	int len = e1-s1+1;
	
	if(len <= 0)
		return -1;
	if(len == 1)
		return (a1[s1]+a2[s2])/2.0f;
	if(len == 2)
		return (Math.max(a1[s1],a2[s2])+Math.min(a1[e1],a2[e2]))/2.0f;

	float m1 = getM(a1,s1,e1);
	float m2 = getM(a2,s2,e2);

	/* If medians are equal then return either m1 or m2 */
	if(m1 == m2)
		return m1;

	/* if m1 < m2 then median must exist in ar1[m1....] and
        ar2[....m2] */
	if(m1 < m2){
		if(len%2 == 0) // even lengths
			return getMedian2(a1,s1+(len/2),e1, a2,s2,s2+(len/2)-1); // compute indexes based on small example
		
		return getMedian2(a1,s1+(len/2),e1, a2,s2,s2+(len/2)); // odd lengths
	}
	
	/* if m1 > m2 then median must exist in ar1[....m1] and
        ar2[m2...] */
	if(len%2 == 0)
		return getMedian2(a1,s1,s1+(len/2)-1, a2,s2+(len/2),e2);
	
	return getMedian2(a1,s1,s1+(len/2), a2,s2+(len/2),e2);
}

private static float getM(int[] arr,int s,int e){
	int len = e-s+1;
	int i=len/2;
	if(len%2 == 0){
		return (arr[i]+arr[i+1])/2.0f;
	}
	return arr[i];
}

//----------------------------------------------------------------------------------------
// TWO ARRAYS WITH DIFFERENT SIZE
// Solution-1 => Merge arrays and then based on size find median O(m+n) 
// OPTIMIZED 
// O(log (m+n)) solution

public class Solution 
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        // For different length arrays
        int m = nums1.length;
        int n = nums2.length;
        
		// Binary Search Technique
		
        if((m+n)%2 != 0) // odd
        {
            return (double) findKth(nums1,nums2,(m+n)/2, 0, m-1, 0,n-1);
        }
        else // even
        {
            return (findKth(nums1,nums2,(m+n)/2, 0, m-1, 0,n-1) + findKth(nums1,nums2,(m+n)/2 -1, 0, m-1, 0,n-1))*0.5;
        }
    }
    public int findKth(int A[], int B[], int k, int aStart, int aEnd, int bStart, int bEnd)
    {
        int aLen = aEnd - aStart + 1;
        int bLen  = bEnd - bStart + 1;
        
        // Boundary cases
        if(aLen == 0)
            return B[bStart+k];
        if(bLen == 0)
            return A[aStart+k];
        if(k == 0)
            return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
        
		// aMid = aLen / 2 and k = （aLen + bLen)/2
		// so aMid = aLen * k/(aLen + bLen)
		
		// function findKth is a general function to find kth number in two sorted arrays, 
		// not only median of two sorted arrays.
		// Hence, aMid = aLen/2 and bMid = bLen/2 won't work;
		
        int aMid = aLen * k / (aLen + bLen);
        int bMid = k - aMid -1;
        
        aMid = aMid + aStart;
        bMid = bMid + bStart;
        
        // comparison
        if(A[aMid] > B[bMid])
        {
            k = k - (bMid - bStart + 1); // shift k
            aEnd = aMid;
            bStart = bMid + 1; // don't forget +1
        }
        else // A[aMid] < B[bMid]
        { 
            k = k - (aMid - aStart + 1); // shift k 
            aStart = aMid + 1; // don't forget +1
            bEnd = bMid;
        }
        return findKth(A,B,k,aStart,aEnd,bStart,bEnd);
    }
}
// TLE

// Reference: https://discuss.leetcode.com/topic/16797/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation/25
// 68 ms
//Now for the two-array case:

// A1: [# 1 # 2 # 3 # 4 # 5 #]    (N1 = 5, N1_positions = 11)
// A2: [# 1 # 1 # 1 # 1 #]     (N2 = 4, N2_positions = 9)

//Similar to the one-array problem, we need to find a cut that divides the two arrays each into two halves such that
// "any number in the two left halves (of first and second array)" <= "any number in the two right halves".

/*
We can also make the following observations：

There are 2N1 + 2N2 + 2 position altogether. Therefore, there must be exactly N1 + N2 positions on each side of the cut, and 2 positions directly on the cut.

Therefore, when we cut at position C2 = K in A2, then the cut position in A1 must be C1 = N1 + N2 - k. For instance, if C2 = 2, then we must have C1 = 4 + 5 - C2 = 7.

** We are still considering two arrays combined

 [# 1 # 2 # 3 # (4/4) # 5 #]    

 [# 1 / 1 # 1 # 1 #]   
 
** Now, cuts for individual arrays **
 
When the cuts are made, we'd have two L's and two R's. They are

 L1 = A1[(C1-1)/2]; R1 = A1[C1/2];
 L2 = A2[(C2-1)/2]; R2 = A2[C2/2];

 Now how do we decide if this cut is the cut we want? Because L1, L2 are the greatest numbers on the left halves and R1, R2 are the smallest numbers on the right, we only need

	L1 <= R1 && L1 <= R2 && L2 <= R1 && L2 <= R2
	to make sure that any number in lower halves <= any number in upper halves. As a matter of fact, since
	L1 <= R1 and L2 <= R2 are naturally guaranteed because A1 and A2 are sorted, we only need to make sure:

	L1 <= R2 and L2 <= R1.
 
 
 */


public class Solution 
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int N1 = nums1.length, N2 = nums2.length;
    
	if (N1 < N2) 
		return findMedianSortedArrays(nums2, nums1);
    
    int lo = 0, hi = 2 * N2;
    
	while (lo <= hi) {
        int C2 = (lo + hi) / 2; // cut in first array
        int C1 = N1 + N2 - C2; // cut in second array
        
        double L1 = (C1 == 0) ? Integer.MIN_VALUE : nums1[(C1-1)/2];	// Get L1, R1, L2, R2 respectively
        double L2 = (C2 == 0) ? Integer.MIN_VALUE : nums2[(C2-1)/2];
		
        double R1 = (C1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(C1)/2];
        double R2 = (C2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(C2)/2];
        
		if (L1 > R2) 
			lo = C2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
        else if (L2 > R1) 
			hi = C2 - 1;	// A2's lower half too big; need to move C2 left.
        else 
			return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
    }
    
	return -1;
}
}

// CONSIDERS ALL THE CORNER TEST_CASES
// 76 ms
public class Solution 
{
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length, n = B.length;
        if(m == 0) 
			return simpleMedian(B);
        if(n == 0) 
			return simpleMedian(A);
	
        return medianSearch(A, B, Math.max(0, (m + n)/2 - n), Math.min(m - 1, (m + n)/2));
    }
    
    private double medianSearch(int A[], int B[], int left, int right){
        int m = A.length, n = B.length;
        if(left > right) 
			return medianSearch(B, A, Math.max(0, (m + n)/2 - m), Math.min(n - 1, (m + n)/2));
        
		int i = (left + right) / 2;
        int j = (m + n) / 2 - i - 1;
        boolean lvalid = (j < 0) || (A[i] >= B[j]);
        boolean rvalid = (j >= n - 1) || (A[i] <= B[j + 1]);
        
        if(lvalid && !rvalid){ 
        	return medianSearch(A, B, left, i - 1);
        }else if(!lvalid && rvalid){
        	return medianSearch(A, B, i + 1, right);
        }
        
        // median is found
        if((m + n) % 2 == 1) 
			return A[i];
       
		if(i > 0) {
        	int pre = (j < 0) ? A[i - 1] : Math.max(A[i - 1], B[j]);
        	return (A[i] + pre) / 2.0;
        }
        return (A[i] + B[j]) / 2.0;
    }
    
    private double simpleMedian(int A[]){
        int n = A.length;
        if(n % 2 == 1) // odd 
			return A[n/2];
        
		return (A[n/2 - 1] + A[n/2]) / 2.0; // even
    }
}


