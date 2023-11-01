#  1644 Lowest Common Ancestor of a Binary Tree II

Given the `root` of a binary tree, return *the lowest common ancestor (LCA) of two given nodes,* `p` *and* `q`. If either node `p` or `q` **does not exist** in the tree, return `null`. All values of the nodes in the tree are **unique**.

According to the **[definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor)**: "The lowest common ancestor of two nodes `p` and `q` in a binary tree `T` is the lowest node that has both `p` and `q` as **descendants** (where we allow **a node to be a descendant of itself**)". A **descendant** of a node `x` is a node `y` that is on the path from node `x` to some leaf node.

 

**Example 1:**

![img](./1644 Lowest Common Ancestor of a Binary Tree II.assets/binarytree-20230725182610507.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**

![img](./1644 Lowest Common Ancestor of a Binary Tree II.assets/binarytree-20230725182610507.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
```

**Example 3:**

![img](./1644 Lowest Common Ancestor of a Binary Tree II.assets/binarytree-20230725182610507.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.
```



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
    boolean pFound = false;
    boolean qFound = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null){
          return null;
        }

        TreeNode result = helper(root, p, q);
        if (pFound == true && qFound == true){
          return result;
        }else{
          return null;
        }
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
      // base case
      if (root == null){
        return null;
      }

      TreeNode left = helper(root.left, p, q);
      TreeNode right = helper(root.right, p, q);
      
      if (root == q || root == p){
        if (root == q){
          pFound = true;
        }else{
          qFound = true;
        }
        return root;
      }

      // checks
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

