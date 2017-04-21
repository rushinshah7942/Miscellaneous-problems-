/*

	Given an input with duplicate characters generate a shuffle which does not have two duplicate characters together.

*/
/*

	Solution
	----------------------
	1. create hashmap of charactr as key and value as its count
	2. create two arrays -> one for all unique characters and one for its count - respective
	3. Do permutation of all the characters and return true if any valid one is found out

*/
public char[] shuffle(char input[]){
  
	char result[] = new char[input.length];

	//create a map of character to its frequency.
	Map<Character, Integer> map = new HashMap<Character,Integer>();
	for(int i=0; i < input.length; i++){
		Integer count  = map.putIfAbsent(input[i], 1);
		if(count != null) {
			count++;
			map.put(input[i], count);
		}
	}
	char newInput[] = new char[map.size()];
	int freq[] = new int[map.size()];

	//create a new char array and freq array from map.
	int index = 0;
	for(Entry<Character,Integer> entry :  map.entrySet()){
		newInput[index] = entry.getKey();
		freq[index] = entry.getValue();
		index++;
	}
	//assuming char with ASCII value 0 does not exists in the input
	// lastval is which doesn't exists in input string
	shuffleUtil(newInput,freq, result, 0, (char)0);
	return result;
}

//regular permutation code. If we do find a permutation which satisfies the
//constraint then return true and stop the process.
private boolean shuffleUtil(char input[], int freq[], char result[], int pos, char lastVal) {
	
	if(pos == result.length){
		return true;
	}
	// e.g. aabc
	// input: a b c 
	// freq : 2 1 1   
	for(int i=0; i < input.length; i++){

		if(lastVal == input[i]) {
		  continue; // this condition checks for duplicate characters not coming together
		}
		if(freq[i] == 0) {
		  continue;
		}

		freq[i]--;
		result[pos] = input[i];

		if(shuffleUtil(input, freq, result, pos+1, input[i])){
		  return true;
		}
		
		freq[i]++; // increment the count so that we can use it again
	}

	return false;
}