/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.


*/

// for allowing 2 duplicates

public int removeDuplicates(int[] nums) {
    
	//Just go through the numbers and include those in the result that haven't been included twice already.
	// change 2 to k, for allowing k duplicates
	int i = 0;
    for (int n : nums)
        if (i < 2 || n > nums[i-2])
            nums[i++] = n;
    return i;
}

int removeDuplicates(int A[], int n, int k) {

	if (n <= k) return n;

	int i = 1, j = 1;
	int cnt = 1;
	while (j < n) {
		if (A[j] != A[j-1]) {
			cnt = 1;
			A[i++] = A[j];
		}
		else {
			if (cnt < k) {
				A[i++] = A[j];
				cnt++;
			}
		}
		++j;
	}
	return i;
}