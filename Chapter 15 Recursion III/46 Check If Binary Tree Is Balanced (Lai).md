# 46 Check If Binary Tree Is Balanced (Lai)

Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.

**Examples**

​    5

   /   \

  3     8

 /  \     \

1    4     11

is balanced binary tree,

​    5

   /

  3

 /  \

1    4

is not balanced binary tree.

**Corner Cases**

- What if the binary tree is null? Return true in this case.

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



**Solution:**

What's the definition of "balanced"? It could be:

* the tree has a minimum possible overall height
* no leaf is too further away, i.e. 0 or 1, from root than any other leaf
* left and right sub-trees have similar height, i.e. difference is 0 or 1 (balanced height)



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
  public boolean isBalanced(TreeNode root) {
    // Write your solution here
    // base case 
    if (root == null){
      return true;
    }

    int leftHeight = getHeight(root.left);
    int rightHeight = getHeight(root.right);
    int diff = Math.abs(leftHeight - rightHeight);
    if (diff > 1){
      return false;
    }

    return isBalanced(root.left) && isBalanced(root.right);
  }

  private static int getHeight(TreeNode root){
    if (root == null){
      return 0;
    }

    return Math.max(getHeight(root.left), getHeight(root.right))+1;
  }
}
// TC: O(nlogn)
// SC: O(n)
```



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
  public boolean isBalanced(TreeNode root) {
    // Write your solution here
    // base case 
    if (root == null){
      return true;
    }
    // -1 means not balaced return false.
    return getHeight(root) != -1;  // -1 != -1 -> false 
  }

  private static int getHeight(TreeNode root){
    if (root == null){
      return 0;
    }
    int leftHeight = getHeight(root.left);
    if (leftHeight == -1){
      return -1;
    }
    int rightHeight = getHeight(root.right);
    if (rightHeight == -1){
      return -1;
    }
    int diff = Math.abs(leftHeight - rightHeight);
    if (diff > 1){
      return -1;
    }

    return Math.max(getHeight(root.left), getHeight(root.right))+1;
  }
}
// TC: O(nlogn).    = (n nodes )(logn) -> O(n)
// SC: O(n)
```

