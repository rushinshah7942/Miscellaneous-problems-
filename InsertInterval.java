/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

*/

public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    
	List<Interval> result = new ArrayList<Interval>();
    
	for (Interval i : intervals) {
    
		if (newInterval == null || i.end < newInterval.start)
            result.add(i); // add this interval and go for next one
        else if (i.start > newInterval.end) {
            result.add(newInterval); // add new interval before this interval
            result.add(i);
            newInterval = null; // make newInterval as null
        } else {
			// [1,3] and newInterval-> [2,5]
            newInterval.start = Math.min(newInterval.start, i.start);  // start -> min
            newInterval.end = Math.max(newInterval.end, i.end); // end -> max
			// keep adjusting new interval's start and end 
        }
    }
	
	// if the largest non-overlapping one
    if (newInterval != null)
        result.add(newInterval);
    
	return result;
}