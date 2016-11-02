/*
Given two queues with their standard operations (enqueue, dequeue, isempty, size), implement a stack with its standard operations (pop, push, isempty, size).

There should be two versions of the solution.

Version A: By making push operation costly
Version B: By making pop operation costly
*/

/*
Version A:
---------------
when you push an element, choose one empty queue(whichever when both are empty) to add this element, and then push all elements of the other queue into the chosen queue. After that, the newest element is at the head of the chosen queue so that whenever you want to do pop() or top(), you always get the newest element.
-----------------------------------------
-----------------------------------------
Version B:
-------------
When you push elements, choose a queue which is not empty(whichever when both are empty).
When you do pop() or top(), first pop all elements of the queue except the tail into another empty queue, and then pop the tail which is your want.
*/

// VERSION A
// PUSH is costly

class MyStack {
	//using two queue. The push is inefficient.
	private Queue<Integer> q1 = new LinkedList<Integer>();
	private Queue<Integer> q2 = new LinkedList<Integer>();
	
	public void push(int x) {
		if(q1.isEmpty()) {
			q1.add(x);
			for(int i = 0; i < q2.size(); i ++)
				q1.add(q2.poll());
		}else {
			q2.add(x);
			for(int i = 0; i < q1.size(); i++)
				q2.add(q1.poll());
		}
	}

	public void pop() {
		if(!q1.isEmpty()) 
			q1.poll();
		else
			q2.poll();
	}
	public int top() {
		return q1.isEmpty() ? q2.peek() : q1.peek();
	}
	public boolean empty() {
		return q1.isEmpty() && q2.isEmpty();
	}
}

// VERSION B
// POP is costly

class MyStack 
{
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        if(!q1.isEmpty())
            q1.offer(x);
        else
            q2.offer(x);
                
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(q1.isEmpty())
        {
            int size = q2.size();
            // dequeue all elements into q1 except last element is which head i.e. last added elemented into q2
            for(int i=0;i<size-1;i++)
                q1.offer(q2.poll());
            q2.poll();
        }
        else
        {
            int size = q1.size();
            // dequeue all elements into q1 except last element is which head i.e. last added elemented into q2
            for(int i=0;i<size-1;i++)
                q2.offer(q1.poll());
            q1.poll();
        }
    }   

    // Get the top element.
    public int top() {
        int result;
        if(q1.isEmpty())
        {
            int size = q2.size();
            // dequeue all elements into q1 except last element is which head i.e. last added elemented into q2
            for(int i=0;i<size-1;i++)
                q1.offer(q2.poll());
            result = q2.poll();
            q1.offer(result);
        }
        else
        {
            int size = q1.size();
            // dequeue all elements into q1 except last element is which head i.e. last added elemented into q2
            for(int i=0;i<size-1;i++)
                q2.offer(q1.poll());
            result = q1.poll();
            q2.offer(result);
        }
        return result;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}