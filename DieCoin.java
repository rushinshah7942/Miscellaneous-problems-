/*
Describe an algorithm to output a die roll(a random number from 1 to 6), given a function that outputs a coin toss(a random number from 1 to 2). Each possible outcome should be equally likely
*/


/*

Brute-Force solution

One solution is tossing the coin 3 times and using the outcome as bits of a three-bit number. So our outcomes would be 000, 001, 010, 011, 100, 101, 110, 111 (0 to 7). 
If our outcome is 110 or 111 we would have to discard it and toss again.
*/
public static int roll(){
	int outcome = 0;
	
	do{
		int bit1 = toss()-1;
		int bit2 = toss()-1;
		int bit3 = toss()-1;
		
		outcome = 4*bit1 + 2*bit2 + 1*bit3;
	}
	while(outcome > 5)

	return outcome+1; 
}
public static int toss(){
	return random(1,2);
}

// Math.random() return number from 0 to 1
public static int random(int low,int high){
	return low + (int)(Math.random() * ((high-low)+1));
}


// Optimization

/*
it seems that discarding 3 tosses is a waste. It seems like we could reuse the lastly tossed number (in the case of 110 and 111 - 0 and 1), and we would need only 2 additional tosses instead of 3.
*/
public static int roll(){
	int outcome = 0;
	int bit1 = toss()-1;
	do{
		int bit2 = toss()-1;
		int bit3 = toss()-1;
		
		outcome = 4*bit1 + 2*bit2 + 1*bit3;
		
		bit1 = bit3;
	}
	while(outcome > 5)

	return outcome+1; 
}
