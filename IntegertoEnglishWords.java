/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Hint:
=====
Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

*/

public class Solution {
 private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 														"Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", 															"Ninety"};
    
    public String numberToWords(int num) {
        if (num == 0) 
            return "Zero";
        return helper(num); 
    }
    
    private String helper(int num) {
        String result = new String();
        if (num < 10) 
			result = belowTen[num];
        else if (num < 20) 
			result = belowTwenty[num -10];
        else if (num < 100) 
			result = belowHundred[num/10] + " " + helper(num % 10);
        else if (num < 1000) 
			result = helper(num/100) + " Hundred " +  helper(num % 100);
        else if (num < 1000000) 
			result = helper(num/1000) + " Thousand " +  helper(num % 1000);
        else if (num < 1000000000) 
			result = helper(num/1000000) + " Million " +  helper(num % 1000000);
        else 
			result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        
		return result.trim();
    }
}