# 53 Delete In Binary Search Tree (Lai)

Delete the target key K in the given binary search tree if the binary search tree contains K. Return the root of the binary search tree.

Find your own way to delete the node from the binary search tree, after deletion the binary search tree's property should be maintained.

**Assumptions**

- There are no duplicate keys in the binary search tree

  The smallest larger node is first candidate after deletion



Solution:

```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public TreeNode deleteTree(TreeNode root, int key) {
    // Write your solution here
    if (root == null){
      return root;
    }

    if (root.key == key){
      if (root.left == null && root.right == null){
        return null;
      }else if (root.left == null){
        return root.right;
      }else if (root.right == null){
        return root.left;
      }else {
        // root.left != null && root.right != null
        // The smallest larger node is first candidate after deletion
        // find the right subtree smallest as root
        if (root.right.left == null){
          root.right.left = root.left;
          return root.right;
        }else{
          // root.right.left != null
          TreeNode newRoot = smallest(root.right);
          newRoot.left = root.left;
          newRoot.right = root.right;
          return newRoot;
        }

      }

    }else{
      // root.key != key
      if (root.key < key){
        root.right = deleteTree(root.right, key);
      }else if (root.key > key){
        root.left = deleteTree(root.left, key);
      }
    }
    return root;
  }

  private TreeNode smallest(TreeNode root){
    TreeNode cur = root;
    TreeNode pre = null;
    while(cur.left != null){
      pre = cur;
      cur = cur.left;
    }

    TreeNode smallest = cur;
    pre.left = cur.right;
    return smallest;
  }
}
// TC: O(logn), O(n)                 2 ^ x = n   x = logn
// SC: O(n)
/*
        8                   7 
      /   \        ->         \          root.left.right = root.right 
      7    9                  9 


         8                             9
        /   \                        /    \
      4      10       ->            4     10
     / \    / \                    / \    /  \
    1   5  9   11                 1  5   9.9   11
          /  \9.9
         8.5
          \8.6
     
/* The smallest larger node is first candidate after deletion*/


```

