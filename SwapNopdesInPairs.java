/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution 
{
    public ListNode swapPairs(ListNode head) 
    {
        if(head == null || head.next == null)   
            return head;
 
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode p = h;
 
        //System.out.println("points to: " + p.val);
        while(p.next != null && p.next.next != null)
        {
            //use t1 to track first node
            ListNode t1 = p; // 1 in second pass
            
            p = p.next;
            t1.next = p.next; // 1 -> 4 in second pass
 
            //use t2 to track next node of the pair
            ListNode t2 = p.next.next; // 3
            p.next.next = p; // in here 2 points to 1
            p.next = t2; // 1 ->3
            
            // 0->2->1->3->4 at first pass
        }
 
        return h.next;
    }
}