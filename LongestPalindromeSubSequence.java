/*
Given a sequence, find the length of the longest palindromic subsequence in it. For example, if the given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.

*/

class LPS
{
 
    // A utility function to get max of two integers
    static int max (int x, int y) { return (x > y)? x : y; }
      
    // Returns the length of the longest palindromic substruence in str
    static int lps(String str)
    {
       int n = str.length();
       int i, j, index;
       int dp[][] = new int[n][n];  // Create a table to store results of subproblems
      
       // Strings of length 1 are palindrome of lentgh 1
       for (i = 0; i < n; i++)
           dp[i][i] = 1;
              
        // Build the table. Note that the lower diagonal values of table are
        // useless and not filled in the process. The values are filled in a
        // manner similar to Matrix Chain Multiplication DP solution (See
        // http://www.geeksforgeeks.org/archives/15553). index is length of
        // substring
        for (index=2; index<=n; index++)
        {
            for (i=0; i<n-index+1; i++)
            {
                j = i+index-1;
                if (str.charAt(i) == str.charAt(j) && index == 2)
                   dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j))
                   dp[i][j] = dp[i+1][j-1] + 2;
                else
                   dp[i][j] = max(dp[i][j-1], dp[i+1][j]);
            }
        }
              
        return dp[0][n-1];
    }
          
    /* Driver program to test above functions */
    public static void main(String args[])
    {
        String str = "GEEKSFORGEEKS";
        int n = str.length();
        System.out.println("The lnegth of the lps is "+ lps(str));
    }
}