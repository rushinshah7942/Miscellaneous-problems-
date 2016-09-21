/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
	....
	BA to BZ 
	CA to CZ then.....
*/
public String convertToTitle(int n) 
{
    StringBuilder sb = new StringBuilder();        
    while(n>0)
    {
        n--;
        char ch = (char) (n % 26 + 'A');
        n /= 26;
        sb.append(ch);
    }
    sb.reverse();
    return sb.toString();
}