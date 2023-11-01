# 156 Binary Tree Upside Down

Given the `root` of a binary tree, turn the tree upside down and return *the new root*.

You can turn a binary tree upside down with the following steps:

1. The original left child becomes the new root.
2. The original root becomes the new right child.
3. The original right child becomes the new left child.

![img](./156 Binary Tree Upside Down.assets/main.jpg)

The mentioned steps are done level by level. It is **guaranteed** that every right node has a sibling (a left node with the same parent) and has no children.

 

**Example 1:**

![img](./156 Binary Tree Upside Down.assets/updown.jpg)

```
Input: root = [1,2,3,4,5]
Output: [4,5,2,null,null,3,1]
```

**Example 2:**

```
Input: root = []
Output: []
```

**Example 3:**

```
Input: root = [1]
Output: [1]
```

<img src="./156 Binary Tree Upside Down.assets/Screenshot 2023-07-23 at 18.47.31.png" alt="Screenshot 2023-07-23 at 18.47.31" style="zoom:50%;" />

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case 
        if (root == null || root.left == null){
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }

}

// TC: O(n)
// SC: O(n)
```

