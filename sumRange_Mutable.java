/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/
-----------------
Solution - 1
-----------------
A simple solution is to run a loop from l to r and calculate sum of elements in given range. To update a value, simply do arr[i] = x. The first operation takes O(n) time and second operation takes O(1) time.

----------------------
Solution - 2
---------------------
Another solution is to create another array and store sum from start to i at the ith index in this array. Sum of a given range can now be calculated in O(1) time, but update operation takes O(n) time now. This works well if the number of query operations are large and very few updates.

-------------------
Solution - 3
-------------------
public class NumArray {

    int n;
    int[] raw;
    NumArray parent;

    public NumArray(int[] nums) {
        n = nums.length;
        raw = nums;
        if (n>1) {
            int[] parRaw = new int[(n+1)/2];
            for (int i=0; i<n; i++) {
                parRaw[i/2]+=nums[i];
            }
            parent = new NumArray(parRaw);
        }
    }

    public void update(int i, int val) {
        if (n>1) {
            parent.update(i/2, parent.get(i/2)-raw[i]+val);
        }
        raw[i]=val;
    }

    public int get(int i) {
        return raw[i];
    }

    public int sumRange(int i, int j) {
        if (i>0) {
            return sumRange(0,j) - sumRange(0,i-1);
        } else if (j==0) {
            return raw[0];
        } else {
            int sum = parent.sumRange(0,j/2);
            if (j%2==0 && j+1<n) {
                sum -= raw[j+1];
            }
            return sum;
        }
    }
}