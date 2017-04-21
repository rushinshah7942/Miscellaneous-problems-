/*
	Given a singly linked list, determine if it is a palindrome.
	
	Logic: Create a new list in reversed order and then compare each node. The time and space are O(n).
*/

public boolean isPalindrome(ListNode head) 
{
    if(head == null)
        return true;
 
    ListNode p = head;
    ListNode prev = new ListNode(head.val);
 
    while(p.next != null)
	{
        ListNode temp = new ListNode(p.next.val);
        temp.next = prev;
        prev = temp;
        p = p.next;
    }
 
    ListNode p1 = head;
    ListNode p2 = prev;
 
    while(p1!=null)
	{
        if(p1.val != p2.val)
            return false;
 
        p1 = p1.next;
        p2 = p2.next;
    }
 
    return true;
}