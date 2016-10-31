/*
Print all permutations of a String
*/

static List<String> result = new ArrayList<>();

public static void permutation(String str) { 
    permutation("", str); 
	System.out.println(result);
}

private static void permutation(String prefix, String str) {
    int n = str.length();
    if (n == 0) 
		result.add(prefix);
    else {
        for (int i = 0; i < n; i++)
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
    }
}