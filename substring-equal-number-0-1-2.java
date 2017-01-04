/*
Given a string which consists of only 0s, 1s or 2s, count the number of substrings that have equal number of 0s, 1s and 2s.

Examples:

Input  :  str = “0102010”
Output :  2
Explanation : Substring str[2, 4] = “102” and 
              substring str[4, 6] = “201” has 
              equal number of 0, 1 and 2

Input : str = "102100211"
Output : 5



A simple solution is to iterate through all substring of str and checking whether they contain equal 0,1 and 2 or not. Total number of substring of str is O(n2) checking each substring for count takes O(n) times, So total time taken to solve this problem is O(n3) time with brute-force approach.


An efficient solution is to keep track of counts of 0, 1 and 2.

Let zc[i] denotes number of zeros between index 1 and i
    oc[i] denotes number of ones between index 1 and i
    tc[i] denotes number of twos between index 1 and i
for substring str[i, j] to be counted in result we should have :
	zc[i] – zc[j-1] = oc[i] – oc[j-1] = tc[i] - tc[j-1]
we can write above relation as follows :
z[i] – o[i] = z[j-1] – o[j-1]	and
z[i] – t[i] = z[j-1] – t[j-1]


Reference: http://www.geeksforgeeks.org/substring-equal-number-0-1-2/
*/


import java.util.HashMap;

class Pair{
	int x;
	int y;
	Pair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Pair other = (Pair) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		
		return true;
	}
}
public class Solution{
	public static void main(String[] args){
		String str = "102100211";
		System.out.println(subStrings(str));
	}
	
	public static int subStrings(String str)
	{
		HashMap<Pair,Integer> map = new HashMap<Pair,Integer>();
		
		Pair temp = new Pair(0,0);
		map.put(temp,1);
		
		int zeros=0,ones=0,twos=0;
		int result = 0;
		for(int i = 0;i<str.length();++i)
		{
			if(str.charAt(i)== '0')
				zeros++;
			else if(str.charAt(i)=='1')
				ones++;
			else
				twos++;
			
			int first = zeros-ones;
			int second = zeros-twos;
				
			Pair obj = new Pair(first,second);
			
			System.out.println(first+"," + second);
			if(map.containsKey(obj))
			{
				System.out.println("map val:" + map.get(obj));
				result += map.get(obj);
				map.put(obj,map.get(obj)+1);
			}
			else
			{
				map.put(obj,1);
			}
		}
		return result;
	} 
}