/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II
*/

public void reverseWords(char[] s){
	reverseWords(s,0,s.length-1);
	for(int i = 0, j = 0;i <= s.length;i++){
		if(i==s.length || s[i] == ' '){
			reverseWords(s,j,i-1);
			j = i+1;
		}
	}
}

private void reverseWords(char[] s, int begin, int end){
	while(begin < end){
		char c = s[begin];
		s[begin] = s[end];
		s[end] = c;
		begin++;
		end--;
	}
}