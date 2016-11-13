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
		StringBuilder result = new StringBuilder();
		String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
		long num = Math.abs((long) numerator);
		long den = Math.abs((long) denominator);
		result.append(sign);
		result.append(num / den);
		long remainder = num % den;
		if (remainder == 0)
			return result.toString();
		result.append(".");
		System.out.println("result:" + result.toString());
		HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
		while (!hashMap.containsKey(remainder)) {
			System.out.println("remainder" + remainder);
			hashMap.put(remainder, result.length());
			result.append(10 * remainder / den);
			remainder = 10 * remainder % den;
		}
		int index = hashMap.get(remainder);
		result.insert(index, "(");
		result.append(")");
		return result.toString().replace("(0)", "");
		}
}