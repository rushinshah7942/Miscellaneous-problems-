/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

*/

// O(n^2) solution

public int strStr(String haystack, String needle) 
{
	if(haystack == null || needle == null)
		return 0;
		
	if(needle.length() < 1)
		return 0;
	
	if(needle.length() > haystack.length())
		return -1;
	
	for(int i=0;i<haystack.length();i++){
		int k = i;
		
		// we will increment 'k' and keep 'i' steady
		for(int j=0;j<needle.length();j++){
			//"mississippi"
			//"issippi"
			if(i + needle.length() > haystack.length())
				return -1;        
				
			if(needle.charAt(j) == haystack.charAt(k)){
				k++;
				if(j == needle.length()-1)
					return i;
			}
			else
				break;
		}
	}
	return -1;
}