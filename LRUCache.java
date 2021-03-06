/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.

put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

*/

//------------------------------------

public class LRUCache {

    public LRUCache(int capacity) {
        
    }
    
    public int get(int key) {
        
    }
    
    public void put(int key, int value) {
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
//------------------------------------



// Doubly Linked List
class Node{
    int key;
    int value;
    Node pre;
    Node next;
 
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    int capacity;
    
	Node head=null;
    Node end=null;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;    
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n); // remove 
            setHead(n); // set head as it is recently used key
            return n.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value; // change old node value
            remove(old); 
            setHead(old);
        }else{
            
            Node created = new Node(key, value);

            if(map.size() >= capacity){ // check the capacity
                map.remove(end.key); // end node's key
                remove(end); // remove end
                setHead(created); // set new node as head
 
            }else{
                setHead(created); // just set new head
            }    
 
            map.put(key, created); // map stores key and node
			// that node will store value (and its key as well)
        }
    }
    public void remove(Node n){
        // check previous
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            head = n.next; // change head as next node
        }
 
        // check next
        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            end = n.pre; // change end as previous node
        }
 
    }
    public void setHead(Node n){
        n.next = head;
        n.pre = null;
 
        if(head!=null)
            head.pre = n;
 
        head = n;
 
        if(end ==null)
            end = head;
    }
 
}