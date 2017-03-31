/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

*/

public String intToRoman(int num) 
{
	
		// ask interviewer this mapping, as no one can remember this mapping
		String[] dict = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] val = {1000,900,500,400,100,90,50,40,10,9,5,4,1};  
		
        StringBuilder sb = new StringBuilder("");
        
		for(int i=0; i<13; i++) 
        {
            if(num>=val[i]) 
            {
                int count = num/val[i]; // find quotient
				
                num %= val[i];  // find remainder
				
                for(int j=0; j<count; j++) 
                {
					// repeat quotient times
                    sb.append(dict[i]);
                }
				
            }
        }
        return sb.toString(); 		
}