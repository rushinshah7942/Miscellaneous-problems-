/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/


// https://en.wikipedia.org/wiki/Pascal%27s_triangle

 // Formula:=>  (n; r)=(n!)/((n-r)!r!)=(n-1; r)+(n-1; r-1). 
 
public class Solution 
{
    public List<List<Integer>> generate(int numRows) 
    {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows <=0)
        {
            return triangle;
        }
        for (int i=0; i<numRows; i++)
        {
            List<Integer> row =  new ArrayList<Integer>();
            for (int j=0; j<i+1; j++)
            {
                if (j==0 || j==i)
                {
                    row.add(1);
                } 
                else 
                {
                    row.add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}