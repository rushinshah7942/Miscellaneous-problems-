/*
Find the length of a maximum palindrome subset in an array. For example: in 1, 2, 4, 1 the maximum palindrome subset is 1, 2, 1 and the answer is 3
*/

// time O(n)
// space O(n)

int maxPalindrome(int[] nums) {
    int maxLength = 0;
    int oddPresent = false; // Keeps track whether there is an element which is not in pair

    HashMap<Integer, Integer> map = new HashMap<>();
    
    for(int num: nums) {
        if (map.containsKey(num)) {
            int value = map.get(num);
            map.put(num, value + 1);
        } else {
            map.put(num, 1);
        }
    }
    
    for (int value: map.values()) {
        
        if (value % 2) {
            maxLength += 2;
        } else {
            oddPresent = true;
        }
    }
    
    if (oddPresent) {
        maxLength++;
    }
    
    return maxLength;
}