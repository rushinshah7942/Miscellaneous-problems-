/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

*/

/*

Solution-1  Without using Priority Queue:
-----------------------------------------
The basic idea is to use an array to track the index of the next element in the other array.

The best way to understand this solution is using an example such as nums1={1,3,11} and nums2={2,4,8}.

Time complexity: O(k*m)
where m = length of shorter array

*/

// 11 ms
public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> result = new ArrayList<int[]>();
 
    k = Math.min(k, nums1.length*nums2.length);
 
    if(k==0)
        return result;

	//Records the index of number in nums2 which currently paired with number in nums1.	
    int[] idx = new int[nums1.length];
 
    while(k>0){
        int min = Integer.MAX_VALUE;
        int t=0;
        for(int i=0; i<nums1.length; i++){
            if(idx[i]<nums2.length && nums1[i]+nums2[idx[i]]<min){
                t=i;  
                min = nums1[i]+nums2[idx[i]];
            }
        }
 
        int[] arr = {nums1[t], nums2[idx[t]]};    // e.g. idx[t] points to index in nums2
        result.add(arr);
 
        idx[t]++; // now consider next index for given t value of nums1
 
        k--;
    }
 
    return result;
}

/*

Solution-2:
-----------
First, we take the first k elements of nums1 and paired with nums2[0] as the starting pairs so that we have (0,0), (1,0), (2,0),.....(k-1,0) in the heap.

Each time after we pick the pair with min sum, we put the new pair with the second index +1. ie, pick (0,0), we put back (0,1). Therefore, the heap alway maintains at most min(k, len(nums1)) elements.

If arrays given are not sorted, then sort both arrays and then do the following operation!

Time Complexity: O(K*LogK)
Space: O(K)

*/


// 8 ms
public class Solution {
 
	class Pair{
        int[] pair; // or you can take x and y seperately
        int idx; // current index to nums2
        long sum;
        Pair(int idx, int n1, int n2){
            this.idx = idx;
            pair = new int[]{n1, n2};
            sum = (long) n1 + (long) n2;
        }
    }
	
    class CompPair implements Comparator<Pair> {
 
		public int compare(Pair p1, Pair p2){
            return Long.compare(p1.sum, p2.sum); // imp to use Long.compare in real scenarios
        }
    }
	
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
		List<int[]> ret = new ArrayList<>();
        if (nums1==null || nums2==null || nums1.length ==0 || nums2.length ==0) 
			return ret;
		
        int len1 = nums1.length, len2=nums2.length;  

        PriorityQueue<Pair> q = new PriorityQueue<>(k, new CompPair()); 
        
		for (int i=0; i<nums1.length && i<k ; i++) { // only need first k number in nums1 to start  
            q.offer( new Pair(0, nums1[i],nums2[0]) );
        }
		

		// till now, we have k or num1.length elements of nums1 with first element of nums2
		// now start with pairing element of  num2 with index > 0
        for (int i=1; i<=k && !q.isEmpty(); i++) { // get the first k sums
            Pair p = q.poll(); 
            ret.add( p.pair ); // You always add first pair as arrays are sorted 
            if (p.idx < len2 -1 ) { // get to next value in nums2
                int next = p.idx+1;
                q.offer( new Pair(next, p.pair[0], nums2[next]) );
            }
        }
        return ret;
    }
}