# 128 Lowest Common Ancestor III (Lai)

Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).

Return null If any of the nodes is not in the tree.

**Assumptions**

- There is no parent pointer for the nodes in the binary tree
- The given two nodes are **not** guaranteed to be in the binary tree

**Examples**

​    5

​    /  \

   9   12

  /  \    \

 2   3    14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)

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
  boolean oneFound = false;
  boolean twoFound = false;
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    // write your solution here

    if (root == null){
      return root;
    }

    TreeNode result = helper(root, one, two);
    if (oneFound == true && twoFound == true){
      return result;
    }else{
      return null;
    }
  }

  private TreeNode helper(TreeNode root, TreeNode one, TreeNode two){
    if (root == null){
      return root;
    }

    TreeNode left = helper(root.left, one, two);
    TreeNode right = helper(root.right, one, two);

    if (root == one){
      oneFound = true;
      return root;
    }

    if (root == two){
      twoFound = true;
      return root;
    }

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
// SC: O(n)
```

