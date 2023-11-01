# 235 Lowest Common Ancestor of a Binary Search Tree

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): “The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow **a node to be a descendant of itself**).”

 

**Example 1:**

![img](./235 Lowest Common Ancestor of a Binary Search Tree.assets/binarysearchtree_improved.png)

```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
```

**Example 2:**

![img](./235 Lowest Common Ancestor of a Binary Search Tree.assets/binarysearchtree_improved.png)

```
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
```

**Example 3:**

```
Input: root = [2,1], p = 2, q = 1
Output: 2
```



**Solution:**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// case 1: left == null && right == null -> return null
// case 2: left == null || right == null -> return not null side
// case 3: left != null && right != null -> return root 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       // base case 
       if (root == null){
           return root;
       } 

       // Early termination if you found any node: there's no need to  search the other node 
       // in this subtree anymore, because both cases are covered already
       // case 1: you‘ll find this only 1 node
       // case 2: you'll find both nodes, but this one must be the LCA
       if (root == p || root == q){
           return root;
       }

       // Step1: What do you expect from your lchild/rchild?  (usually it is the return type of 
       // the recursion function)
       // (1) left subtree's result of 3 cases check
       // (2) right subtree's result of 3 cases check
       TreeNode left = lowestCommonAncestor(root.left, p, q);
       TreeNode right = lowestCommonAncestor(root.right, p, q);

       // Step 2 & 3: What do you want to do in the current layer?
       // check 3 cases
       // case 3
       if (left != null && right != null){
           return root;
       }

       // case 1 & 2
       if (left == null ){
           return right;
       }else{ // right == null
           return left;
       }

    }
}
//TC: O(N)
//SC: O(N)
```

