/*
A gene string can be represented by an 8-character long string, with choices from "A","C","G","T". 
Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string. 
For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation. 
Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string. 

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1. 

NOTE: 1. Starting point is assumed to be valid, so it might not be included in the bank. 2. If multiple mutations are needed, all mutations during in the sequence must be valid. For example, 
bank: "AACCGGTA" 
start: "AACCGGTT" 
end: "AACCGGTA" 
return: 1

bank: "AACCGGTA", "AACCGCTA", "AAACGGTA"
start: "AACCGGTT"
end: "AAACGGTA"
return: 2
bank: "AAAACCCC", "AAACCCCC", "AACCCCCC"
start: "AAAAACCC"
end: "AACCCCCC"
return: 3
*/

public class Solution 
{
    public int minMutation(String start, String end, String[] bank) 
    {
        if(start== null || end == null || start.length() != end.length())
            return -1;
            
        Set<String> bankSet = new HashSet<>();
        
        for(String s : bank)
            bankSet.add(s);
        
        if(!bankSet.contains(end))
            return -1;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        queue.offer("#");
        
        int level = 0;
        int min = Integer.MAX_VALUE;
        char[] genes = new char[]{'A','C','T','G'};
        char[] check_end = end.toCharArray();
        
        while(!queue.isEmpty())
        {
            String temp = queue.poll();
            if(temp.equals("#"))
            {
                level++;
                if(!queue.isEmpty())
                    queue.offer("#");
            }
            else if(temp.equals(end))
                min = Math.min(min,level);
                
            else
            {
                char[] check_start = temp.toCharArray();
                for(int i=0;i<check_start.length;i++)
                {
                    if(check_start[i] != check_end[i])
                    {
                        char current = check_start[i];
                        for(char c : genes)
                        {
                            if(c != current)
                            {
                                check_start[i] = c;
                                String tmp_str = String.valueOf(check_start);
                            
                                if(bankSet.contains(tmp_str))
                                    queue.offer(tmp_str);
                            }
                        }
                        check_start[i] = current;
                    }
                }
            }
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }
}
