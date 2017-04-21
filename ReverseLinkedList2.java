/*
	Reverse a linked list from position m to n. Do it in-place and in one-pass.

	For example:
	Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	return 1->4->3->2->5->NULL.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseLinkedList2 
{
    public ListNode reverseBetween(ListNode head, int m, int n) 
    {
        if(head == null || head.next == null)
            return head;
            
        ListNode helper = new ListNode(0);
        helper.next = head;
        
        ListNode p = head;
        ListNode pre = helper;
        
        for(int i=0;i<m-1;i++)
        {
            pre = pre.next;
            p = p.next;
        }
        ListNode p1 = p;
        ListNode p2= p.next;
        
        for(int i=m;i<n;i++)
        {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        p.next = p2;
        pre.next = p1;
        
        return helper.next;
    }
}