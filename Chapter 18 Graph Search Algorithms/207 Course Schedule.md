# 207 Course Schedule

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

- For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return `true` if you can finish all courses. Otherwise, return `false`.

 

**Example 1:**

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**

```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```



**Solution:**

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<List<Integer>>(numCourses);

        for (int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            adj.get(prerequisiteCourse).add(course);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses;i ++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }

        int nodesVisited = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            nodesVisited++;

            for (int neighbor : adj.get(node)){
                indegree[neighbor]--;
                if (indegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        return nodesVisited == numCourses;
        
    }
}
// TC: O(m+n)
// SC: O(m+n)
```

https://leetcode.com/explore/learn/card/graph/623/kahns-algorithm-for-topological-sorting/3886/

<img src="./207 Course Schedule/Screenshot 2024-01-14 at 17.51.52.png" alt="Screenshot 2024-01-14 at 17.51.52" style="zoom:50%;" />

