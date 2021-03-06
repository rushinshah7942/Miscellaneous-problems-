/*
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
*/

public class PhoneDirectory {
    int max;
    HashSet<Integer> set;
    LinkedList<Integer> queue;
 
    /** Initialize your data structure here**/
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<Integer>();
        queue = new LinkedList<Integer>();
        for(int i=0; i<maxNumbers; i++){
            queue.offer(i);
        }
        max=maxNumbers-1;
    }
 
    /** Provide a number which is not assigned to anyone.**/
    public int get() {
        if(queue.isEmpty())
            return -1;
 
        int e = queue.poll();
        set.add(e);
        return e;
    }
 
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.contains(number) && number<=max;
    }
 
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)){
            set.remove(number);
            queue.offer(number);
        }
    }
}