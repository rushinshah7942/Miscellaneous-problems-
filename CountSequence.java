/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class Solution 
{
    public String countAndSay(int n) 
    {
        if(n==1)
			return "1";
		
		if(n==2)
			return "11";

		StringBuilder sb = new StringBuilder();
		sb.append("11");
		
		for(int i=2;i<n;i++)
		{
			String temp = sb.toString();
			sb.delete(0, sb.length());
			
			int count = 1;
			for(int j = 0;j<temp.length();j++)
			{
			    // last number to count
				if(j == temp.length()-1)
				{
					sb.append(count);
					sb.append(temp.charAt(j));
					break;
				}
				// check next number and if not same
				if(temp.charAt(j)!=temp.charAt(j+1))
				{
					sb.append(count);
					sb.append(temp.charAt(j));
					count = 1;
				}
				else // same
				{
					count++;
				}
			}
		}
		return sb.toString();		 

    }
}