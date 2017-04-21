/*

Write a program that takes an integer and prints out all ways to multiply smaller integers that equal the original number, without repeating sets of factors. In other words, if your output contains 4 * 3, you should not print out 3 * 4 again as that would be a repeating set. Note that this is not asking for prime factorization only. Also, you can assume that the input integers are reasonable in size; correctness is more important than efficiency. 

Eg: PrintFactors(12) 
12 * 1 
6 * 2 
4 * 3 
3 * 2 * 2

*/

public static void printFactors(int number) {
    printFactors("", number, number);
}

/*
 * This function contains factorString as an argument to facilitate the recursive call for subsequent
 * factors until it reaches prime values. 
 * For example, let's say input number = 32 and when i = 8 it prints
 * 8*(32/8) ==> 8 * 4 but the subsequent reduction of 4 is needed and 
 * this is done by recursively passing in 4
 * as number. But we also want to maintain the chain "8 * ". So this makes the carry over string as an input
 * argument for the helper function printFactorsList
 */
 
// Time Complexity : PrintFactorsHelper itself takes O(n) from the for loop. PrintFactorsHelper will be called at max klog(n) times, since we are always dividing (at least by two) the largest factor, so the resulting complexity should be O(n*log(n)).
 
public static void printFactors(String expression, int dividend, int previous) {
    if(expression == "") // base case
        System.out.println(previous + " * 1");

    for (int factor = dividend - 1; factor >= 2; --factor) {
        
		// only if it's factor
		if (dividend % factor == 0 && factor <= previous) {
            int next = dividend / factor;   
            if (next <= factor) 			// for case, next = 4 and factor = 3, as we going from bigger to smaller factors 
                if (next <= previous)
                    System.out.println(expression + factor + " * " + next);

            printFactors(expression + factor + " * ", next, factor); // this recursion line is important!!
			// we have seen factor, then find more factors of next
        }
    }
}