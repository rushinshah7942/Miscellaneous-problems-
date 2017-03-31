/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

*/

/*
 
 String[] dict = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
 int[] val = {1000,900,500,400,100,90,50,40,10,9,5,4,1}; 

 */

//recursive solution 
 
public class Solution 
{    
    public int romanToInt(String s) 
    {
        if(s.length()<0)
            return 0;
            
        if(s.startsWith("M")) return 1000+romanToInt(s.substring(1));
        if(s.startsWith("CM")) return 900+romanToInt(s.substring(2));
        if(s.startsWith("D")) return 500+romanToInt(s.substring(1));
        if(s.startsWith("CD")) return 400+romanToInt(s.substring(2));
        if(s.startsWith("C")) return 100+romanToInt(s.substring(1));            
        if(s.startsWith("XC")) return 90+romanToInt(s.substring(2));        
        if(s.startsWith("L")) return 50+romanToInt(s.substring(1));            
        if(s.startsWith("XL")) return 40+romanToInt(s.substring(2));            
        if(s.startsWith("X")) return 10+romanToInt(s.substring(1));    
        if(s.startsWith("IX")) return 9+romanToInt(s.substring(2));
        if(s.startsWith("V")) return 5+romanToInt(s.substring(1));            
        if(s.startsWith("IV")) return 4+romanToInt(s.substring(2));    
        if(s.startsWith("I"))    return 1+romanToInt(s.substring(1));
        
        return 0;
    }
}


// iterative solution

public int romanToInt(String s){
	
	int nums[]=new int[s.length()]; // 0 initially
    
	for(int i=0;i<s.length();i++){
        switch (s.charAt(i)) {
            
			case 'M':
                nums[i]=1000;
                break;
            case 'D':
                nums[i]=500;
                break;
            case 'C':
                nums[i]=100;
                break;
            case 'L':
                nums[i]=50;
                break;
            case 'X' :
                nums[i]=10;
                break;
            case 'V':
                nums[i]=5;
                break;
            case 'I':
                nums[i]=1;
                break;
        }
    }
    int sum=0;
    for(int i=0;i<nums.length-1;i++){
        if(nums[i]<nums[i+1])
            sum-=nums[i];
        else
            sum+=nums[i];
    }
    return sum+nums[nums.length-1];
}

// using hashmap 

public int romanToInt(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    
	int len = s.length();
	int index = 0, result = 0;
    
	while (index < len) {
        Character chCur = s.charAt(index);
        Character chNext = null;
        if (index + 1 < len)
            chNext = s.charAt(index + 1);
        if(chNext != null && map.get(chCur) < map.get(chNext))
            result -= map.get(chCur); // e.g. IV (4) -> we need to substract 1 and then add 5, as I < V
        else
            result += map.get(chCur);
        index++;
    }
    return result;
}