# 56 Bipartite (Lai)

Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.

**Examples**

1  --  2

  /  

3  --  4

is bipartite (1, 3 in group 1 and 2, 4 in group 2).

1  --  2

  /  |

3  --  4

is not bipartite.

**Assumptions**

- The graph is represented by a list of nodes, and the list of nodes is not null.



**Solution:**

```java
/**
 * public class GraphNode {
 *   public int key;
 *   public List<GraphNode> neighbors;
 *   public GraphNode(int key) {
 *     this.key = key;
 *     this.neighbors = new ArrayList<GraphNode>();
 *   }
 * }
 */
public class Solution {
  public boolean isBipartite(List<GraphNode> graph) {
    // write your solution here
    // GraphNode: whether visited
    // Integer: color for the graphNode
    HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
    for (GraphNode node : graph){
      if (bfs(node, visited) == false){
        return false;
      }
    }
    return true;
  }

  public boolean bfs(GraphNode node, HashMap<GraphNode, Integer> visited){
    // wether visited
    if (visited.containsKey(node)){
      return true;
    }

    // not visited have to check
    Queue<GraphNode> queue = new LinkedList<GraphNode>(); // [1
    queue.offer(node); // I vistied
    visited.put(node, 0);

    while (!queue.isEmpty()){ //   1 
      GraphNode cur = queue.poll();
      int curGroup = visited.get(cur); // 0

      int neiGroup = 1;
      if (curGroup == 1){
        neiGroup = 0;
      }
      for (GraphNode nei: cur.neighbors){
        if (visited.containsKey(nei)){
          // visited before
          if (visited.get(nei) != neiGroup){
            return false;
          }
        }else {
          // not visited before
          visited.put(nei, neiGroup);
          queue.offer(nei);
        }
      }
    }
    return true;
  }
}

// TC; O(n)
// SC: O(n)
```

