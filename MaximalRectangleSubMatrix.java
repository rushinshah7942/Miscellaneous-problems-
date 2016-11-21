/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

return 6
*/


// Time O(rows*colmuns)
// Space O(Columns)

// if colmuns are very large than the rows, then we can swap loops and do same logic with rows
public int maximum(int input[][]){
	
	if (input==null || input.length==0 || input[0].length==0)
        return 0;
	
	int temp[] = new int[input[0].length];
	int maxArea = 0;
	int area = 0;
	for(int i=0; i < input.length; i++){
		for(int j=0; j < temp.length; j++){
			if(input[i][j] == 0){
				temp[j] = 0;
			}else{
				temp[j] += input[i][j];
			}
		}
		area = maxHistogram(temp);
		if(area > maxArea){
			maxArea = area;
		}
	}
	return maxArea;
}

// below is the same problem of finding largest rectangle in histogram
public int maxHistogram(int heights[]){
	Stack<Integer> stack = new Stack<Integer>();
	int maxArea = 0, area = 0, i;
	for(i=0; i < heights.length;){
		if(stack.isEmpty() || heights[stack.peek()] <= heights[i]){
			stack.push(i++);
		}else{
			int top = stack.pop();
			 if(stack.isEmpty()){
				area = heights[top] * i;
			}
			else{
				area = heights[top] * (i - stack.peek() - 1);
			}
			if(area > maxArea){
				maxArea = area;
			}
		}
	}
	while(!stack.isEmpty()){
		int top = stack.pop();
		if(stack.isEmpty()){
			area = heights[top] * i;
		} else{
			area = heights[top] * (i - stack.peek() - 1);
		}
		
		if(area > maxArea){
			maxArea = area;
		}
	}
	return maxArea;
}