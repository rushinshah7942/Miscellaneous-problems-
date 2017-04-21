/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.

*/

// If we can use ++ or -- operation 
// Should be able to handle negative numbers as well

function sum(a, b) {
  while(a > 0) { --a; ++b };
  while(a < 0) { ++a; --b };
  return b;
}

// Iterative sum
public int getSum(int a, int b) {
	if (a == 0) 
		return b;
	if (b == 0) 
		return a;

	while (b != 0) {
		int carry = a & b; // find the carry and move the carry to left 
		a = a ^ b; // store unique ones in both a and b
		b = carry << 1;// move carry left 
	}
	
	return a;
}

// Iterative subtract
public int getSubtract(int a, int b) {
	while (b != 0) {
		int borrow = (~a) & b; // instead of carry, we borrow
		a = a ^ b;
		b = borrow << 1;
	}
	
	return a;
}

// Recursive
public int getSum(int a, int b) {
	return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
}

// Recursive
public int getSubtract(int a, int b) {
	return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
}

// Get negative number
public int negate(int x) {
	return ~x + 1;
}