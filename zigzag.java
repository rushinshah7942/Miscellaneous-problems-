/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

*/

public String convert(String s, int numRows) 
{
	if (numRows == 1)
		return s;
 
	StringBuilder sb = new StringBuilder();
	// step
	int step = 2 * numRows - 2;
 
	for (int i = 0; i < numRows; i++) 
	{
		//first & last rows
		if (i == 0 || i == numRows - 1) 
		{
			for (int j = i; j < s.length(); j = j + step) 
			{
				sb.append(s.charAt(j));
			}
		} 
		//middle rows
		else 
		{
			int j = i;
			boolean flag = true;
			int step1 = 2 * (numRows - 1 - i);
			int step2 = step - step1;
 
			while (j < s.length()) 
			{
				sb.append(s.charAt(j));
				if (flag)
					j = j + step1;
				else
					j = j + step2;
				flag = !flag;
			}
		}
	}


	return sb.toString();
}

