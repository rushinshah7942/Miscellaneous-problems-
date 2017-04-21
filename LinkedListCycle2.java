/*
	Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution 
{
    public ListNode detectCycle(ListNode head) 
    {
        ListNode slow = head;
        ListNode fast = head;
                
        while(fast != null && fast.next !=null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
            {
				// cycle exists                
				ListNode slow = head; 
                while (slow != fast)
                {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    
        /*
        slow = head;        
        // start again 
        while(slow != fast)
        {
                slow = slow.next;
                fast = fast.next;
        }
        return slow;
        */
    }
}