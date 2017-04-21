/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

*/

// Follow-up after first part of Nested List Weight Sum
// Java Solution

public int depthSumInverse(List<NestedInteger> nestedList) {
    if(nestedList==null||nestedList.size()==0)
        return 0;
 
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); 
	// key would be Layer
	// value would be ArrayList of NestedInteger
 
    //two stacks: one is for processing nested integer, the other is for tracking layers. 
    Stack<NestedInteger> stack = new Stack<NestedInteger>();
    Stack<Integer> layers = new Stack<Integer>();
 
    //put all NestedIntegers to Stack and record its layer to be 1
    for(NestedInteger ni: nestedList){
        stack.push(ni);
        layers.push(1);
    }
 
    int maxLayer=Integer.MIN_VALUE;
 
    while(!stack.isEmpty()){
        
		NestedInteger top = stack.pop();
        int topLayer = layers.pop();
 
        maxLayer=Math.max(maxLayer, topLayer); // we need maxLayer to keep track, how deep we went to 
        // e.g. [1,[4,[2,3]], 5]
		//       1  2  3 3    1   
		// maxLayer shoule be 3 
		
        if(top.isInteger()){
            if(map.containsKey(topLayer)){
                map.get(topLayer).add(top.getInteger());
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(top.getInteger());
                map.put(topLayer, list);
            }        
        }else{
            for(NestedInteger ni: top.getList()){
                stack.push(ni);
                layers.push(topLayer+1);
            }
        }
    }
 
    // calcualte sum
    int result=0;
    for(int i=maxLayer; i>=1; i--){
        if(map.get(i)!=null){
            for(int v: map.get(i)){
                result += v*(maxLayer-i+1);
            }
        }
    }
 
    return result;
}