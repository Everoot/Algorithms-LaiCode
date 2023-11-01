# 648 Lowest Common Ancestor VI (Lai)

Given two nodes in a K-nary tree, find their lowest common ancestor.

**Assumptions**

-There is no parent pointer for the nodes in the K-nary tree.

-The given two nodes are guaranteed to be in the K-nary tree.

**Examples**



​    5

   /  \

   9  12

  / | \   \

 1 2  3   14



The lowest common ancestor of 2 and 14 is 5.

The lowest common ancestor of 2 and 9 is 9.



**Solution:**

```java
/**
* public class KnaryTreeNode {
 *     int key;
 *     List<KnaryTreeNode> children;
 *     public KnaryTreeNode(int key) {
 *         this.key = key;
 *         this.children = new ArrayList<>();
 *     }
 * }
 */
public class Solution {
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
    // Write your solution here
    Set<KnaryTreeNode> sets = new HashSet<KnaryTreeNode>(nodes);
    return helper(root, sets);
  }

  private static KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> sets){
    if (root == null){
      return root;
    }

    if (sets.contains(root)){
      return root;
    }

    KnaryTreeNode left = null;
    for (KnaryTreeNode branch: root.children){
      KnaryTreeNode subresult = helper(branch, sets);
      if (subresult == null){
        continue;
      }else if (left == null){
        left = subresult;
      }else{
        return root;
      }
    }
    return left;
  }
}

// TC:O(n)
// SC:O(n)
```

