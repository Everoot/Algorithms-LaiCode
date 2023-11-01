# 110 Balanced Binary Tree

Given a binary tree, determine if it is 

**height-balanced**

.



 

**Example 1:**

![img](./110 Balanced Binary Tree.assets/balance_1.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: true
```

**Example 2:**

![img](./110 Balanced Binary Tree.assets/balance_2.jpg)

```
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
```

**Example 3:**

```
Input: root = []
Output: true
```



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
        // base case
        if (root == null){
            return true;
        }

        // subproblem Result
        boolean leftSubtreeIsBalanced  = isBalanced(root.left);
        boolean rightSubtreeIsBalanced = isBalanced(root.right);

        if (!leftSubtreeIsBalanced || !rightSubtreeIsBalanced){
            return false;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1){
            return false;
        }

        return true;
        
    }

    private static int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return (Math.max(left,right)+1);
    }
}
```



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

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (Math.abs(left - right) > 1){
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }
        
        int left = getHeight(root.left) +1;
        int right = getHeight(root.right)+1;
        return Math.max(left, right);
    }
}

/*
        T
       / \
       2  1
      /\ 
      2 1

      nlog(n) 
*/
```

