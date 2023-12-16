# 114 Flatten Binary Tree to Linked List

Given the `root` of a binary tree, flatten the tree into a "linked list":

- The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`.
- The "linked list" should be in the same order as a [**pre-order** **traversal**](https://en.wikipedia.org/wiki/Tree_traversal#Pre-order,_NLR) of the binary tree.

 

**Example 1:**

![img](./114 Flatten Binary Tree to Linked List/flaten.jpg)

```
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
```

**Example 2:**

```
Input: root = []
Output: []
```

**Example 3:**

```
Input: root = [0]
Output: [0]
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
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }

        TreeNode[] prev = new TreeNode[1];
        helper(root, prev);
        return;
    }

    private static void helper(TreeNode root, TreeNode[] prev){
        if (root == null){
            return;
        }

        TreeNode leftNode = root.left; // because leftChild can be changed
        TreeNode rightNode = root.right; 


        if (prev[0] != null){ //if: root is the 1st node to visit
            prev[0].right = root;
        }

        root.left = null; // OR = prev[0] if we need to make it a doubly linked list
        prev[0] = root;

        helper(leftNode, prev);
        helper(rightNode, prev);
    }
}
// TC: O(n)
// SC: O(n)
```

