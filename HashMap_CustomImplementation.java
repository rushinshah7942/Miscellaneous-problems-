/*
Custom Implementation of HashMap in Java with methods get, put.
*/


import junit.framework.Assert;
import org.junit.Test;

public class HashMapImpl {

	class HashMap {
	
		int SIZE_OF_TABLE = 128;
		HashEntry[] table;
		
		HashMap() {
			table = new HashEntry[SIZE_OF_TABLE];
			for (int i = 0; i < SIZE_OF_TABLE; i++) {
				table[i] = null;
			}
		}

		public void put(int key, int value) {
			int index = hashCodeNew(key);
			System.out.println(index);
		
			HashEntry hashEntry = new HashEntry(key, value);
			
			if (table[index] == null) {
				table[index] = hashEntry;
			} else {
				HashEntry runner = table[index];
				
				while (runner.next != null) {
					if (runner.key == hashEntry.key) {
						runner.value = hashEntry.value;
						break;
					} else {
						runner = runner.next;
					}
				}
				
				// last entry
				if (runner.next == null) {
					if (runner.key == hashEntry.key) {
						runner.value = hashEntry.value;
					} else {
						runner.next = hashEntry;
					}
				}
			}

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
			return hashCode % SIZE_OF_TABLE;
		}
	}

	
	class HashEntry {
		int key;
		int value;
		HashEntry next = null;

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
