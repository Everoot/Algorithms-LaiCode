# 94 Binary Tree Inorder Traversal (recursive)

Given the `root` of a binary tree, return *the inorder traversal of its nodes' values*.

 

**Example 1:**

![img](./94 Binary Tree Inorder Traversal (recursive).assets/inorder_1-20230523005712238.jpg)

```
Input: root = [1,null,2,3]
Output: [1,3,2]
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
        
    }

    public static void helper(TreeNode root, List<Integer> result){
        if (root == null){
            return;
        }

        helper(root.left, result);
        result.add(root.val);
        helper(root.right, result);
        return;
    }
}

/* 

        1 
            \
            2 
            /
            3

        inorder      left, root, right
                      

        root.left 

        root 

        root.right
*/
```

