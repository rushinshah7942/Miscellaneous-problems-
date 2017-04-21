/*
Custom Implementation of HashMap in Java with methods get, put.

Key - Integer
Value - Integer

*/

import junit.framework.Assert;
import org.junit.Test;

public class HashMapImpl {

	class HashMap {
	
		int SIZE_OF_TABLE = 128; // always even 
		HashEntry[] table;
		
		HashMap() {
			table = new HashEntry[SIZE_OF_TABLE];
			for (int i = 0; i < SIZE_OF_TABLE; i++) {
				table[i] = null;
			}
		}

		public void put(int key, int value) {
			int index = hashCodeNew(key); // compute hash-code

			//System.out.println(index);
		
			HashEntry hashEntry = new HashEntry(key, value);
			
			if (table[index] == null) {
				table[index] = hashEntry; // first entry for that bucket
			} else {
				HashEntry runner = table[index]; // get the first entry in that bucket 
				
				// now search for that key 
				while (runner.next != null) {
					if (runner.key == hashEntry.key) {
						runner.value = hashEntry.value; // update the value
						break;
					} else {
						runner = runner.next;
					}
				}
				
				// last entry
				if (runner.next == null) {
					if (runner.key == hashEntry.key) {
						runner.value = hashEntry.value; // update the value
					} else {
						runner.next = hashEntry;
					}
				}
			}
			// modCount++;
			// for fail-fast behavior - ask first 
		}

		public int get(int key) {
			
			int index = hashCodeNew(key);
			
			if (table[index] == null) {
				return -1;
			} else {
				HashEntry runner = table[index];
				if (runner.key == key) {
					return runner.value;
				}
			
				while (runner.next != null) {
					if (runner.key == key) {
						return runner.value;
					}
				}
				return -1;
			}
		}

		public int hashCodeNew(int h) {
			h ^= (h >>> 20) ^ (h >>> 12);
			int hashCode = h ^ (h >>> 7) ^ (h >>> 4);
			//return hashCode % SIZE_OF_TABLE;
			return hashCode % (SIZE_OF_TABLE - 1); // the index formula can output values from 0 to SIZE_OF_TABLE -1  
			                                       // and the array of size is fully used
		}
	}

	// so we will call OurClass.HAshEntry
	static class HashEntry {
		int key;
		int value;
		HashEntry next = null; // builds chain - arraylist of Entry objects

		HashEntry() {
		}

		public HashEntry(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return this.key;
		}

		public int getValue() {
			return this.value;
		}
	}

	@Test
	public void testBasic() {
		HashMap hashMap = new HashMap();
		hashMap.put(10, 20);
		hashMap.put(20, 11);
		hashMap.put(21, 1);
		hashMap.put(20, 10);

		int value = hashMap.get(20);
		Assert.assertEquals(10, value);

	}
}


// transient volatile int modCount;
// The number of times this HashMap has been structurally modified Structural modifications are those that change the number of mappings in the HashMap or otherwise modify its internal structure (e.g., rehash). This field is used to make iterators on Collection-views of the HashMap fail-fast. (See ConcurrentModificationException).
 
private abstract class HashIterator<E> implements Iterator<E> {

	 Entry<K,V> next;        // next entry to return
	 int expectedModCount;   // For fast-fail behavior 
	 int index;              // current slot
	 Entry<K,V> current;     // current entry

	 HashIterator() {
		 expectedModCount = modCount;
		 if (size > 0) { // advance to first entry
			 Entry[] t = table;
			 while (index < t.length && (next = t[index++]) == null); // get the first non-empty slot, keep updating next
		 }
	 }

	 public final boolean hasNext() {
		 return next != null;
	 }

	 final Entry<K,V> nextEntry() {
		 if (modCount != expectedModCount)
			 throw new ConcurrentModificationException();

		 Entry<K,V> e = next; // get the next pointer
		 if (e == null)
			 throw new NoSuchElementException();

		 // before returning e 
		 // set next to non-empty bucket
		 if ((next = e.next) == null) {
			 Entry[] t = table;
			 while (index < t.length && (next = t[index++]) == null); // there could be empty buckets intermittently
			 // so everytime we move our next pointer ahead to get non-empty bucket
		 }
		 current = e; // set e to current
		 return e;
	 }

	 public void remove() {
		 if (current == null)
			 throw new IllegalStateException();
		 if (modCount != expectedModCount)
			 throw new ConcurrentModificationException();

		 Object k = current.key;
		 current = null;
		 HashMap.this.removeEntryForKey(k);
		 expectedModCount = modCount; // reset modCount
	 }

}


// Removes and returns the entry associated with the specified key in the HashMap. 
// Returns null if the HashMap contains no mapping for this key.
 
   final Entry<K,V> More ...removeEntryForKey(Object key) {
       int hash = (key == null) ? 0 : hash(key.hashCode());
       int i = indexFor(hash, table.length);
 
	   Entry<K,V> prev = table[i];
       Entry<K,V> e = prev;
 
       while (e != null) {
           Entry<K,V> next = e.next;
           Object k;
           if (e.hash == hash &&
               ((k = e.key) == key || (key != null && key.equals(k)))) {
               modCount++;
               size--;
               if (prev == e)
                   table[i] = next;
               else
                   prev.next = next;
               e.recordRemoval(this);
               return e;
           }
           prev = e;
           e = next;
       }
 
       return e;
   }