/*
Merge Sort - External
*/

External merge sort
-----------------------
One example of external sorting is the external merge sort algorithm, which sorts chunks that each fit in RAM, then merges the sorted chunks together. 

For example, for sorting 900 megabytes of data using only 100 megabytes of RAM:

1. Read 100 MB of the data in main memory and sort by some conventional method, like quicksort.
2. Write the sorted data to disk.

3. Repeat steps 1 and 2 until all of the data is in sorted 100 MB chunks (there are 900MB / 100MB = 9 chunks), which now need to be merged into one single output file.

4. Read the first 10 MB (= 100MB / (9 chunks + 1) ) of each sorted chunk into input buffers in main memory and allocate the remaining 10 MB for an output buffer. (In practice, it might provide better performance to make the output buffer larger and the input buffers slightly smaller.)

5. Perform a 9-way merge and store the result in the output buffer. Whenever the output buffer fills, write it to the final sorted file and empty it. Whenever any of the 9 input buffers empties, fill it with the next 10 MB of its associated 100 MB sorted chunk until no more data from the chunk is available. 
This is the key step that makes external merge sort work externally -- because the merge algorithm only makes one pass sequentially through each of the chunks, each chunk does not have to be loaded completely; rather, sequential parts of the chunk can be loaded as needed.
---------------------------
K-way merging              |
---------------------------

k-way merging generalizes binary merging to an arbitrary number k of sorted input lists. Applications of k-way merging arise in various sorting algorithms, including patience sorting[5] and an external sorting algorithm that divides its input into k = 
1
/
M
 − 1 blocks that fit in memory, sorts these one by one, then merges these blocks.[2]:119–120
Several solutions to this problem exist. A naive solution is to do a loop over the k lists to pick off the minimum element each time, and repeat this loop until all lists are empty:
Input: a list of k lists.
While any of the lists is non-empty:
Loop over the lists to find the one with the minimum first element.
Output the minimum element and remove it from its list.
In the worst case, this algorithm performs (k−1)(n−
k
/
2
) element comparisons to perform its work if there are a total of n elements in the lists.[6] It can be improved by storing the lists in a priority queue (min-heap) keyed by their first element:
Build a min-heap h of the k lists, using the first element as the key.
While any of the lists is non-empty:
Let i = find-min(h).
Output the first element of list i and remove it from its list.
Re-heapify h.
Searching for the next smallest element to be output (find-min) and restoring heap order can now be done in O(log k) time (more specifically, 2⌊log k⌋ comparisons[6]), and the full problem can be solved in O(n log k) time (approximately 2n⌊log k⌋ comparisons).[6][2]:119–120
A third algorithm for the problem is a divide and conquer solution that builds on the binary merge algorithm:
If k = 1, output the single input list.
If k = 2, perform a binary merge.
Else, recursively merge the first ⌊k/2⌋ lists and the final ⌈k/2⌉ lists, then binary merge these.
When the input lists to this algorithm are ordered by length, shortest first, it requires fewer than n⌈log k⌉ comparisons, i.e., less than half the number used by the heap-based algorithm; in practice, it may be about as fast or slow as the heap-based algorithm.[6]



-------------------------
Tuning performance       |
-------------------------
The Sort Benchmark, created by computer scientist Jim Gray, compares external sorting algorithms implemented using finely tuned hardware and software. Winning implementations use several techniques:

Using parallelism
-------------------
Multiple disk drives can be used in parallel in order to improve sequential read and write speed. This can be a very cost-efficient improvement: a Sort Benchmark winner in the cost-centric Penny Sort category uses six hard drives in an otherwise midrange machine.[6]
Sorting software can use multiple threads, to speed up the process on modern multicore computers.
Software can use asynchronous I/O so that one run of data can be sorted or merged while other runs are being read from or written to disk.
Multiple machines connected by fast network links can each sort part of a huge dataset in parallel.[7]

Increasing hardware speed
-----------------------------
Using more RAM for sorting can reduce the number of disk seeks and avoid the need for more passes.
Fast external memory like solid-state drives can speed sorts, either if the data is small enough to fit entirely on SSDs or, more rarely, to accelerate sorting SSD-sized chunks in a three-pass sort.
Many other factors can affect hardware's maximum sorting speed: CPU speed and number of cores, RAM access latency, input/output bandwidth, disk read/write speed, disk seek time, and others. "Balancing" the hardware to minimize bottlenecks is an important part of designing an efficient sorting system.
Cost-efficiency as well as absolute speed can be critical, especially in cluster environments where lower node costs allow purchasing more nodes.

Increasing software speed
--------------------------
Some Sort Benchmark entrants use a variation on radix sort for the first phase of sorting: they separate data into one of many "bins" based on the beginning of its value. Sort Benchmark data is random and especially well-suited to this optimization.
Compacting the input, intermediate files, and output can reduce time spent on I/O, but is not allowed in the Sort Benchmark.
Because the Sort Benchmark sorts long (100-byte) records using short (10-byte) keys, sorting software sometimes rearranges the keys separately from the values to reduce memory I/O volume.