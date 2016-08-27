/*
	Given a string S, find the longest palindromic substring in S. 
	You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
	
	Logic: Dynamic Programming

	Let s be the input string, i and j are two indices of the string. 
	Define a 2-dimension array "table" and let table[i][j] denote whether a substring from i to j is palindrome.

	Changing condition:

		table[i+1][j-1] == 1 && s.charAt(i) == s.charAt(j)
		=>
		table[i][j] == 1
*/

