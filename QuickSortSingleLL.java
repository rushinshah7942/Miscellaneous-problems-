/*
The important things about implementation are, it changes pointers rather swapping data and time complexity is same as the implementation for Doubly Linked List.

In partition(), we consider tail element as pivot. We traverse through the current list and if a node has value greater than pivot, we move it after tail. If the node has smaller value, we keep it at its current position.

In QuickSortRecur(), we first call partition() which places pivot at correct position and returns pivot. After pivot is placed at correct position, we find tail node of left side (list before pivot) and recur for left list. Finally, we recur for right list.

*/

class Node {

    private int data;
    private Node next;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public String toString() {
        return Integer.toString(data);
    }
}


public class LinkedLists {
    
    private static class Buffer {
        public Node newHead;
        public Node newEnd;
        public Node prev;
    } 
	// we need return Pivot, New Head, New End values from Partition 
	// as Java can not return more than one value, we create static class Buffer and use that reference
    
    public static Node toList(int... arr) {

        if (arr == null || arr.length == 0) {
            return null;
        }

        Node root = new Node(arr[0]);
        Node temp = root;

        for (int i = 1; i < arr.length; i++) {
            temp.setNext(new Node(arr[i]));
            temp = temp.getNext();
        }
        return root;
    }
    
    public static String print(Node root) {

        if (root == null) {
            return null;
        }
        return getFullPath(root);
    }
    
    private static String getFullPath(Node root) {
        String str = String.valueOf(root.getData());
        if (root.getNext() != null) {
            str += " -> " + getFullPath(root.getNext());
        }
        return str;
    }
    
    public static Node getTail(Node root) {
        if (root == null || root.getNext() == null)
            return root;
        return getTail(root.getNext());
    }
    
    private static Node partition(Buffer buffer, Node start, Node end) {

        Node curr = start;
        Node tail = end;
        Node pivot = tail, prev = null;

        while (curr != pivot) {
			
            if (curr.getData() <= pivot.getData()) {

				// if newhead has not been set
                if (buffer.newHead == null) {
                    buffer.newHead = curr;
                }

                prev = curr;
                curr = curr.getNext();
            } else {
				// move curr node to next of tail and update tail

				// remove curr node and set prev next to curr's next
                if (prev != null) {
                    prev.setNext(curr.getNext());
                }

                Node temp = curr.getNext();

                tail.setNext(curr);
                tail = curr; // update tail
                curr.setNext(null);

                curr = temp;
            }
        }
		// if pivot was the smallest in all the elements
        if (buffer.newHead == null) {
            buffer.newHead = pivot;
        }

		// update new end 
        buffer.newEnd = tail;
        buffer.prev = prev; // do this

        return pivot;
    }
    
    private static Node _quickSort(Node root, Node tail) {

		// base condition
        if (root == null || root.getNext() == null)
            return root;

        Buffer buffer = new Buffer();
        Node pivot = partition(buffer, root, tail);

		// if pivot is smallest - no need to recur for left part
        if (buffer.newHead != pivot) {

            Node prev = buffer.prev; 
            prev.setNext(null); // separate prev from pivot - to create left LL and recur on Left part
			
			// prev will be my new pivot for left recur part
            buffer.newHead = _quickSort(buffer.newHead, prev);

			// after sorting left part join it with pivot
            Node temp = getTail(buffer.newHead);
            temp.setNext(pivot);
        }
			
		// join pivot with sorted right part	
        pivot.setNext(_quickSort(pivot.getNext(), buffer.newEnd));

        return buffer.newHead;
    }
    
    public static Node quickSort(Node root) {
        return _quickSort(root, getTail(root));
    }
    
    public static void main(String[] args) {

        Node list = toList(4, 3, 9, 1, 6, 5);
        System.out.println("Before Sorting : " + print(list));

        list = quickSort(list);
        System.out.println("\nAfter Sorting : " + print(list));
    }
    
}

