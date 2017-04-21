/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); 
add(3); 
add(5);
find(4) -> true
find(7) -> false


public class TwoSum {

    // Add the number to an internal data structure.
	public void add(int number) {

	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		
	}
}


*/

// Ask interviewer which method do they want faster???
// My opinion is Add should be faster, as it will be called more times than find()

// Add is faster - o(1)
// Find is slower - o(N)

public class TwoSum {
    Map<Integer,Integer> map;
    
    TwoSum(){
        map = new Hashmap<Integer,Integer>();
    }

    // Add the number to an internal data structure.
	// Or keep HashMap<Integer, boolean> -> if number occurs more than once keep it true
	public void add(int number) {
	    if(map.containsKey(number)){
	        map.put(number, map.get(number) + 1);
	    }else{
	        map.put(number,1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {

		Iterator<Integer> iter = map.keySet().iterator();

	    while(iter.hasNext()){
	        int num1 = iter.next();
	        int num2 = value - num1;
	        if(map.containsKey(num2)){
	            if(num1 != num2 || map.get(num2) > 1){
	                return true;
	            }
	        }
	    }
	    return false;
	}
}

// Add is slower O(N)
// Find is faster o(1)

public class TwoSum {
	Set<Integer> sum;
	Set<Integer> num;
	
	TwoSum(){
		sum = new HashSet<Integer>();
		num = new HashSet<Integer>();
	}
	// Add the number to an internal data structure.
	public void add(int number) {
		
		// if there is duplicate, we would store it's summation i.e. double the value
		if(num.contains(number)){
			sum.add(number * 2);
		}else{ // for sum hashset, update it with all values in num to addition of newly added value
			Iterator<Integer> iter = num.iterator();
			
			while(iter.hasNext()){
				sum.add(iter.next() + number);
			}
			
			num.add(number);
		}
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		return sum.contains(value);
	}
}
// e.g. add(1),add(1),add(3),add(5)
// sum : 2 4 6 8
// num : 1 3 5