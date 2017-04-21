/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/

// Excellent approach

public ListNode rotateRight(ListNode head, int k) {
 
 
    // 1 2 3 4 5
	//
    if(head == null || k == 0) {
        return head;
    }
    ListNode p = head;
    int len = 1;
    while(p.next != null) {
        p = p.next;
        len++;
    }
    p.next = head;
	// join 5 to head to make circular list
	// p is at 5
 
    k %= len;
	// k = k % 5
	// go upto 5 - 2 from given point
   
    for(int i = 0; i < len - k; i++) {
        p = p.next;
    }
    head = p.next;  // make next head  
    p.next = null; // make next null 
    return head;
}

// Naive approach
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null || head.next == null)
            return head;
            
        if(k==0)
            return head;
        
        ListNode node = head;
        //StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        while(node!=null)
        {
            list.add(node.val);
            node = node.next;
        }
        // 12345
        
        if(k == list.size())
            return head;
        
        k = k % list.size();
        
        int[] str = new int[list.size()];
        for (int i=0; i < str.length; i++)
        {
            str[i] = list.get(i).intValue();
        }
            
        reverse(str,0,str.length-1);
        reverse(str,0,k-1);
        reverse(str,k,str.length-1);
        
        
        for(int i=0;i<str.length;i++)
        {
            System.out.print(str[i] + " ");
        }
        
        ListNode result = new ListNode(str[0]);
        ListNode helper = result;
        
        for(int i=1;i<str.length;i++)
        {
            ListNode tt = new ListNode(str[i]);
            helper.next = tt;
            helper = helper.next;
        }
        
        return result;
    }
    public void reverse(int[] str, int start, int end)
    {
        while(start < end)
        {
            int tmp = str[start];    
            str[start] = str[end];
            str[end] = tmp;
            start++;
            end--;
        }
    }
}



