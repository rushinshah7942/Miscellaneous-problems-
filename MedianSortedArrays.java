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

