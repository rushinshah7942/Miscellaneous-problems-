/*
Write a function that takes a string as input and returns the string reversed.
*/

//Example:
//Given s = "hello", return "olleh".

	
	//method 1: use StringBuilder
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    //method 2: use swap method
	// In-place
    public String reverseString(String s)
	{
        if(s == null || s.length() == 0)
            return "";
        char[] cs = s.toCharArray();
        int begin = 0, end = s.length() - 1;
        while(begin <= end)
		{
            char c = cs[begin];
            cs[begin] = cs[end];
            cs[end] = c;
            begin++;
            end--;
        }
        
        return new String(cs);
    }
	
	// method - 3 
	// using recursion

	public void reverseString() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String reverseAlphabet = reverse(str, alphabet.length()-1);
	}

	String reverse(String str, int index){
		if(index == 0){
			return str.charAt(0) + "";
		}

		char letter = str.charAt(index);
		return letter + reverse(str, index-1);
	}
	