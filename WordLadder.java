/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
*/

public class Solution {

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    
        wordList.add(endWord);
		
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        
		int level = 0;
        
		while(!queue.isEmpty()){
            int size = queue.size();
        
			for(int i = 0; i < size; i++){ // BFS
                String cur = queue.remove();
            
				if(cur.equals(endWord)){ 
                    return level + 1;
                }
                for(int j = 0; j < cur.length(); j++)
                {
                    // change each letter and then check in wordlist
                    char[] word = cur.toCharArray();
                    for(char ch = 'a'; ch < 'z'; ch++){
                        word[j] = ch;
                        String check = new String(word);
                        if(!check.equals(cur) && wordList.contains(check)){
                            queue.add(check);
                            wordList.remove(check);
                        }
                    }
                }
            }
			
            level++;
        }
        return 0;
    }
}

public class WordNode
    {
        public string word;
        public int numsteps;
        
        public WordNode(string word, int numsteps)
        {
            this.word = word;
            this.numsteps = numsteps;
        }
    }

// Time O(len *n * 26^n)
// len is the length of the given start string(or end string), N is the number of elements of the dict.	
	
There are two ways to approach the problem(code in C#) - 
1.) One way BFS 
a.) Create a class to store word and the level of the word from the root.
b.) Maintain a queue of the above class to do BFS.
c.) loop until queue is not empty
d.) For each char replace it with 26 possible chars and check if it exists in dictionary 
e.) if found return the level.
	
public int LadderLength(string beginWord, string endWord, ISet<string> wordList) {
	if(beginWord ==null || endWord ==null)
		return 0;
	if(beginWord.Length != endWord.Length)
		return 0;
	if(beginWord.Length ==1)
		return 2;
	if(!wordList.Contains(beginWord) || !wordList.Contains(endWord))
	   return 0;
	
	int maxCount=Int32.MaxValue; 
	Queue<WordNode> myq = new Queue<WordNode>();
	myq.Enqueue(new WordNode(beginWord,1));
	
	while(myq.Count!=0){
		WordNode top = myq.Dequeue();
		if(top.word.Equals(endWord) && maxCount> top.numsteps)
			maxCount = top.numsteps;
		char [] ch = top.word.ToCharArray();
		for(int i =0; i < ch.Length; i++){
			for(char c = 'a'; c<='z'; c++){
				char temp = ch[i];
				ch[i]=c;
				String s = new string(ch);
				if(wordList.Contains(s)){
					myq.Enqueue(new WordNode(s, top.numsteps +1));
					if(!top.word.Equals(endWord))
						wordList.Remove(s);
				}
				ch[i]=temp;
			}
		}       
	}
	return maxCount== Int32.MaxValue ? 0 : maxCount;
}


// cutting runtime by a factor of 2 
// O(len * n * {26^(n/2) + 26^(n/2)} )
//or simply
// O(len * n * 26^(n/2) )

/* 

"The idea behind bidirectional search is to run two simultaneous searches—one forward from
the initial state and the other backward from the goal—hoping that the two searches meet in
the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth. "

*/

2. Biderectional BFS - 
a.) In previous solution as we go deeper , the number of words to loop will increase exponentially.
b.) if we consider end word as begin word , we could repeat the same step to find the soultion.
c.) Therefore , if we start from both end and eventually check if we found intermediate target list of words which can eventually form the end word in end list.
d.) Since we are using HashSet , We can save time for lopping through each word at deeper levels.

public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
	Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

	int len = 1;
	int strLen = beginWord.length();
	HashSet<String> visited = new HashSet<String>();
	
	beginSet.add(beginWord);
	endSet.add(endWord);

	while (!beginSet.isEmpty() && !endSet.isEmpty()) {
	
		// every visited word will be added into endset
		if (beginSet.size() > endSet.size()) {
			Set<String> set = beginSet;
			beginSet = endSet;
			endSet = set;
		}

		Set<String> temp = new HashSet<String>();
		
		for (String word : beginSet) {
		
			char[] chs = word.toCharArray();

			for (int i = 0; i < chs.length; i++) {

				for (char c = 'a'; c <= 'z'; c++) {
					char old = chs[i];
					chs[i] = c;
					String target = String.valueOf(chs);

					// and keep checking if endSet contains target or not	
					if (endSet.contains(target)) {
						return len + 1;
					}

					if (!visited.contains(target) && wordList.contains(target)) {
						temp.add(target);
						visited.add(target);
					}
					chs[i] = old;
				}
			}
		}

		beginSet = temp;
		len++;
	}
	
	return 0;
}

Output
---------------------
"hit"
"cog"
["hot","dot","dog","lot","log","cog"]
Your stdout

Execution
-------------------

begin set: [hit]
end set: [cog]

begin set: [hot]
end set: [cog]

begin set: [lot, dot]
end set: [cog]
// at this point begin and end are interchanged

begin set: [log, cog, dog]
end set: [lot, dot]