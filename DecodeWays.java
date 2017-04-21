/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.


Solution:
=========
	Used a dp array of size n + 1 to save subproblem solutions. 
	dp[0] means an empty string will have one way to decode, dp[1] means the way to decode a string of size 1. 
	Then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.

*/

// Time : O(n)
// Space: O(n)

public int numDecodings(String s) {
	
	if(s == null || s.length() == 0) {
		return 0;
	}
	
	int n = s.length();
	int[] dp = new int[n+1];
	
	dp[0] = 1;
	dp[1] = s.charAt(0) != '0' ? 1 : 0;
	
	for(int i = 2; i <= n; i++) {

		int first = Integer.valueOf(s.substring(i-1, i));
		int second = Integer.valueOf(s.substring(i-2, i));
	
		if(first >= 1 && first <= 9) {
		   dp[i] += dp[i-1];  
		}
		if(second >= 10 && second <= 26) {
			dp[i] += dp[i-2]; // only if second is valid
		}
	}
	return dp[n];
}
/*
Let's say you want to decode "12". Before the loop, dp[] array becomes [1,1,0]. Then when i = 2, first is 2, second is 12, both can be decoded. So dp[2] = dp[1] + dp[0], which is 2. 
*/

// Space : O(1)
 int numDecodings(string s) {
    int n = s.size();
    if (n == 0 || s[0] == '0') 
		return 0;
    if (n == 1) 
		return 1;
    
	int pre2 = 1, pre1 = 1;
    int cur;
    for (int i = 1; i < n; ++i) {
        cur = 0;
        int first = (s[i] - '0');
        int second = stoi(s.substr(i - 1, 2));
        if (1 <= first && first <= 9)
            cur += pre1;
        if (10 <= second && second <= 26)
            cur += pre2;
    
		pre2 = pre1;
        pre1 = cur;
    }
    return cur;
}