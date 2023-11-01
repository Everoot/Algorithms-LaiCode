# 144 Binary Tree Preorder Traversal (recursive)

Given the `root` of a binary tree, return *the preorder traversal of its nodes' values*.

 

**Example 1:**

![img](./144 Binary Tree Preorder Traversal (recursive).assets/inorder_1.jpg)

```
Input: root = [1,null,2,3]
Output: [1,2,3]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
        
    }

    public static void helper(TreeNode root, List<Integer> result){
        if (root == null){
            return;
        }

        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);
        return;
    }
}

/* 

 postorder
 root.left
 root.right
 root

*/
```

