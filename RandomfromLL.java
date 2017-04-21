/*

Select a Random Node from a Singly Linked List
Given a singly linked list, select a random node from linked list (the probability of picking a node should be 1/N if there are N nodes in list). You are given a random number generator.

*/


/*

Below is a Simple Solution
1) Count number of nodes by traversing the list.
2) Traverse the list again and select every node with probability 1/N. The selection can be done by generating a random number from 0 to N-i for i’th node, and selecting the i’th node node only if generated number is equal to 0 (or any other fixed number from 0 to N-i).

We get uniform probabilities with above schemes.

i = 1, probability of selecting first node = 1/N
i = 2, probability of selecting second node =
                   [probability that first node is not selected] * 
                   [probability that second node is selected]
                  = ((N-1)/N)* 1/(N-1)
                  = 1/N  
Similarly, probabilities of other selecting other nodes is 1/N

The above solution requires two traversals of linked list.

*/

/*

PROBLEM:

Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
BASIC IDEA:

Choose 1, 2, 3, ..., k first and put them into the reservoir.
For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
Repeat until k+i reaches n
PROOF:

For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i)
When k+i reaches n, the probability of each number staying in the reservoir is k/n
EXAMPLE

Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
First, choose [111, 222, 333] as the initial reservior
Then choose 444 with a probability of 3/4
For 111, it stays with a probability of
P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
= 1/4 + 3/4*2/3
= 3/4
The same case with 222 and 333
Now all the numbers have the probability of 3/4 to be picked


*/


/*

How to select a random node with only one traversal allowed?
The idea is to use Reservoir Sampling. Following are the steps. This is a simpler version of Reservoir Sampling as we need to select only one key instead of k keys.

(1) Initialize result as first node
   result = head->key 
(2) Initialize n = 2
(3) Now one by one consider all nodes from 2nd node onward.
    (3.a) Generate a random number from 0 to n-1. 
         Let the generated random number is j.
    (3.b) If j is equal to 0 (we could choose other fixed number 
          between 0 to n-1), then replace result with current node.
    (3.c) n = n+1
    (3.d) current = current->next
	
*/

// A reservoir sampling based function to print a
// random node from a linked list
// Time : O(n)

void printrandom(Node node) {

	// If list is empty
	if (node == null) {
		return;
	}

	// Use a different seed value so that we don't get
	// same result each time we run this program
	Math.abs(UUID.randomUUID().getMostSignificantBits());

	// Initialize result as first node
	int result = node.data;

	// Iterate from the (k+1)th element to nth element
	Node current = node;
	int n;
	for (n = 2; current != null; n++) {

		// change result with probability 1/n
		if (Math.random() % n == 0) {
			result = current.data;
		}

		// Move to next node
		current = current.next;
	}

	System.out.println("Randomly selected key is " + result);
}
	
// Overall Design

import java.util.*;
public class Solution {

    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    ListNode head = null;
    Random randomGenerator = null;
   
	public Solution(ListNode head) {
        this.head = head;
        this.randomGenerator = new Random();

    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode result = null;
        ListNode current = head;
        
        for(int n = 1; current!=null; n++) {
            if (randomGenerator.nextInt(n) == 0) {
                result = current;
            }
            current = current.next;
        }
        
        return result.val;
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
	
	
	
/*

https://en.wikipedia.org/wiki/Reservoir_sampling

Relation to Fisher-Yates shuffle[edit]
Suppose one wanted to draw k random cards from a deck of playing cards (i.e., n=52). A natural approach would be to shuffle the deck and then take the top k cards. In the general case, the shuffle also needs to work even if the number of cards in the deck is not known in advance, a condition which is satisfied by the inside-out version of the Fisher-Yates shuffle:
  To initialize an array a of n elements to a randomly shuffled copy of S, both 0-based: 
   a[0] ← S[0] 
   for i from 1 to n - 1 do 
       r ← random (0 .. i) 
       a[i] ← a[r] 
       a[r] ← S[i]
Note that although the rest of the cards are shuffled, only the top k are important in the present context. Therefore, the array a need only track the cards in the top k positions while performing the shuffle, reducing the amount of memory needed. Truncating a to length k, the algorithm is modified accordingly:
  To initialize an array a to k random elements of S (which is of length n), both 0-based: 
   a[0] ← S[0] 
   for i from 1 to k - 1 do 
       r ← random (0 .. i) 
       a[i] ← a[r] 
       a[r] ← S[i]  
   for i from k to n - 1 do 
       r ← random (0 .. i) 
       if (r < k) then a[r] ← S[i]
Since the order of the first k cards is immaterial, the first loop can be removed and a can be initialized to be the first k items of S. This yields Algorithm R.

*/	