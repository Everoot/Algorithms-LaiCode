# 104 Maximum Depth of Binary Tree

Given the `root` of a binary tree, return *its maximum depth*.

A binary tree's **maximum depth** is the number of nodes along the longest path from the root node down to the farthest leaf node.

 

**Example 1:**

![img](./104 Maximum Depth of Binary Tree.assets/tmp-tree.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

**Example 2:**

```
Input: root = [1,null,2]
Output: 2
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
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return Math.max(left, right);
    }
}
```





**Complexity analysis**

- Time complexity : we visit each node exactly once, thus the time complexity is $O(N)$, where $N$ is the number of nodes.
- Space complexity : in the worst case, the tree is completely unbalanced, *e.g.* each node has only left child node, the recursion call would occur $N$ times (the height of the tree), therefore the storage to keep the call stack would be $O(N)$.
  But in the best case (the tree is completely balanced), the height of the tree would be $log(N)$. Therefore, the space complexity in this case would be $O(log⁡(N))$. 