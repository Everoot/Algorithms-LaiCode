---
tags:
    - Tree
    - Breadth-First Search	
    - Binary Tree
---



# 102 Binary Tree Level Order Traversal

Given the `root` of a binary tree, return *the level order traversal of its nodes' values*. (i.e., from left to right, level by level).

**Example 1:**

![img](./102 Binary Tree Level Order Traversal/tree1.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

**Example 2:**

```
Input: root = [1]
Output: [[1]]
```

**Example 3:**

```
Input: root = []
Output: []
```



**Solution:**

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null){
            return result;
        }

        int index = 0;
        DFS(root, index, result);

        return result;
        
    }

    private void DFS(TreeNode root, int index, List<List<Integer>> result){
        // base case
        if (root == null){
            return;
        }

        if (index >= result.size()){
            result.add(new ArrayList<Integer>());
        }

        result.get(index).add(root.val);
        DFS(root.left, index + 1, result);
        DFS(root.right, index +1 , result);
    }
}

// TC: O(n)
// SC: O(n)
```

