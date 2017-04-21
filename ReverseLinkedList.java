/*
	Reverse a singly linked list.
*/

// Iterative solution
public ListNode reverseList(ListNode head) 
{
    if(head==null||head.next==null)
        return head;
 
    ListNode p1 = head;
    ListNode p2 = p1.next;
 
    head.next = null;
    while(p1!=null&& p2!=null)
	{
        ListNode temp = p2.next;
        p2.next = p1;
        p1 = p2;
        p2 = temp;
    }
 
    return p1;
}
// Recursive Solution

public ListNode reverseList(ListNode head) 
{
    if(head==null || head.next == null)
        return head;
 
    //get second node    
    ListNode second = head.next;
    //set first's next to be null
    head.next = null;
 
    ListNode rest = reverseList(second);
    second.next = head;
 
    return rest;
}