/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

// Short version of solution
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
		int j = b.length() -1;
		int carry = 0;
        
		while (i >= 0 || j >= 0) {
            int sum = carry;
			
			// check again for individual pointers
            if (j >= 0) 
				sum += (b.charAt(j--) - '0'); // handles explicit casting 
            if (i >= 0) 
				sum += (a.charAt(i--) - '0');
            
			sb.append(sum % 2); // possible sums -> 0 1 2 
            carry = sum / 2;
        }
        if (carry != 0) 
			sb.append(carry);
        
		return sb.reverse().toString(); // reverse
    }
}

public class Solution 
{
    public String addBinary(String a, String b) 
    {
        if(a== null && b== null)
            return "0";
        
        if(a.equals("") && b.equals(""))
            return "0";
        
        int l1 = a.length();
        int l2 = b.length();
        
        StringBuilder sb = new StringBuilder();
        
        int carry = 0;
        char ch1;
        char ch2;
        while(l1 >0 && l2 >0)    
        {
            l1--;
            l2--;
            
            ch1 = a.charAt(l1);
            ch2 = b.charAt(l2);
            
            if(ch1 == '1' && ch2 == '1')
            {
                if(carry == 1)
                {
                    sb.append('1');
                    carry = 1;
                }
                else
                {
                    carry = 1;
                    sb.append('0');
                }
            }
            else if(ch1 == '0' && ch2 == '0')
            {
                if(carry == 1)
                {
                    sb.append('1');
                    carry = 0;
                }
                else
                {
                    sb.append('0');
                }
            }
            else
            {
                if(carry == 1)
                {
                    sb.append('0');
                    carry = 1;
                }
                else
                {
                    sb.append('1');
                }
            }
        }    
            while(l1 >0  )  
            {
                l1--;
                ch1 = a.charAt(l1);
                if(ch1 == '1')
                {
                    if(carry == 1)
                    {
                        carry =1;
                        sb.append("0");
                    }
                    else
                        sb.append(ch1);
                }
                else
                {
                    if(carry == 1)
                    {
                        carry =0;
                        sb.append("1");
                    }
                    else
                        sb.append(ch1);
                }
            }
            while(l2 >0)
            {
                l2--;
                ch2 = b.charAt(l2);
                if(ch2 == '1')
                {
                    if(carry == 1)
                    {
                        carry =1;
                        sb.append("0");
                    }
                    else
                        sb.append(ch2);
                }
                else
                {
                    if(carry == 1)
                    {
                        carry =0;
                        sb.append("1");
                    }
                    else
                        sb.append(ch2);
                }
            }
        
        if(carry == 1)
            sb.append(1);
            
            
        return sb.reverse().toString();    
    }
}

