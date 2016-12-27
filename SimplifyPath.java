/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

*/

public class Solution {
    public String simplifyPath(String path) {

		// always use Deque, Java's built-in Stack iterator methods are inherited from other classes, so it might not follow LIFO property.
		Deque<String> stack = new LinkedList<>();
		Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
		
		for (String dir : path.split("/")) {
			
			if (dir.equals("..") && !stack.isEmpty()) 
				stack.pop();
			else if (!skip.contains(dir)) 
				stack.push(dir);
		
		}
		
		String res = "";
		for (String dir : stack) 
			res = "/" + dir + res;
		
		return res.isEmpty() ? "/" : res;
	}
}