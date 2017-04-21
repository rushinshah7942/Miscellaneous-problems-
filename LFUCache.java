/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?
*/

/*
Two HashMaps are used, 
- one to store <key, value> pair, 
- another store the <key, node>.

I use double linked list to keep the frequent of each key. In each double linked list node, keys with the same count are saved using java built in LinkedHashSet. This can keep the order.

Every time, one key is referenced, first find the current node corresponding to the key, If the following node exist and the frequent is larger by one, add key to the keys of the following node, else create a new node and add it following the current node.

All operations are guaranteed to be O(1).

*/

/*
	- I will keep count to mark how many times that items has been used
	- I will store items with same count together in Doubley LinkedList
	- Whenever, keys size in that count node becomes zero, I will delete that node
	- DLL will be always sorted based on Count (how many times particular node was used)
	
*/

public class LFUCache {
	
    private Node head = null;
    private int cap = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, Node> nodeHash = null;
     
    class Node {
   
        public int count = 0;
        public LinkedHashSet<Integer> keys = null;
        public Node prev = null, next = null;
        
        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
	// Node looks like this 
	/*----------------------
	| count = 2            |
	| Key : | 21 | 12 | 23 |
	-----------------------*/
    public LFUCache(int capacity) {
        this.cap = capacity;
        valueHash = new HashMap<Integer, Integer>();
        nodeHash = new HashMap<Integer, Node>();
    }
    
	// key referenced -> hence increase its count in DLL
    public int get(int key) { // increase count while getting operation
        if (valueHash.containsKey(key)) {
            increaseCount(key); // below 
            return valueHash.get(key);
        }
        return -1;
    }
    
	/*
		Idea is brilliant... 
		
		Especially storing all keys with same counts in one node, if one of the keys in that node got hit once more, it will be moved into the next node with (count+1) if the node exits or it will be wrapped into a newly created node with (count+1).
		
		All your operations seem to be amortized O(1).

	*/
    public void set(int key, int value) {
        if ( cap == 0 ) 
            return;
		
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value); // replace the value for key
        } 
		else {
            if (valueHash.size() < cap) {
                valueHash.put(key, value);
            } else {
                removeOld(); // if it has reached full capacity
                valueHash.put(key, value);
            }
            addToHead(key); // least frequently used 
        }
		
        increaseCount(key);
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            Node node = new Node(0); // create node with 0 count
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node; // at last make new node as head
        } else {
			// with 0 count 
            head.keys.add(key);
        }
		
        nodeHash.put(key, head);      
    }
    
    private void increaseCount(int key) {
        Node node = nodeHash.get(key); // current
        // node.next will be new node
        node.keys.remove(key);
        
        if (node.next == null) { // create new node for new count
            node.next = new Node(node.count+1);
            node.next.prev = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count+1) { // if there is node for next count, then add to its keys
            node.next.keys.add(key);
        } else { // create node of that count, in-between prev and next nodes
            Node tmp = new Node(node.count+1);
            tmp.keys.add(key);
            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }

        nodeHash.put(key, node.next); // add mapping for that node into nodeHash

        if (node.keys.size() == 0)  // current node's key size is 0, then remove that node, and remove mapping
            remove(node);
    }
    
    private void removeOld() {
        if (head == null) 
            return;
        
		int old = 0;
        for (int n: head.keys) {
            old = n;
            break;
        }
        
        head.keys.remove(old);
        if (head.keys.size() == 0) 
            remove(head);
        
        nodeHash.remove(old);
        valueHash.remove(old);
    }
    
    private void remove(Node node) {
        if (node.prev == null) {
            head = node.next; // if head to be removed
        } else {
            node.prev.next = node.next; // connect prev and next nodes
        } 
        if (node.next != null) {
            node.next.prev = node.prev; // if next is not null, set its prev
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */