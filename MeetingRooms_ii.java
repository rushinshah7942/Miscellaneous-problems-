/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.

*/

// time O(n*logn)

public int minMeetingRooms(Interval[] intervals) {
    if(intervals==null || intervals.length==0)
        return 0;
 
    Arrays.sort(intervals, new Comparator<Interval>(){
        public int compare(Interval i1, Interval i2){
            return i1.start-i2.start;
        }
    });
 
    PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
    int count=1;
    queue.offer(intervals[0].end);
 
    for(int i=1; i<intervals.length; i++){
		
        if(intervals[i].start < queue.peek()){
            count++;
 
        }else{
            queue.poll();
        }
 
        queue.offer(intervals[i].end);
    }
 
    return count;
}