/*
Different Ways to Add Parentheses
----------------------------------
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

*/

/*
Solution: 
-------------------
For each operator in the list, we compute all possible results for entries to the left of that operator, which is List<Integer> left, and also all possible results for entries to the right of that operator, namely List<Integer> right, and combine the results. It can be achieved by recursion or more efficiently by dp.
*/

// Recursion
// Time complexity : O(3 ^ n)
// for each + , - , *, it goes into recursion for length of input(n)

public class Solution {
    
    Map<String, List<Integer>> map = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String input) {
        
		List<Integer> ret = new LinkedList<Integer>();
        
    	for (int i=0; i < input.length(); i++) {
			/*
			map.getOrDefault as below or this
			
			if(map.containsKey(input))
				return map.get(input);
			*/
			
    		// only for each operator, part left and right side
    		if (input.charAt(i) == '-' ||
    			input.charAt(i) == '*' ||
    			input.charAt(i) == '+' ) 
    		{
    			String part1 = input.substring(0, i);
    			String part2 = input.substring(i+1);
    
    			List<Integer> part1Ret = map.getOrDefault(part1, diffWaysToCompute(part1)); // memoization
    			List<Integer> part2Ret = map.getOrDefault(part2, diffWaysToCompute(part2)); // memoization
                // List<Integer> part1Ret = diffWaysToCompute(part1);        
    			// List<Integer> part2Ret = diffWaysToCompute(part2);        
    			
				for (Integer p1 :   part1Ret) {
    			
					for (Integer p2 :   part2Ret) {
    				
						int ans = 0;
    					
						switch (input.charAt(i)) {
    						case '+': ans = p1 + p2;
    							break;
    						case '-': ans = p1 - p2;
    							break;
    						case '*': ans = p1 * p2;
    							break;
    					}
						System.out.println("ans: " + ans);
    					ret.add(ans); 
    				}
    			}
    		} // if-end
			
    	} // for-end
		
    	if (ret.size() == 0) { // add single digits as well
    		ret.add(Integer.valueOf(input));
    	}
    	
    	map.put(input, ret);
    	return ret;
    }
}

/* 

Execution for -> input: 2*3-4*5
-------------------------------

Your input
-----------------
"2*3-4*5"


Your stdout
---------------------
input: 2*3-4*5
input: 2
input: 3-4*5
input: 3
input: 4*5
input: 4
input: 5
part1 : [4] part2 : [5]
ans: 20
part1 : [3] part2 : [20]
ans: -17
input: 3-4
input: 3
input: 4
part1 : [3] part2 : [4]
ans: -1
input: 5
part1 : [-1] part2 : [5]
ans: -5
part1 : [2] part2 : [-17, -5]
ans: -34 =>>>> (2*(3-(4*5))) 
ans: -10 =>>>(2*((3-4)*5))
input: 2*3
input: 2
input: 3
part1 : [2] part2 : [3]
ans: 6
input: 4*5
input: 4
input: 5
part1 : [4] part2 : [5]
ans: 20
part1 : [6] part2 : [20]
ans: -14 =>>> ((2*3)-(4*5)) 
input: 2*3-4
input: 2
input: 3-4
input: 3
input: 4
part1 : [3] part2 : [4]
ans: -1
part1 : [2] part2 : [-1]
ans: -2
input: 2*3
input: 2
input: 3
part1 : [2] part2 : [3]
ans: 6
input: 4
part1 : [6] part2 : [4]
ans: 2
input: 5
part1 : [-2, 2] part2 : [5]
ans: -10 ==> ((2*(3-4))*5)
ans: 10 =>> (((2*3)-4)*5)


HashMap -> for memoization

input: 2*3-4*5

{2=[2], 3=[3], 4=[4], 5=[5], 3-4*5=[-17, -5], 2*3-4=[-2, 2], 3-4=[-1], 2*3-4*5=[-34, -10, -14, -10, 10], 2*3=[6], 4*5=[20]}

*/
