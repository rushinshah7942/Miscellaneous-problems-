/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

public class Solution 
{
    // Kahn's algorithm
    public int[] findOrder(int numCourses, int[][] prerequisites) 
    {
        if(numCourses < 0) 
            return new int[]{0};
        
        if(prerequisites.length == 0)
        {
            int[] res = new int[numCourses];
            for(int i = 0; i < numCourses; i ++){
                res[i] = i;
            }
            return res;
        }
        
        int[] res = new int[numCourses];
        ArrayList[] graph = new ArrayList[numCourses];
        int[] in_degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count = 0;
        
        for(int i = 0; i < numCourses; i ++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int[] require : prerequisites){
            in_degree[require[0]] ++;
            graph[require[1]].add(require[0]);
        }
        for(int i = 0; i < numCourses; i ++){
            if(in_degree[i] == 0){
                queue.add(i);
                res[count ++] = i;
            }
        }
        
        while(!queue.isEmpty()){
            int cur = (int)queue.poll();
            for(int i = 0; i < graph[cur].size(); i ++){
                int to_learn = (int)graph[cur].get(i);
                in_degree[to_learn] --;
                if(in_degree[to_learn] == 0){
                    queue.add(to_learn);
                    res[count ++] = to_learn;
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }
}