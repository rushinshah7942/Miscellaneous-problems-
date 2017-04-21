/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarifications from Interviewer
-------------------------------------
What constitutes a word?
- A sequence of non-space characters constitutes a word.

Could the input string contain leading or trailing spaces?
- Yes. However, your reversed string should not contain leading or trailing spaces.

How about multiple spaces between two words?
- Reduce them to a single space in the reversed string.

*/

public class Solution {
    public String reverseWords(String s) {
        /*
            \\s - matches single whitespace character
            \\s+ - matches sequence of one or more whitespace characters.
        */
        String[] parts = s.trim().split("\\s+");
    
        // using StringBuilder will be optimum in case of larger string text 
        // as String object is immutable, for adding each word to String will create many temp objects
        // and later garbage collected  
        //String out = "";
        StringBuilder sb = new StringBuilder();
        
        for (int i = parts.length - 1; i > 0; i--) {
            sb.append(parts[i]).append(" ");
        }
        sb.append(parts[0]);

        return sb.toString();
    }
}
// Another Solution - 
// First, reverse the whole string, then reverse each word.

// Clean Java two-pointers solution (no trim( ), no split( ), no StringBuilder)
public class Solution {
  
  public String reverseWords(String s) {
    if (s == null) return null;
    
    char[] a = s.toCharArray();
    int n = a.length;
    
	// TIME : O(n)
	
    // step 1. reverse the whole string
    reverse(a, 0, n - 1); 
    // step 2. reverse each word
    reverseWords(a, n);
    // step 3. clean up spaces
    return cleanSpaces(a, n);
  }
  
  void reverseWords(char[] a, int n) {
    int i = 0, j = 0;
      
    while (i < n) {
      while (i < j || (i < n && a[i] == ' ') ) i++; // skip spaces
      while (j < i || (j < n && a[j] != ' ') ) j++; // skip non spaces
      reverse(a, i, j - 1);                      // reverse the word
    }
  }
  
  // trim leading, trailing and multiple spaces
  String cleanSpaces(char[] a, int n) {
    int i = 0, j = 0;
      
    while (j < n) {
      while (j < n && a[j] == ' ') j++;             // skip spaces
      while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
      while (j < n && a[j] == ' ') j++;             // skip spaces
      if (j < n) a[i++] = ' ';                      // keep only one space
    }
  
    return new String(a).substring(0, i);
  }
  
  // reverse a[] from a[i] to a[j]
  private void reverse(char[] a, int i, int j) {
    while (i < j) {
      char t = a[i];
      a[i++] = a[j];
      a[j--] = t;
    }
  }
  
}


