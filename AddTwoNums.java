/*
	You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNum
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
	{
        
        ListNode temp = new ListNode(0);
        ListNode a1 =l1, a2=l2, result = temp;
        int carry = 0;
        
        // OR condition -> to process bigger LinkedList
        while(a1!=null || a2!=null)
        {
            
            if(a1!=null)
            {
                carry += a1.val;
                a1 = a1.next;
            }
            if(a2!=null)
            {
                carry += a2.val;
                a2 = a2.next;
            }
            result.next = new ListNode(carry%10);
            carry = carry/10;
            result = result.next;
        }
        
        if(carry == 1)
            result.next = new ListNode(1);
        
        // return the first pointer to the answer list 
        return temp.next;
    }
}