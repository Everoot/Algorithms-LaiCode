# 129 Lowest Common Ancestor IV (Lai)

Given K nodes in a binary tree, find their lowest common ancestor.

**Assumptions**

- K >= 2
- There is **no** parent pointer for the nodes in the binary tree
- The given K nodes are **guaranteed** to be in the binary tree

**Examples**

​    5

​    /  \

   9   12

  /  \    \

 2   3    14

The lowest common ancestor of 2, 3, 14 is 5

The lowest common ancestor of 2, 3, 9 is 9



**Solution:**

```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    // Write your solution here.
    Set<TreeNode> set = new HashSet<>(nodes);
    TreeNode result = helper(root, set);
    return result;
  }

  public TreeNode helper(TreeNode root, Set<TreeNode> set){
    // base case
    if (root == null){
      return root;
    }

    if (set.contains(root)){
      return root;
    }

    TreeNode left = helper(root.left, set);
    TreeNode right = helper(root.right, set);

    if (left != null && right != null){
      return root;
    }

    if (left != null){
      return left;
    }else{
      return right;
    }
  }
}

// TC: O(n)
// SC: O(h+k)
```

