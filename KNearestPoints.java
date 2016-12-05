/*
Given a list of points and a central point, find the k nearest points from the central point.

Solution:
Use a max heap. First store the first K points into the heap. Then compare the distance between the current point and the max point. If less than the max value, then poll out the max and add the current point into the heap.

*/


// Using heap as implementation of priority queue to store the first k points. 
// So the complexity is O(n log k) with O(k) memory.

public class Solution {
	
    public List<Point> findKNearestPoints(List<Point> points, Point original, int k) {
        List<Point> result = new ArrayList<>();
         
        if (points == null || ponts.size() == 0 || original == null || k <= 0) {
            return result;
        }
         
        PriorityQueue<Point> pq = new PriorityQueue<>(k, Collections.reverseOrder()); // capacity k specified, with Max-heap
		
		// Default implementation of PriorityQueue is Min-Heap , hence root(head of queue) will always be smallest element
         
        for (Point point : points) {
            if (pq.size() < k) {
                pq.offer(point);
            } else {
                if (pq.peek().compareTo(point) > 0) { // new distance is smaller than head of queue
                    pq.poll();
                    pq.offer(point);
                }
            }
        }
         
        result.addAll(pq);
        return result;
    }
     
    class Point implements Comparable<Point> {
        int x, y;
        double distance;
         
        public Point (int x, int y, Point original) {
            this.x= x;
            this.y = y;
             
            // sqrt(x^2 + y^2)
            distance = Math.hypot(x - original.x, y - original.y);
        }
         
        @Override
        public int compareTo(Point that) {
            return this.distance.compareTo(that.distance);
        }
    }
}