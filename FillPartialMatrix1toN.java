/*
Given a nxn matrix, with partially filled cells of numbers from 1..n and the rest with 0's. Fill the cells such each row and column has numbers 1 to n without any repetition. 
Eg: 
[ 
[1, 2, 0], 
[0, 1, 0], 
[0, 0, 1] 
] 

[ 
[1, 2, 3], 
[3, 1, 2], 
[2, 3, 1] 
]

*/

//Time Complexity: O(N^2) where N is the size of the matrix. Space: O(N^2).

public int[][] fillMatrix(int[][] m){
	if(m == null || m.length == 0 || m[0].length == 0){
		throw new IllegalArgumentException();
	}
	boolean[][] rowData = new boolean[m.length][m.length]; // to mark number in given row
	boolean[][] colData = new boolean[m.length][m.length];

	for(int i = 0; i < m.length; i++){
		for(int c = 0; c < m[0].length; c++){
			if(m[i][c] != 0){
				rowData[i][m[i][c]] = true; // given row, mark used number 
				colData[c][m[i][c]] = true; // given column, mark used number 
			}
		}
	}
	
	boolean r = fillHelp(m,0,0,rowData,colData);
	
	return m;
}

private boolean fillHelp(int[][] m, int r, int c, boolean[][] rowDetail, boolean[][] colDetail){
	while(r < m.length){
		while(c < m.length){
			if(m[r][c] == 0){
				for(int i = 1; i <= 9; i ++){
					if(!rowDetail[r][i] && !colDetail[c][i])
					{
						// if not marked
						m[r][c] = i;
						rowDetail[r][i] = true;
						colDetail[c][i] = true;
						
						result = fillHelp(m,r,c+1,rowDetail,colDetail);
						
						if(result){
							return true;
						}
						
						rowDetail[r][i] = false;
						colDetail[c][i] = false;
						m[r][c] = 0;
					}
				}
				return false;
			
			}else{
				c++;
			}
		}
		c = 0;
		r++;
	}
	return true;
}