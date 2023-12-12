# 639 Max Path Sum From Leaf To Root (Lai)

Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.

**Assumptions**

The root of given binary tree is not null.

**Examples**



​     10

​    /   \

  -2    7

 /   \

8   -4

The maximum path sum is 10 + 7 = 17.



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
  public int maxPathSumLeafToRoot(TreeNode root) {
    // Write your solution here
    if (root == null){
      return 0;
    }

    if (root.left == null && root.right == null){
      return root.key;
    }

    int sum = 0;
    sum = helper(root, sum);
    return sum;
  }

  private static int helper(TreeNode root, int sum){
    // Base case
    sum = root.key + sum;
    if (root.left == null && root.right == null){
      return sum;
    }

    if (root.left == null){
      return helper(root.right, sum);
    }

    if (root.right == null){
      return helper(root.left, sum);
    }

    return Math.max(helper(root.left, sum),helper(root.right, sum));
  }
}

// TC: O(n)
// SC: O(n)
```

