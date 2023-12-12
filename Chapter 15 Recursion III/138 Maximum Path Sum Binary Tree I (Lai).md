# 138 Maximum Path Sum Binary Tree I (Lai)

Given a binary tree in which each node contains an integer number. Find the maximum possible sum **from one leaf node to another leaf node.** If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

**Examples**

 -15

 /   \

2    11

   /   \

  6   14

The maximum path sum is 6 + 11 + 14 = 31.

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
  private int max = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
    // Write your solution here
    helper(root);
    return max;
  }

  private int helper(TreeNode root){
    // base case 
    if (root == null){
      return 0;
    }
    // Find maximum sum in left and right subtree. Also find 
    // maximum root to leaf sums in left and right subtrees
    // and store them in left and right 

    // step1:
    int left = helper(root.left);
    int right = helper(root.right);

    // find the maxium path sum passing through root
    // cur sum
    int cur = left + right + root.key;

    // update result (or result) only when needed
    if (max < cur && root.left != null && root.right != null){ 
      // from one leaf node to another leaf node
      max = cur;
    }
    
    if (root.left == null && root.right == null){  // step 3 
      return root.key;
    }
    
    if (root.left == null){
      return right + root.key;
    }

    if (root.right == null){
      return left + root.key;
    }

    // root.left != null && root.right != null
    return Math.max(left, right) + root.key;
  }
}

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
  public int maxPathSum(TreeNode root) {
    // Write your solution here
    int[] result = new int[]{Integer.MIN_VALUE};
    helper(root, result);
    return result[0];
  }

  private static int helper(TreeNode root, int[] result){
    // base case 
    if (root == null){
      return 0;
    }
    // Find maximum sum in left and right subtree. Also find 
    // maximum root to leaf sums in left and right subtrees
    // and store them in left and right 

    // step1:
    int left = helper(root.left, result);
    int right = helper(root.right, result);

    // find the maxium path sum passing through root
    // cur sum
    int cur = left + right + root.key;

    // update result (or result) only when needed
    if (result[0] < cur && root.left != null && root.right != null){ 
      // from one leaf node to another leaf node
      result[0] = cur;
    }
    
    if (root.left == null && root.right == null){  // step 3 
      return root.key;
    }
    
    if (root.left == null){
      return right + root.key;
    }

    if (root.right == null){
      return left + root.key;
    }

    // root.left != null && root.right != null
    return Math.max(left, right) + root.key;
  }
}

```

