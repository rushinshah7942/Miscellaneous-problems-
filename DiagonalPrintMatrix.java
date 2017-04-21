/*

Print an NxM matrix with nw-se diagonals starting at bottom left corner. Ex:

1  2  3  4
5  6  7  8
9 10 11 12

The output should be:

9
5 10
1 6 11
2 7 12
3 8
4

*/

// Solution-1
// one of the approach is to use queue and add top and right elements
// and keep hashset of all visited elements

// Solution -2 
// without using extra space 
// as below
public class NWSE {

    public static void main(String[] args) {
        // TODO code application logic here
        int[][] inp = new int[][] {
            {1,2,3},
            {5,6,7},
            {9,10,11}};
       
        int temp;
     
	   for(int i = inp.length-1,j=0; i>=0 ; i--,j=0)
       {
           System.out.print(inp[i][j] + "  ");
		   
           temp = i; // store old row where we started 
           
		   while(i+1 < inp.length && j+1 < inp[0].length)
           {
               System.out.print(inp[i+1][j+1] + "  "); // keep print right diagonal
               i++;j++;
           }
           
		   i = temp; // restore old row
           
		   System.out.println();
		   
		   // special case - when we switch from processing row to processing column
           if(i == 0)
           {
			   // now, column will go from 1 to inp[0].length-1
			   
               for (int k = 1; k < inp[0].length; k++) {

				   temp = k; // save old column
                   
				   System.out.print(inp[i][k] + "  ");
				   
				   // keep getting right diagonal
                   while (i + 1 < inp.length && k + 1 < inp[0].length) {
                       System.out.print(inp[i + 1][k + 1] + "  ");
                       i++;
                       k++;
                   }
                   
				   k = temp;
                   
				   i = 0;
                   
				   System.out.println();

               }
           }
       }
               
    }       
}
