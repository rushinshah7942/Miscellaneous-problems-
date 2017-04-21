/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

*/

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        
		// "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);

        num %= den;

        if (num == 0) {
            return res.toString();
        }
        
        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());

        while (num != 0) {
            
			num *= 10; // like we do divison, we take 0
            res.append(num / den);
            num %= den;
			
			// we get similar remainder (num%= den) ,then we need to get it from map
            if (map.containsKey(num)) {
                int index = map.get(num); // index where we need to insert (
                res.insert(index, "(");
                res.append(")");// and just append )
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}