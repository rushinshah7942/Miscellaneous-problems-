/*

Given an unsorted array of positive integers. Find the number of triangles that can be formed with three different array elements as three sides of triangles. For a triangle to be possible from 3 values, the sum of any two values (or sides) must be greater than the third value (or third side).

For example, if the input array is {4, 6, 3, 7}, the output should be 3. There are three triangles possible {3, 4, 6}, {4, 6, 7} and {3, 6, 7}. Note that {3, 4, 7} is not a possible triangle.
As another example, consider the array {10, 21, 22, 100, 101, 200, 300}. There can be 6 possible triangles: {10, 21, 22}, {21, 100, 101}, {22, 100, 101}, {10, 100, 101}, {100, 101, 200} and {101, 200, 300}

*/

//brute force, O(n^3)

vector< vector<int> > findTriangles(vector<int> nums) {
    vector< vector<int> > triangles;
    vector<int> triangle;
    int i, j, k;

    for (i = 0; i < nums.size() - 2; i++) {
        for (j = i + 1; j < nums.size() - 1; j++) {
            for (k = j + 1; k < nums.size(); k++) {
                if (nums[i] + nums[j] <= nums[k]) continue;
                if (nums[j] + nums[k] <= nums[i]) continue;
                if (nums[k] + nums[i] <= nums[j]) continue;
                triangle.clear();
                triangle.push_back(nums[i]);
                triangle.push_back(nums[j]);
                triangle.push_back(nums[k]);
                triangles.push_back(triangle);
            }
        }
    }

    return triangles;
}


class CountTriangles
{
    // Function to count all possible triangles with arr[]
    // elements
    static int findNumberOfTriangles(int arr[])
    {
        int n = arr.length;
     
		// Sort the array elements in non-decreasing order
        Arrays.sort(arr);
 
        // Initialize count of triangles
        int count = 0;
 
        for (int i = 0; i < n-2; ++i)
        {
            // Initialize index of the rightmost third element
            int k = i + 2;
 
            // Fix the second element
            for (int j = i+1; j < n -1 ; ++j)
            {
                while (k < n && arr[i] + arr[j] > arr[k])
                    ++k;
 
                count += (k - j - 1);
            }
        }
        return count;
    }
}
