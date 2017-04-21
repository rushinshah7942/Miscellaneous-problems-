/*
	Given a linked list, determine if there is cycle or not. If cycle exists, break it.
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
    public void removeCycle(ListNode head) 
    {
		ListNode slow = head, fast = head;
		
		while(fast != null && fast.next.next !=null){
			if(slow == fast){
				// cycle exists
				// now remove cycle
				break;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(slow == fast){
			slow = head; // start slow with head again
			while (slow != fast.next) {  // imp step, not while(slow != fast)
                slow = slow.next;
                fast = fast.next;
            }
			fast.next = null; // this is where cycle starts 
		}
	}
}