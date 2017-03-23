/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

// Time complexity : O(n logk)
// Space : O(k)

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int val : nums){
            
            if(map.containsKey(val)){
                map.put(val, map.get(val)+1);
            }
            else{
                map.put(val,1);
            }
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->a.getValue() - b.getValue()); // lamda expression - Java8
        
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            pq.offer(entry);
            if(pq.size() > k){
                pq.poll(); // removes one with smallest count, as ours is Min-Heap.
            }
        }
        
        List<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
        
    }
    /*
    can also use Custom Comparator class in PriorityQueue
    
    static class frequentComparator extends Comparator<Map.Entry<Integer,Integer>>{
        public int compare(Map.Entry<Integer> o1, Map.Entry<Integer> o2){
            return o1.getValue() - o2.getValue();
        }
    }
    */
}

// Using Bucket Sort
// Time O(n)
// Space O(n)
// Much Faster than using PriorityQueue

public List<Integer> topKFrequent(int[] nums, int k) {

	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
		frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	// assgin elements in bucket and frequency will serve as bucket index
	for (int key : frequencyMap.keySet()) {
		int frequency = frequencyMap.get(key);
		if (bucket[frequency] == null) {
			bucket[frequency] = new ArrayList<>(); // imp step
		}
		bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos]);
		}
	}
	return res;
}