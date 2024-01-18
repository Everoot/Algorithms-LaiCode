#785 Is Graph Bipartite?

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
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++){
            color[i] = -1;
        }
        for (int i = 0; i < n; i++){
            if (color[i] == -1){
                if (bfs(i, graph, color) == false){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean bfs(int node, int graph[][], int[] color){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(node);
        color[node] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for (int nei : graph[cur]){
                if (color[nei] == -1){
                    if (color[cur] == 0){
                        color[nei] = 1;
                    }else{
                        color[nei] = 0;
                    }
                    queue.add(nei);
                }else if (color[nei] == color[cur]){
                    return false;
                }
            }

        }
        return true;
    }
}
//TC: O(N+E)
//SC: O(n)
```



```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;   // graph length 4
        int[] colors = new int[n];
        for (int i = 0; i < n; i++){
            colors[i] = -1;
        }


        for (int node = 0; node < n; node++){
            if (colors[node] == -1){
                // has not visit 
                Stack<Integer> stack = new Stack(); // [ 0 
                stack.push(node);
                colors[node] = 0;

                while(!stack.empty()){
                    Integer cur = stack.pop(); // 0
                    for (int nei : graph[cur]){
                        if (colors[nei] == -1){
                            // not visited
                            stack.push(nei);
//                            colors[nei] = colors[cur] ^ 1;
                            if (colors[cur] == 0){
                                colors[nei] = 1;
                            }else{
                                colors[nei] = 0;
                            }
                        }else if (colors[nei] == colors[cur]){
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







