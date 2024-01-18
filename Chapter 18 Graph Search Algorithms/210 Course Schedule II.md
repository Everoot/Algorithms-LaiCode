# 210 Course Schedule II

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

- For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return *the ordering of courses you should take to finish all courses*. If there are many valid answers, return **any** of them. If it is impossible to finish all courses, return **an empty array**.

 

**Example 1:**

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
```

**Example 2:**

```
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
```

**Example 3:**

```
Input: numCourses = 1, prerequisites = []
Output: [0]
```



**Solution:**

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // the adjacency list representation of prerequisites.
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for (int[] dependency : prerequisites){
            int x = dependency[0];
            int y = dependency[1];
            graph.get(y).add(x);
        }

        return topologicalSort(graph);
    }

    private int[] topologicalSort(List<List<Integer>> graph){
        int numCourses = graph.size();
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (int x = 0; x < numCourses; x++){
            for (int y : graph.get(x)){
                incomingEdges[y]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int x = 0; x < numCourses; x++){
            if (incomingEdges[x] == 0){
                queue.offer(x);
            }
        }

        int numExpanded = 0;
        while (!queue.isEmpty()){
            int x = queue.poll();
            topologicalOrder[numExpanded++] = x;
            for (int y : graph.get(x)){
                if (--incomingEdges[y] == 0){
                    queue.offer(y);
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder : new int[]{};
    }
}

// TC: O(V+E)
// SC: O(V+E)
```

https://www.youtube.com/watch?v=Akt3glAwyfY&t=145s

