/*
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
*/

public class Solution 
{
    public List<String> fizzBuzz(int n) 
    {
        List<String> result = new ArrayList<>();
        
        for(int i=1;i<=n;i++)
        {
            String temp = "";
            if(i%3 == 0)
                temp = "Fizz";
            if(i%5 == 0)
            {
                if(temp.equals(""))
                    temp = "Buzz";
                else
                    temp = "FizzBuzz";
            }
            
            if(temp.equals(""))
                result.add(String.valueOf(i));
            else
                result.add(temp);
        }
        
        return result;
    }
}