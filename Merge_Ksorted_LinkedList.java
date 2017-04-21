/*
	Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/*

	The simplest solution is using PriorityQueue. The elements of the priority queue are ordered according to their natural ordering, or by a comparator provided at the construction time (in this case).

*/

// Time: n*k*log(k).

public ListNode mergeKLists(ListNode[] lists) {
    if(lists==null||lists.length==0)
        return null;
	
	// if we did not use comparator, then 
	// java.lang.ClassCastException: ListNode cannot be cast to java.lang.Comparable will be thrown
	// e.g PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>();
	
    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
        public int compare(ListNode l1, ListNode l2){
            return l1.val - l2.val; // we need to write comparator to determine how we will compare two ListNodes
        }
    });
 
    ListNode head = new ListNode(0);
    ListNode p = head;
 
    for(ListNode list: lists){
        if(list!=null)
            queue.offer(list); // adds to end of queue
    }    
	// maintaining size of k 
	// as we only add first elements from each list 
	
    while(!queue.isEmpty()){ // this loop will iterate for k * (no. of elements in each list)
		
        ListNode n = queue.poll(); // head of the queue ie. minimum head of all lists
        p.next = n;
        p=p.next;
 
        if(n.next!=null)
            queue.offer(n.next); // adds remaining nodes of list , hence still log(k) operation here
    }   
	// Time-complexity  = O(k*n*(logk))
	// as we are inserting N*K elements from K list, and insert time-complexity in priority queue is O(log k) 
	// we maintain K elements into PriorityQueue
		
    return head.next;
 
}

// OPTIMZATION
// from N* K * O(log K) to N * log(K)
// as we are inserting N*K elements from K list, and insert time-complexity in priority queue is O(log k) 
// we maintain K elements into PriorityQueue

// K is the number of lists and N is total number of nodes.


public static ListNode mergeKLists(ListNode[] lists){
    return partion(lists,0,lists.length-1);
}
public static ListNode partion(ListNode[] lists,int s,int e){
    if(s == e)  
		return lists[s];
    
	if(s < e){ // keep partioning like merge-sort, until only one element to be processed
        int q = (s+e)/2;
        ListNode l1=partion(lists,s,q);
        ListNode l2=partion(lists,q+1,e);
        return merge(l1,l2);
    }
	else
        return null;
}
//This function is from Merge Two Sorted Lists - Bingo!
// Recursive and quite concise
public static ListNode merge(ListNode l1,ListNode l2){
    if(l1==null) 
		return l2;
    
	if(l2==null) 
		return l1;
	
    if(l1.val < l2.val){
        l1.next = merge(l1.next,l2);
        return l1;
    }else{
        l2.next = merge(l1,l2.next);
        return l2;
    }
}