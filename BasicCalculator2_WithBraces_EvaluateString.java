/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Note: Do not use the eval built-in library function.

*/


/*

Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
Only 5 possible input we need to pay attention:

digit: it should be one digit from the current num

'+': num is over, we can add the previous num and start a new num
'-': num is over, we can add(sign = -1) the previous num and start a new num

'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
')': pop out the top two nums from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.

Finally if there is only one num, from the above solution, we haven't add the num to the result, so we do a check see if the num is zero.

*/

public class Solution {
 	public int calculate(String s) {
		
		Stack<Integer> stack = new Stack<Integer>();
 		int result = 0;
		int num = 0;
		int sign = 1;
		
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			
			if(Character.isDigit(c)){
				num = 10 * num + (int)(c - '0');
			}else if(c == '+'){
			
				result += sign * num;

				num = 0;
				sign = 1;
			
			}else if(c == '-'){
				
				result += sign * num;
				
				num = 0;
				sign = -1;
				
			}else if(c == '('){
				//we push the result first, then sign;
				stack.push(result);
				stack.push(sign);
				
				//reset the sign and result for the value in the parenthesis
				sign = 1;   
				result = 0;
				
			}else if(c == ')'){
				
				result += sign * num;  
				num = 0;
				
				result *= stack.pop();    //stack.pop() is the sign before the parenthesis
				result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis				
			}
		}
		if(num != 0) 
			result += sign * num;
		
		return result;
    }
}



//--------------------------------------------------------------------------------
// Another Approach
// 

/*

remove all '(', ')', ' ';
reverse the express string;
add '+' or '-' to the end of the express.
By this approach, the reformed expression will be easy to handled.

"1 + 1"                             =>   "1+1+"
" 2-1 + 2 "                        =>   "2+1-2+"
"(1+(4+5+2)-3)+(6+8)"    =>   "8+6+3-2+5+4+1+"
"2-(5-6)"                          =>   "6+5-2+"

*/
public int calculate(String s) {
    if(s == null)
        return 0;
    s = reform(s);
    int result = 0, num = 0, base = 1;
    for(char c: s.toCharArray())
        switch(c){
        case '+': result += num; num = 0; base = 1; break;
        case '-': result -= num; num = 0; base = 1; break;
        default: num += (c - '0') * base; base *= 10;
        }
    return result;
}

private String reform(String s) {
    StringBuilder sb = new StringBuilder();
    Stack<Boolean> stack = new Stack<>();
    stack.push(true);
    boolean add = true;
    for(char c: s.toCharArray())
        switch(c){
        case ' ': break;
        case '(': stack.push(add); break;
        case ')': stack.pop(); break;
        case '+': 
            add = stack.peek(); 
            sb.append(stack.peek() ? '+' : '-'); 
            break;
        case '-': 
            add = !stack.peek(); 
            sb.append(stack.peek() ? '-' : '+'); 
            break;
        default: sb.append(c);
        }
    if(sb.charAt(0) != '+' || sb.charAt(0) != '-')
        sb.insert(0, '+');
    return sb.reverse().toString();
}