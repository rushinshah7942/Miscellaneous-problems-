/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

// Iterative approach
void generate(int n) 
{
    ArrayList<String> results = new ArrayList<String>();
    generateParentheses(n, 0, new StringBuilder(), results);
    System.out.println(results);
}

void generateParentheses(final int remaining, final int openCount, final StringBuilder s, final List<String> results) 
{
    if (remaining == 0 && openCount == 0) 
	{
        results.add(s.toString());
        return;
    }
    if (openCount > 0) 
	{ // we can close the open one
        s.append(")");
        generateParentheses(remaining, openCount-1, s, results);
        s.setLength(s.length()-1); // pop the last char off
    }
    if (remaining > 0) 
	{ // start a new one
        s.append("(");
        generateParentheses(remaining-1, openCount+1, s, results);
        s.setLength(s.length()-1); // pop the last char off
    }
}


// Recursion

public class Solution 
{
    public List<String> generateParenthesis(int n) 
    {
        // recursive soliution
        // EXPLAINED: http://stackoverflow.com/questions/23413881/understanding-function-to-generate-parentheses/23414519
        
        ArrayList<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }
    public void dfs(ArrayList<String> result, String s, int left, int right)
    {
        if(left > right)
            return;
        if(left==0&&right==0)
        {
            result.add(s);
            return;
        }
        if(left>0)
        {
            dfs(result, s+"(", left-1, right);
        }
        if(right>0)
        {
            dfs(result, s+")", left, right-1);
        }
    }
}


// Output

(
	()

		()(
			()()

				()()(
					()()()


			()((
				()(()
					()(())



	((
		(()
			(())

				(())(
					(())()


			(()(
				(()()
					(()())



		(((
			((()
				((())
					((()))



[()()(), ()(()), (())(), (()()), ((()))]
