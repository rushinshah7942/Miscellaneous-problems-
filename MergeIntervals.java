/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 
// Optimized solution
// The idea is to sort the intervals by their starting points. Then, we take the first interval and compare its end with the next intervals starts. As long as they overlap, we update the end to be the max end of the overlapping intervals. Once we find a non overlapping interval, we can add the previous "extended" interval and start over.

// Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n)).

public List<Interval> merge(List<Interval> intervals) {
 
	if (intervals.size() <= 1)
        return intervals;
    
    // Sort by ascending starting point using an anonymous Comparator
    intervals.sort( (i1, i2) -> Integer.compare(i1.start, i2.start) );
    /*
	Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) {
					return i1.start - i2.start;
				}
				return i1.end - i2.end;
			}
		});
	*/
	
    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;
    
    for (Interval interval : intervals) {
        if (interval.start <= end) // Overlapping intervals, keep moving the end if needed
            end = Math.max(end, interval.end);
        else {                     // Disjoint intervals, add the previous one and reset bounds
            result.add(new Interval(start, end));
            start = interval.start;
            end = interval.end;
        }
    }
    
    // Add the last interval, corner case, do with taking example
    result.add(new Interval(start, end));
	
    return result;
}

 
 
 
// Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n)). 
// But internally it removes from iterator, which is O(n)
// hence, it's O(n^2)

 public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
		
		if (intervals == null || intervals.isEmpty())
			return intervals;
		
		// Sort by ascending starting point using an anonymous Comparator
		// intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
		// or 
		
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) {
					return i1.start - i2.start;
				}
				return i1.end - i2.end;
			}
		});
		
		ListIterator<Interval> it = intervals.listIterator();
		Interval cur = it.next();
		
		while (it.hasNext()) {
			Interval next = it.next();
			if (cur.end < next.start) {
				cur = next;
				continue;
			} else {
				cur.end = Math.max(cur.end, next.end);
				it.remove();
			}
		}
		return intervals;
	}
}