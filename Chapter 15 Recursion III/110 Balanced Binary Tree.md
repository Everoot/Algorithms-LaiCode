# 110 Balanced Binary Tree

Given a binary tree, determine if it is **height-balanced**.

**Example 1:**

![img](./110 Balanced Binary Tree/balance_1.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: true
```

**Example 2:**

![img](./110 Balanced Binary Tree/balance_2.jpg)

```
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
```

**Example 3:**

```
Input: root = []
Output: true
```



**Solution1:**

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
    public boolean isBalanced(TreeNode root) { // n = 2^? =>  nlogn
        // base case 
        if (root == null){
            return true;
        }

        int diff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(diff) > 1){
            return false;
        }

        return (isBalanced(root.left) && isBalanced(root.right));
    }

    private static int getHeight(TreeNode root){    // O(n)
        if (root == null){
            return 0;
        }

        return (Math.max(getHeight(root.left), getHeight(root.right)) + 1);
    }
}


// TC: O(nlogn)
// SC: O(n)
```



**Solution2:** 

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
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        // use -1 to denote the tree is not balanced
        // >= 0 value means the tree is balanced and it is the height of the tree.
        return height(root) != -1;
    }

    private static int height(TreeNode root){
        if (root == null){
            return 0;
        }

        int leftHeight = height(root.left);
        // if left subtree is already not balanced, we do not need to continue
        // and we can return -1 directly.
        if (leftHeight == -1){
            return -1;
        }

        int rightHeight = height(root.right);
        if (rightHeight == -1){
            return -1;
        }

        // if not balanced, return -1;
        if (Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }

        return Math.max(leftHeight, rightHeight)+1;

    }
}

// Time: O(n)
// Space: O(height)
```

