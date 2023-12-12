# 124 Binary Tree Maximum Path Sum

A **path** in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence **at most once**. Note that the path does not need to pass through the root.

The **path sum** of a path is the sum of the node's values in the path.

Given the `root` of a binary tree, return *the maximum **path sum** of any **non-empty** path*.

 

**Example 1:**

![img](./124 Binary Tree Maximum Path Sum/exx1.jpg)

```
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
```

**Example 2:**

![img](./124 Binary Tree Maximum Path Sum/exx2.jpg)

```
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
```



**Solutions:**

​	100

​	/    \

​     -1      -1

对比: from one leaf node to another leaf 小也得要. 98

any node to any node  能拐弯, 没必要到leaf 没限制 100

1. 读懂题 题目允不允许拐弯(人字形)
2. 必须拐弯吗

```java
// 允许拐弯
// 非必需

**
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
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right),0);
				
        // compare 
        int cur = left + right + root.val;
      	// update
        maxSum = Math.max(maxSum, cur);

        return Math.max(left + root.val, right + root.val);
    }
}

// TC: O(N)
// SC: O(N)
```

any to any
