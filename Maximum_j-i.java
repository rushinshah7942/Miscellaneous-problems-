/*
Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
*/

/*
  Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
  Output: 6  (j = 7, i = 1)

  Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
  Output: 8 ( j = 8, i = 0)

  Input:  {1, 2, 3, 4, 5, 6}
  Output: 5  (j = 5, i = 0)

  Input:  {6, 5, 4, 3, 2, 1}
  Output: -1 
*/


/*
we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest element on left side of arr[i] including arr[i], and RMax[j] holds the greatest element on right side of arr[j] including arr[j]. After constructing these two auxiliary arrays, we traverse both of these arrays from left to right. While traversing LMin[] and RMa[] if we see that LMin[i] is greater than RMax[j], then we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are greater than or equal to LMin[i]. Otherwise we must move ahead in RMax[j] to look for a greater j – i value.
*/
int max(int x, int y) {
	return x > y ? x : y;
}

int min(int x, int y) {
	return x < y ? x : y;
}
 
public int maxIndexDiff(int arr[], int n) {
	int maxDiff;
	int i, j;

	int RMax[] = new int[n];
	int LMin[] = new int[n];

	/* Construct LMin[] such that LMin[i] stores the minimum value
	   from (arr[0], arr[1], ... arr[i]) */
	LMin[0] = arr[0];
	for (i = 1; i < n; ++i)
		LMin[i] = min(arr[i], LMin[i - 1]);

	/* Construct RMax[] such that RMax[j] stores the maximum value
	   from (arr[j], arr[j+1], ..arr[n-1]) */
	RMax[n - 1] = arr[n - 1];
	for (j = n - 2; j >= 0; --j)
		RMax[j] = max(arr[j], RMax[j + 1]);

	/* Traverse both arrays from left to right to find optimum j - i
	   This process is similar to merge() of MergeSort */
	i = 0; j = 0; maxDiff = -1;
	while (j < n && i < n) {
		if (LMin[i] < RMax[j]) {
			maxDiff = max(maxDiff, j - i);
			j = j + 1;
		} 
		else
			i = i + 1;
	}
	return maxDiff;
}