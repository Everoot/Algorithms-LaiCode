# 647 Lowest Common Ancestor V (Lai)

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
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
    // Write your solution here
    // base case 
    if (root == null){
      return root;
    }

    if (root == a || root == b){
      return root;
    }

    KnaryTreeNode store = null; // find one 
    for (KnaryTreeNode node : root.children){
      KnaryTreeNode childResult = lowestCommonAncestor(node, a, b);

      if (childResult == null){
        continue;
      }

      if (store == null){
        store = result;    
      }else{            // find two    -> means I find one and two in the child -> current root the lca
        return root;
      }
    }
    return store; // -> if (left != null)
  }
}
/*
          5
        /  \
        9  12   -root 1 
      / | \  
      1 2 3

// TC: O(n)
// SC: O(n)

*/
```

