/*
Count the number of prime numbers less than a non-negative number, n.
*/

// Brute-Force Approach o(N^1.5)

public class Solution 
{
    public int countPrimes(int n) 
    {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) 
				count++;
        }
        return count;
    }

    private boolean isPrime(int num) {
        if (num <= 1) 
			return false;
		
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) 
				return false;
        }
        return true;
    }
}


// Sieve of Eratosthenes - Preferrable for small prime numbers
// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
// Time -> O(n * log(log n))
// which is faster than O(n * n^(1/2))
// 40 ms 
public class Solution 
{
    public int countPrimes(int n) 
    {
        boolean[] notPrime = new boolean[n]; 
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
				// all multiples of i will be marked true in notPrime[] array
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count;
    }
}
	
// 21 ms

public int countPrimes(int n) {
    if (n < 3)
        return 0;
        
    boolean[] f = new boolean[n];
    //Arrays.fill(f, true); boolean[] are initialed as false by default
    int count = n / 2;
    for (int i = 3; i * i < n; i += 2) {
        if (f[i])
            continue;
        
        for (int j = i * i; j < n; j += 2 * i) {
            if (!f[j]) {
                --count;
                f[j] = true;
            }
        }
    }
    return count;
}


//
public int countPrimes(int n) {
        
        /**
         * if n = 2, the prime 2 is not less than n,
         * so there are no primes less than n
         */
        if (n < 3) return 0;
        
        /** 
         * Start with the assumption that half the numbers below n are
         * prime candidates, since we know that half of them are even,
         * and so _in general_ aren't prime.
         * 
         * An exception to this is 2, which is the only even prime.
         * But also 1 is an odd which isn't prime.
         * These two exceptions (a prime even and a for-sure not-prime odd)
         * cancel each other out for n > 2, so our assumption holds.
         * 
         * We'll decrement count when we find an odd which isn't prime.
         *
         * If n = 3,  c = 1.
         * If n = 5,  c = 2.
         * If n = 10, c = 5.
         */
        int c = n / 2;
        
        /**
         * Java initializes boolean arrays to {false}.
         * In this method, we'll use truth to mark _composite_ numbers.
         * 
         * This is the opposite of most Sieve of Eratosthenes methods,
         * which use truth to mark _prime_ numbers.
         * 
         * We will _NOT_ mark evens as composite, even though they are.
         * This is because `c` is current after each `i` iteration below.
         */
        boolean[] s = new boolean[n];
        
        /**
         * Starting with an odd prime-candidate above 2, increment by two
         * to skip evens (which we know are not prime candidates).
         */
        for (int i = 3; i * i < n; i += 2) {
            if (s[i]) {
                // c has already been decremented for this composite odd
                continue;
            }
            
            /** 
             * For each prime i, iterate through the odd composites
             * we know we can form from i, and mark them as composite
             * if not already marked.
             * 
             * We know that i * i is composite.
             * We also know that i * i + i is composite, since they share
             * a common factor of i.
             * Thus, we also know that i * i + a*i is composite for all real a,
             * since they share a common factor of i.
             * 
             * Note, though, that i * i + i _must_ be composite for an
             * independent reason: it must be even.
             * (all i are odd, thus all i*i are odd,
             * thus all (odd + odd) are even).
             * 
             * Recall that, by initializing c to n/2, we already accounted for
             * all of the evens less than n being composite, and so marking
             * i * i + (odd)*i as composite is needless bookkeeping.
             * 
             * So, we can skip checking i * i + a*i for all odd a,
             * and just increment j by even multiples of i,
             * since all (odd + even) are odd.
             */
            for (int j = i * i; j < n; j += 2 * i) {
                if (!s[j]) {
                    c--;
                    s[j] = true;
                    }
                }
            }
        return c;
    }
}