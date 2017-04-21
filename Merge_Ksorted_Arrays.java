/*
	Merge k sorted arrays -> of same size
	--------------------------------------
	This is a classic interview question. Another similar problem is "merge k sorted lists".

	This problem can be solved by using a heap. The time is O(nlog(n)).

	Given m arrays, the minimum elements of all arrays can form a heap. It takes O(log(m)) to insert an element to the heap and it takes O(1) to delete the minimum element.

*/

class ArrayContainer implements Comparable<ArrayContainer> {
	int[] arr;
	int index;
 
	public ArrayContainer(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
 
	@Override
	public int compareTo(ArrayContainer o) {
		return this.arr[this.index] - o.arr[o.index]; // compare two elements of two different arrays
	}
}
// after removing from the heap you must add the "next element from the exact array which we had root from." Knowing what the next element in the array is or what array an element came from gets pretty ugly without something like an ArrayContainer class.
public class KSortedArray {
	
	public static int[] mergeKSortedArray(int[][] arr) {
		
		// input: 2D matrix is given
		
		//PriorityQueue is heap in Java 
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
		
		int total=0;
 
		//add arrays to heap
		for (int i = 0; i < arr.length; i++) {
			queue.add(new ArrayContainer(arr[i], 0)); // add all first elements of each array
			total = total + arr[i].length; // to create result array, keep storing the total length
		}
 
		int m = 0;
		int result[] = new int[total];
 
		//while heap is not empty
		while(!queue.isEmpty()){
			
			ArrayContainer ac = queue.poll();
			
			result[m++] = ac.arr[ac.index];
 
			if(ac.index < ac.arr.length-1){
				queue.add(new ArrayContainer(ac.arr, ac.index+1)); // add next element of that array whose value we just used
			}
		}
 
		return result;
	}
 
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };
 
		int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
		System.out.println(Arrays.toString(result));
	}
}
