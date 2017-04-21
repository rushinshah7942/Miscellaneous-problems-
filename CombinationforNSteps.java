/*

If you can hop 1, 2, or 3 steps at a time, calculate the total number of possible combinations for `n` steps.



Clearly one can make only 1 step, 1 way.
If n = 2, then one can make 1 + 1 or 2, so 2 ways. 
If n = 3, then one can make 1 + 1 + 1, 2 + 1, 1 + 2, 3 -> 4 ways.
Thus, the pattern is :
n < 1 : 1 
n = 1 : 1
n = 2 : 2 
n = 3 : 4 
...
Giving: ( it is a rip off of Generalized Fibonacci like sequences ) 

S(n) = S(n-1) + S(n-2) + S(n-3)

	= O(3^n) 
	
=====
S(3) = S(2) + S(1) + S(0) = 4 
S(4) = S(3) + S(2) + S(1) = 4 + 2 + 1 = 7
=== is it correct? Let's verify ? ====
1 + 1 + 1 + 1 
1+1 + 2 
1+ 2 + 1
2 + 1 + 1 
2 + 2 
1 + 3 
3 + 1 
===> Yess!


*/

