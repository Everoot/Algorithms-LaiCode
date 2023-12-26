#  132 Deep Copy Undirected Graph (Lai)

Make a deep copy of an undirected graph, there could be cycles in the original graph.

**Assumptions**

- The given graph is not null



**Solution:**

```java
/*
* class GraphNode {
*   public int key;
*   public List<GraphNode> neighbors;
*   public GraphNode(int key) {
*     this.key = key;
*     this.neighbors = new ArrayList<GraphNode>();
*   }
* }
*/
public class Solution {
  public List<GraphNode> copy(List<GraphNode> graph) {
    // Write your solution here.
    if (graph == null){
      return null;
    }

    Map<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
    // first GrapNode is original , second GraphNode is clone
		
    // put all the graphnode in the map
    for (GraphNode node : graph){
      if (!map.containsKey(node)){
        map.put(node, new GraphNode(node.key));
        helper(node, map); // -> add the neighbors
      }
    }

    return new ArrayList<GraphNode>(map.values());
  }

  private void helper(GraphNode seed, Map<GraphNode, GraphNode> map){
    GraphNode clone = map.get(seed);
        // copy neihbors
    for(GraphNode neighbor : seed.neighbors){
      if (!map.containsKey(neighbor)){
        map.put(neighbor, new GraphNode(neighbor.key));
        helper(neighbor, map);
      }
      clone.neighbors.add(map.get(neighbor));
    }
  }
}

// TC:O(n) = O(E+V)
// SC:O(n) = O(E+V)
```

