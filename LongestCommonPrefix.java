/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

// Brute-Force
// Go one by one in String array and find the mathcing prefix and then again compare with next element in String array
// O(n^2)

// Sort
// Sort the array first, and then you can simply compare the first and last elements in the sorted array.
// O(n*logn) 

public class Solution 
{
	public String longestCommonPrefix(String[] strs) 
	{
        StringBuilder result = new StringBuilder();
        
        if (strs!= null && strs.length > 0)
		{
        
            Arrays.sort(strs);
            
            char [] a = strs[0].toCharArray();
            char [] b = strs[strs.length-1].toCharArray();
            
            for (int i = 0; i < a.length; i ++)
			{
                if (b.length > i && b[i] == a[i])
				{
                    result.append(b[i]);
                }
                else 
				{
                    return result.toString();
                }
            }
		}
        return result.toString();
    }
}