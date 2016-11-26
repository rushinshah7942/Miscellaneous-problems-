/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?
*/



public class LFUCache {
    private Node head = null;
    private int cap = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, Node> nodeHash = null;
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        valueHash = new HashMap<Integer, Integer>();
        nodeHash = new HashMap<Integer, Node>();
    }
    
    public int get(int key) { // increase count while getting operation
        if (valueHash.containsKey(key)) {
            increaseCount(key);
            return valueHash.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if ( cap == 0 ) 
            return;
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value); // replace the key
            Node node = nodeHash.get(key); // get node for that key
            node.keys.remove(key); // linkedhashset, hence remove and then add key
            node.keys.add(key);
        } else {
            if (valueHash.size() < cap) {
                valueHash.put(key, value);
            } else {
                removeOld(); // if it has reached full capacity
                valueHash.put(key, value);
            }
            addToHead(key); // least recent used
        }
        increaseCount(key);
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            Node node = new Node(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node; // at last make new node as head
        } else {
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
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */