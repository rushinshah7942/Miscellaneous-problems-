/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
*/

/*


public class MedianFinder {

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        
    }
    
    public double findMedian() {
        
    }
}
*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 
 
 
 
public class MedianFinder {

    PriorityQueue<Integer> minHeap; // upper-half
    PriorityQueue<Integer> maxHeap; // lower-half
    
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>(); // lowest element in upper half will be root
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // maximum element from lower-half will be root
    }
    
    // Adds a number into the data structure.
	// Time : O(log n)
    public void addNum(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
		
		// to keep both the priority queues balanced
        if(minHeap.size() >= maxHeap.size()+2) {
            maxHeap.add(minHeap.remove());
        } else if(maxHeap.size() >= minHeap.size()+2){ 
            minHeap.add(maxHeap.remove());
        }
		
		/*
		or just 4 lines as below 
		
		maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
		*/
		
		
    }

    // Returns the median (note: double) of current data stream
	// Time : O(1)
    public double findMedian() {
        
		if(minHeap.size() == maxHeap.size()) {
            return (double)(minHeap.peek()+maxHeap.peek())/2;
        } else if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        } // their size difference would be at most 1
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian(); 