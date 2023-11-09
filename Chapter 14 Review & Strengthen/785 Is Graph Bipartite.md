# 785 Is Graph Bipartite?

There is an **undirected** graph with `n` nodes, where each node is numbered between `0` and `n - 1`. You are given a 2D array `graph`, where `graph[u]` is an array of nodes that node `u` is adjacent to. More formally, for each `v` in `graph[u]`, there is an undirected edge between node `u` and node `v`. The graph has the following properties:

- There are no self-edges (`graph[u]` does not contain `u`).
- There are no parallel edges (`graph[u]` does not contain duplicate values).
- If `v` is in `graph[u]`, then `u` is in `graph[v]` (the graph is undirected).
- The graph may not be connected, meaning there may be two nodes `u` and `v` such that there is no path between them.

A graph is **bipartite** if the nodes can be partitioned into two independent sets `A` and `B` such that **every** edge in the graph connects a node in set `A` and a node in set `B`.

Return `true` *if and only if it is **bipartite***.

**Example 1:**

![img](./785 Is Graph Bipartite/bi2.jpg)

```
Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
```

**Example 2:**

![img](./785 Is Graph Bipartite/bi1.jpg)

```
Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
```



**Solution:**

```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        // [[1,2,3],[0,2],[0,1,3],[0,2]]
        //   
        int n = graph.length; // 4 
        int[] color = new int[n]; // 
        Arrays.fill(color, -1); // 0, -1, -1, -1

        for (int start = 0; start < n; start++) {
            if (color[start] == -1) {  // 
                Stack<Integer> stack = new Stack(); // [ 1
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {       
                    Integer node = stack.pop(); // 0
                    for (int nei: graph[node]) { // [1,2,3]
                        if (color[nei] == -1) { // 
                            stack.push(nei);
                            color[nei] = color[node] ^ 1; // 异或
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
// TC:O(n)
// SC:O(n)
```

**Complexity Analysis**

- Time Complexity: O(N+E), where *N* is the number of nodes in the graph, and E is the number of edges. We explore each node once when we transform it from uncolored to colored, traversing all its edges in the process.
- Space Complexity: O(N), the space used to store the `color`.



