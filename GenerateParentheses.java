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

// Recursion
// The idea here is to only add '(' and ')' that we know will guarantee us a solution (instead of adding 1 too many close). 
// Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. 
// Each of these steps are recursively called.

// Time : O(2 ^ (2N))
// As we  are always checking two possibility that left > 0 or right > 0
// Space: O(2N) = O(N) -> at a time , single valid sequence will be there

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
        if(left > right) // discard for this condition
            return;
		
        if(left==0 && right==0){
            result.add(s);
            return;
        }
        if(left>0){
            dfs(result, s+"(", left-1, right);
        }
        if(right>0){
            dfs(result, s+")", left, right-1);
        }
    }
}

/*

// execution
// I am ignoring the cases for left > right 

'' 3 3 -> 
 ( 2 3  -> left
    (( 1 3  -> left
        ((( 0 3 -> left  
            ((() 0 2 -> right
                ((()) 0 1 -> right
                    ((())) 0 0 -> ans
        (() 1 2 -> right 
           (()( 0 2 -> left 
               (()() 0 1 -> right
                    (()()) 0 0 -> ans
           
           (()) 1 1 -> right
               (())( 0 1 -> left
                   (())() 0 0 -> ans
    () 2 2 -> right
        ()( 1 2 -> left
            ()(( 0 2 -> left
                ()(() 0 1 -> right
                    ()(()) 0 0 -> ans
            ()() 1 1 -> right
                ()()( 0 1 -> left
                 ()()() -> ans                                      

 ) 3 2 -> left > right - just return 				 
*/

// Another approach

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
// Another - Iterative // https://discuss.leetcode.com/topic/3474/an-iterative-method
/*

My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1).
Actually, the result f(n) will be put an extra () pair to f(n-1). 

Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

Let us consider an example to get clear view:

f(0): ""

f(1): "("f(0)")"

f(2): "("f(0)")"f(1), "("f(1)")"

f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"

So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"

*/

public class Solution
{
    public List<String> generateParenthesis(int n)
    {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));
        
        for (int i = 1; i <= n; ++i)
        {
            final List<String> list = new ArrayList<>();
            
            for (int j = 0; j < i; ++j)
            {
                for (final String first : lists.get(j))
                {
                    for (final String second : lists.get(i - 1 - j))
                    {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            
            lists.add(list);
        }
        
        return lists.get(lists.size() - 1);
    }
}