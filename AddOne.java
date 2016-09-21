/*

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

*/

public class Solution 
{
    public int[] plusOne(int[] digits) 
    {
        if(digits == null)
            return digits;
            
        if(digits.length == 0)
        {
            int[] aa = {1};
            return aa;
        }
     
        List<Integer> ls = new ArrayList<Integer>();
           
        int i= digits.length-1;
        int carry = 0;
        
        while(i>=0)
        {
            int sum = 0;
            
            if(i == digits.length-1)
                sum = digits[i] + 1 + carry;
            else
                sum = digits[i] + carry;
            if(sum >= 10)
            {
                carry = 1;
                sum = sum - 10;
            }
            else 
                carry = 0;
    
            ls.add(sum);
            i--;
        }
        
        if(carry == 1)
            ls.add(1);
        
        Object[] obj = ls.toArray();
        
        int[] result = new int[obj.length];
        int cntr= result.length-1;       
        for(Object temp : obj)
        {
            result[cntr--] = (int)temp;
        }
            
        return result;
    }
}

// Very simple logic 

public class AddOne
{
	public static void main(String[] args)
	{
		int[] digits = {9,7,8};
		for(int val: plusOne(digits))
			System.out.println(val + " ");
	}
	public static int[] plusOne(int[] digits) 
	{
        
    int n = digits.length;
    for(int i=n-1; i>=0; i--) 
    {
        if(digits[i] < 9) 
        {
            digits[i]++;
            return digits;
        }
        
        digits[i] = 0;
    }
    
    // if it comes here, only when most significant number is 9
    int[] newNumber = new int [n+1];
    newNumber[0] = 1;
    
    return newNumber;
	}
}