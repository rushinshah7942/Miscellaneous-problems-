/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
*/

/* 
* Maintain a stack
* If stack is empty or value at index of stack is less than or equal to value at current 
* index, push this into stack.
* Otherwise keep removing values from stack till value at index at top of stack is 
* less than value at current index.
* While removing value from stack calculate area
* if stack is empty 
* it means that till this point value just removed has to be smallest element
* so area = heights[top] * i
* if stack is not empty then this value at index top is less than or equal to 
* everything from stack top + 1 till i. So area will
* area = heights[top] * (i - stack.peek() - 1);
 * Finally maxArea is area if area is greater than maxArea.
*/
public class Solution {
	public int maxHistogram(int heights[]){
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for(i=0; i < heights.length;){
            if(stack.isEmpty() || heights[stack.peek()] <= heights[i]){
                stack.push(i++);
            }else{
                int top = stack.pop();
                //if stack is empty means everything till i has to be greater or equal to heights[top] so get area by
                //heights[top] * i;
                if(stack.isEmpty()){
                    area = heights[top] * i;
                }
                //if stack is not empty then everythin from i-1 to heights.peek() + 1 has to be greater or equal to heights[top]
                //so area = heights[top]*(i - stack.peek() - 1);
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
            //if stack is empty means everything till i has to be greater or equal to heights[top] so get area by
            //heights[top] * i;
            if(stack.isEmpty()){
                area = heights[top] * i;
            }
            //if stack is not empty then everything from i-1 to heights.peek() + 1 has to be greater or equal to heights[top]
            //so area = heights[top]*(i - stack.peek() - 1);
            else{
                area = heights[top] * (i - stack.peek() - 1);
            }
			
			if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }	
}