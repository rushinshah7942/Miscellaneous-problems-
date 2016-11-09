/*
Give an algorithm to randomly reorder integers so that each possible reordering is likely.
or
Shuffle deck of cards for any permutations of cards
*/

public class RandomOrder{
	public static void main(String[] args){
		int[] nums = {1,2,3,4,5,6,7,8,9};
		int count= 0;
		while(count < 10){
			randomOrder(nums);
			count++;
		}
	}
	public static void randomOrder(int[] arr){
		int n = arr.length;
		
		for(int i=0;i<n;i++){
			// choose index between [i,n-1]
			int random = i + (int)(Math.random() * (n-i));
			// swap
			int temp  = arr[random];
			arr[random] = arr[i];
			arr[i] = temp;
		}
		for(int i=0;i<n;i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}

