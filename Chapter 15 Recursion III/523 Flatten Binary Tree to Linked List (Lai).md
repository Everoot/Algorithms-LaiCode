# 523 Flatten Binary Tree to Linked List (Lai)

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

```
         1
        / \
       2   5
      / \   \
     3   4   6
```



The flattened tree should look like:

```
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
```



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
  public TreeNode flatten(TreeNode root) {
    // Write your solution here
    if (root == null){
      return root;
    }

    TreeNode[] prev = new TreeNode[1];
    helper(root, prev);
    return root;
  }

  private static void helper(TreeNode root, TreeNode[] prev){
    if (root == null){
      return;
    }

    TreeNode left = root.left;
    TreeNode right = root.right;

    if (prev[0] != null){
      prev[0].right = root; 
    }

    prev[0] = root;

    root.left = null;

    helper(left, prev);
    helper(right, prev);
    return;
  }
}

// TC: O(n)
// SC: O(n)
```

