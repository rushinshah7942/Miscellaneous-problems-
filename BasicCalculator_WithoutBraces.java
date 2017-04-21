/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

Note: Do not use the eval built-in library function.
*/

public class Solution {
	public int calculate(String s) {
		int len;
		if(s==null || (len = s.length())==0) 
			return 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		
		for(int i=0;i < len; i++){
		
			if(Character.isDigit(s.charAt(i))){
				num = num*10 + s.charAt(i)-'0'; // do not push, right now!
			}
			if((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) 
				             || i == len)
			{ // if end of String, do operation with previous sign
				
				if(sign=='-'){
					stack.push(-num); // at last, we will do addition only, so add negative num of this
				}
				if(sign=='+'){
					stack.push(num);
				}
				if(sign=='*'){
					stack.push(stack.pop()*num);
				}
				if(sign=='/'){
					stack.push(stack.pop()/num);
				}
				
				sign = s.charAt(i); // now, change the sign
				num = 0; // initialize with 0
			}
		}

		// just add them up
		int re = 0;
		for(int i:stack){
			re += i;
		}
		return re;
	}
}