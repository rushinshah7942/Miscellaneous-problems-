/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/

public class Solution {

    public Solution(int[] nums) {
        
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
 
/*
 
-> Math.random() returns a number from zero to one.

-> If I want to return an integer from zero to hundred, I would do:

(int) Math.floor(Math.random() * 101)

-> From one to hundred, I would do:

(int) Math.ceil(Math.random() * 100) 

-> (int)(Math.random() * 101);

To generate a number from 10 to 20 :

(int)(Math.random() * 11 + 10);

-> In the general case:

(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
(where lowerbound is inclusive and upperbound exclusive).



*/ 
/*-------------------------Solution--------------------------------*/

import java.util.Random;

public class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
	// The nextInt(int n) method is used to get a pseudorandom, uniformly distributed int value between 0 (inclusive) and 
	// the specified value (exclusive), drawn from this random number generator's sequence.
            
	
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) 
			return null;
        int[] a = nums.clone();
        for(int j = 1; j < a.length; j++) {
			int i = random.nextInt(j + 1); // random between 0 to j (inclusive)
			// int random = i + (int)(Math.random() * (n-i));
            swap(a, i, j);
        }
        return a;
    }
    
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}