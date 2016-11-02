/*
Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words.
*/

/*
Consider the following dictionary 
{ i, like, sam, sung, samsung, mobile, ice, 
  cream, icecream, man, go, mango}

Input:  ilike
Output: Yes 
The string can be segmented as "i like".

Input:  ilikesamsung
Output: Yes
The string can be segmented as "i like samsung" or "i like sam sung".
*/

String[] dict = {"mobile","samsung","sam","sung","man","mango",
				"icecream","and","go","i","like","ice","cream"};
// copy string dict into HashSet 

boolean dictContains(String word)
{
	// comare against HashSet to achieve O(1) time complexity
}

boolean wordBreak(String str)
{
	if(str.length() == 0)
		return true;

	int n = str.length();
	for(int i=1;i<n;i++)
	{
		if(dictContains(str.substring(0,i)) &&
			wordBreak(str.substring(i))) // rest of the string
			return true;
	}
	return false;
}