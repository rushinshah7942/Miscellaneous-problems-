/*
Zombie Clusters
------------------
There are n zombies in Seattle, and Liv and Ravi are trying to track them down to find out
who is creating new zombies — thus preventing an apocalypse. Other than the patient-zero
zombies (who became so by mixing MaxRager and tainted Utopium), new people only
become zombies after being scratched by an existing zombie; for this reason, zombiism is
transitive. This means that if zombie 0 knows zombie 1 and zombie 1 knows zombie 2, then
zombie 0 is connected to zombie 2. A zombie cluster is a group of zombies who are directly
or indirectly linked through the other zombies they know (such as the one who scratched
them or supplies them with brains).

Complete the zombieCluster function in your editor. It has 1 parameter: an array of binary
strings (i.e., composed of 0s and 1s) named zombies that describes an n × n matrix of known
connected zombies; if zombies[i][j] = 0, then the i and j zombies do not know one another
(otherwise, the cell contains a 1 and they do know one another). Your function must return
an integer denoting the number of zombie clusters Liv and Ravi have identified in Seattle.
Note: Method signatures may vary depending on the requirements of your chosen language.

Constraints
1 ≤ n ≤ 300
0 ≤ i < n
|zombies| = n
Each zombies[i] contains a binary string of n zeroes and ones.
zombies[i][i] = 1, where 0 ≤ i < n.
zombies[i][j] = zombies[j][i], where 0 ≤ i < j < n.

Output Format
Your function must return a single integer denoting the number of different zombie clusters
in Seattle. This is printed to stdout by the locked stub code in your editor.

Sample Input 0
4
1100
1110
0110
0001

Sample Output 0
2

Sample Input 1
5
10000
01000
00100
00010
00001

Sample Output 1
5

*/

import java.util.*;

public class Solution{
  
  public static int countClusters(int[][] matrix, int n){
  	// find edges in put them into map
    // HashMap<Integer, Integer> edges = new HashMap<>();
	//int[][] edges = new int[][]; -> Dynamically create 2D array
	List<int[]> edges = new ArrayList<>();
	
    for(int i= 0;i<n;i++) {
      for(int j=i+1;j<n;j++){
        if(matrix[i][j] == 1){
          edges.add(new int[]{i,j} );
        }
      }
    }
    // System.out.println(edges);
    if(edges.size() == 0)
		return n;
	
    return countComponents(n,edges);
  }
  
    public static int countComponents(int n, HashMap<Integer,Integer> edges) {
      
      int count = n;

      int[] root = new int[n];
      for(int i=0; i<n; i++){
          root[i]=i;        
      }

      for(int[] edge : edges){
          
		  int x = edge[0];
          int y = edge[1];

          int xRoot = getRoot(root, x);
          int yRoot = getRoot(root, y);

          if(xRoot!=yRoot){
              count--;
              root[xRoot]=yRoot;
          }
      }
      return count;
  }

  public static int getRoot(int[] arr, int i){
      while(arr[i]!=i){
          arr[i]= arr[arr[i]];
          i=arr[i];
      }
      return i;
  }
  
  public static void main(String[] args){
    int[][] matrix = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
    
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
      	System.out.print(matrix[i][j] + " ");  
      }
      System.out.println();
    }
    
    System.out.println("count: " + countClusters(matrix, 4));
  }
}




/*
My submission
--------------------

    static int zombieCluster(String[] zombies) {
    
        int offset = 1, n = zombies.length, i=0;
        List<int[]> edges = new ArrayList<>();
        
        for(String str : zombies){
            for(int j = offset;j < n; j++){
                if(str.charAt(j) == '1'){
                    edges.add(new int[]{i,j});
                }
            }
            i++; offset++;
        }
        
        if(edges.size() == 0){
            return n;
        }
        
        return countClusters(n,edges);
    }

    static int countClusters(int n, List<int[]> edges){
        int count = n;
        
        int[] root = new int[n];
        for(int i=0;i<n;i++){
            root[i] = i;
        }
        
        // union-find logic to find connected components
        for(int[] edge : edges){
            int x = edge[0]; 
            int y = edge[1];
            
            int xRoot = getRoot(root,x);
            int yRoot = getRoot(root,y);
            
            if(xRoot != yRoot){
                count--;
                root[xRoot] = yRoot;
            }
        }
        return count;
    }
    static int getRoot(int[] array, int i){
        
        while(array[i] != i){
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }
*/