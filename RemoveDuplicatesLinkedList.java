/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,

Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

public class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
	{
        if(head == null || head.next == null)
            return head;
 
        ListNode prev = head;    
        ListNode p = head.next;
 
        while(p != null)
		{
            if(p.val == prev.val)
			{
                prev.next = p.next;
                p = p.next;
                //no change in prev
            }
			else
			{
                prev = p;
                p = p.next; 
            }
        }
 
        return head;
    }
}

// no tracking of prev node

public class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
	{
        if(head == null || head.next == null)
            return head;
 
        ListNode p = head;
 
        while( p!= null && p.next != null)
		{
            if(p.val == p.next.val)
			{
                p.next = p.next.next;
            }
			else
			{
                p = p.next; 
            }
        } 
        return head;
    }
}


// recursive solution
public ListNode deleteDuplicates(ListNode head) 
{
        if(head == null || head.next == null)
			return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
}