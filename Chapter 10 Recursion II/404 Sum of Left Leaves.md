# 404 Sum of Left Leaves

Given the `root` of a binary tree, return *the sum of all left leaves.*

A **leaf** is a node with no children. A **left leaf** is a leaf that is the left child of another node.

 

**Example 1:**

![img](./404 Sum of Left Leaves.assets/leftsum-tree.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
```

**Example 2:**

```
Input: root = [1]
Output: 0
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
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        // base case
        if (root == null || (root.left == null && root.right == null)){
            return sum;
        }

        helper(root, false);
        return sum;
    }

    private void helper(TreeNode root, boolean isLeft){
        // base case
        if (root == null){
            return;
        }

        // recursion rule
        // leaf
        if (root.left == null && root.right == null){
            if (isLeft == true){
                sum = sum + root.val;
            }
        }

        helper(root.left, true);
        helper(root.right, false);
    }
}


/*
    1. check whether it is left
    2. sum
    // TC: O(n)
    SC: O(n)
*/
```

