/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Note:
You may assume that the array does not change.
There are many calls to sumRange function.

*/

// As it states that there are many calls to sumRange function, so we need to reduce time complexity from linear to constant


// initialization operation O(n)
// sumRange fuction runs in O(1)
public class NumArray 
{
    int[] nums;
    public NumArray(int[] nums) 
    {
        for(int i=1;i<nums.length;i++)
            nums[i]+= nums[i-1];
        
        this.nums = nums;
    }

    public int sumRange(int i, int j) 
    {
        if(i==0)
            return nums[j];
            
        return nums[j] - nums[i-1];    
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);

