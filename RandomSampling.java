/*
How to randomly select a number in an array? 
array: [15, 2, 4, 5, 1, -2, 0] 

Follow-up: 
Given a second array freq where freq[i] represents the occurrence of the ith number in array, how to randomly select a number in array based on the frequency. 

Extra requirement: 
Could you complete the selection in a single-pass(go through each array only once)?

*/

public static int getRandom(int[] array) {
    int rnd = new Random().nextInt(array.length); // inclusive 0 and exclusive array.length(n)
    return array[rnd];
}

/*
 
 "inverse transform sampling" or roulette wheel selection

The idea for follow-up (Extra-array and Two Pass):

-> Iterate through all the elements and set the value of each element as the cumulative frequency thus far. (CDF - cumulative distribution function)
-> Generate a random number between 1 and the sum of all frequencies
-> Do a binary search on the values for this number (finding the first value greater than or equal to the number).

Example:
-------------
Element    A B C D
Frequency  1 4 3 2
Cumulative 1 5 8 10 // generate random and do binary search -> time O(log n)

Generate a random number in the range 1-10 (1+4+3+2 = 10, the same as the last value in the cumulative list), do a binary search, which will return values as follows:

Number   Element returned
1        A
2        B
3        B
4        B
5        B
6        C
7        C
8        C
9        D
10       D
*/


// Java - linear O(n) version
// Returns the selected index based on the weights(probabilities)

int rouletteSelect(double[] weight) {
	// calculate the total weight
	double weight_sum = 0;
	for(int i=0; i<weight.length; i++) {
		weight_sum += weight[i];
	}
	// get a random value
	double value = randUniformPositive() * weight_sum;	

	// locate the random value based on the weights
	for(int i=0; i<weight.length; i++) {		
		value -= weight[i];		
		if(value <= 0) 
			return i;
	} // do binary search

	// when rounding errors occur, we return the last item's index 
	return weight.length - 1;
}

// Returns a uniformly distributed double value between 0.0 and 1.0
double randUniformPositive() {
	// easiest implementation
	return new Random().nextDouble();
}


/*
Alias method
-------------------
In computing, the alias method is a family of efficient algorithms for sampling from a discrete probability distribution, due to A. J. Walker.[1][2] That is, it returns integer values 1 ≤ i ≤ n according to some arbitrary probability distribution pi. The algorithms typically use O(n log n) or O(n) preprocessing time, after which random values can be drawn from the distribution in O(1) time

*/

