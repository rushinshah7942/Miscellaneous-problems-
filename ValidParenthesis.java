/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

// O(n^2) solution

public class Solution 
{
    public boolean isValid(String s) 
    {
        HashMap<Character,Character> hp = new HashMap<Character,Character>();
        
        hp.put('(',')');
        hp.put('{','}');
        hp.put('[',']');
        
        Stack stack = new Stack();
        
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(hp.containsKey(ch))
                stack.push(ch); // opening bracket
            else if(hp.values().contains(ch))
            {
				// values() method's time complexity is O(n) which is inside for loop
				// It came to my attention very late
				// Hence I also included O(n) solution . Without hashmap, with only stack.
				
                //closing bracket
                if(!stack.isEmpty() && hp.get(stack.peek()) == ch)
                {
                    stack.pop();
                }
                else 
                    return false;
            }
        }
        return stack.isEmpty(); 
    }
}

// o(n) solution

public boolean isValid(String s) 
{
	Stack<Character> stack = new Stack<Character>();
	for (char c : s.toCharArray()) 
	{
		if (c == '(')
			stack.push(')');
		else if (c == '{')
			stack.push('}');
		else if (c == '[')
			stack.push(']');
		else if (stack.isEmpty() || stack.pop() != c)
			return false;
	}
	return stack.isEmpty();
}