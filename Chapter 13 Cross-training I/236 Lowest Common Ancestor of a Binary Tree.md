# 236 Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): “The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow **a node to be a descendant of itself**).”

 

**Example 1:**

![img](./236 Lowest Common Ancestor of a Binary Tree.assets/binarytree.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**

![img](./236 Lowest Common Ancestor of a Binary Tree.assets/binarytree.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

**Example 3:**

```
Input: root = [1,2], p = 1, q = 2
Output: 1
```

 

**Solution:**

Core: a or b 在不在这个树上

LCA(root, a, b): LCA of notes in a set {a, b} that are under root

Case 1: both a and b are under root, return LCA of a and b

Case 2: if one of a or b is under root, return a or b

Case 3: if neither a nor b is under root, return null



Solution:

case1: left == null && right == null -> return null

case2: left == null || right == null -> return not null side

case3: left != null && right != null -> return root



base case1: root == null -> return null

base case2: root == a || root == b -> return root



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
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null){
            return null;
        }

        if (root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null){
            return root;
        }

        if (left == null && right == null){
            return null;
        }

        if (left != null){
            return left;
        }else{
            return right;
        }
    }
}

// TC: O(n)

// SC: O(n)
```



**Binary Tree**: In a general binary tree, the problem requires a slightly more complex solution because there are no guaranteed properties about the values or structure beyond the tree being binary. The given problem (236) is about finding the LCA in such a tree.