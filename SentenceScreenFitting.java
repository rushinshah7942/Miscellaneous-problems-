/*
Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.
Note:
A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:
Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:
Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:
Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

*/

// Time Complexity is O(n * rows), where n = number of sentences
public static int wordsTyping(String[] sentence, int rows, int cols) {
        
	int sum = 0;
	for (String s: sentence) {
		if (s.length() > cols) {
			return 0;
		}
		sum += s.length()+1;
	}
	int length = sentence.length;
	int index = 0, count = 0;
	
	for (int i = 0; i < rows; i++) {
		int remaining = cols + 1;
		count += remaining / sum;
		remaining %= sum;
		//System.out.println("remaining:" + remaining);
		while (remaining >= sentence[index].length() + 1) {
			remaining -= sentence[index++].length() + 1;
			//System.out.println("remaining:" + remaining);
			if (index == length) {
				count++;
				index = 0;
			}
		}
	}
	System.out.println("ans:"+ count);
	return count;
}

// Another Solution
public int wordsTyping(String[] sentence, int rows, int cols) {
	String s = String.join(" ", sentence) + " ";
	int start = 0, l = s.length();
	for (int i = 0; i < rows; i++) {
		start += cols;
		if (s.charAt(start % l) == ' ') {
			start++;
		} else {
			while (start > 0 && s.charAt((start-1) % l) != ' ') {
				start--;
			}
		}
	}
	
	return start / s.length();
}
/*

Reference: https://discuss.leetcode.com/topic/62455/21ms-18-lines-java-solution

Explanation:

"abc de f abc de f abc de f ..." // start=0
 012345                          // start=start+cols+adjustment=0+6+1=7 (1 space removed in screen string)
        012345                   // start=7+6+0=13
              012345             // start=13+6-1=18 (1 space added)
                   012345        // start=18+6+1=25 (1 space added)
                          012345
						  
*/						 