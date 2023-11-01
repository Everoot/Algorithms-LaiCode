# 124 Binary Tree Maximum Path Sum

A **path** in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence **at most once**. Note that the path does not need to pass through the root.

The **path sum** of a path is the sum of the node's values in the path.

Given the `root` of a binary tree, return *the maximum **path sum** of any **non-empty** path*.

 

**Example 1:**

![img](./124 Binary Tree Maximum Path Sum.assets/exx1.jpg)

```
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
```

**Example 2:**

![img](./124 Binary Tree Maximum Path Sum.assets/exx2.jpg)

**Solution:**

```
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
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
 1. What do you expect from your lchild/ rchild? (usually it is the return type of the recursion function)
   (1) Left: 直上直下的path 一撇
   (2) Right: 直上直下的path 一捺
2. What do you want to do in the current layer?
   sum of 人字形path = left的一撇(1) + Right的一捺(2) + root.value
   update the global max if necessary
   注意要处理当(1) or (2) 为负数的情况, 负数不如不要
3. What do you want to report to your parent? (same as Q1 == Q3)
   root.key + Math.max(left, right);
   OR
   root.key + left + right;
   注意!!! report也必须是直上直下的路径
 */
class Solution {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        if (root == null){
            return maxSum;
        }

        helper(root);
        return maxSum;
    }

    private int helper(TreeNode root){
        // base case 
        if (root == null){
            return 0;
        }

        // recursive rule
      	// step1
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right),0);
				// step2
        int cur = left + right + root.val;
        maxSum = Math.max(maxSum, cur); // 人字形path
				
      	// step3
        return Math.max(left + root.val, right + root.val);
    }
}
```

