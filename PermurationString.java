/*
Print all n! permutations of a String
*/

// Recursive method (in order)
// Use if your input is String

static List<String> result = new ArrayList<>();

public static void permutation(String str) { 
    permutation("", str); 
	System.out.println(result);
}

private static void permutation(String prefix, String str) {
    int n = str.length();

    if (n == 0) 
		result.add(prefix);
    else 
	{
        for (int i = 0; i < n; i++)
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
    }
}


// What if array is given, and print all permutations of an array

static List<String> result = new ArrayList<>();

public static List<String> permutation(char[] a) // or int[]
{
	int n = a.length;
	perm2(a,n);
}

private static void perm2(char[] a, int n)
{
	if(n == 1)
		result.add(new String(a));
	
	for(int i=0;i<n;i++)
	{
		swap(a,i,n-1);
		perm2(a,n-1);
		swap(a,i,n-1);
	}
}
private static void swap(char[] a, int i, int j)
{
	char c = a[i];
	a[i] = a[j];
	a[j] = c;
}
