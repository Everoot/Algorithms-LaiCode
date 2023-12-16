# 211 Reconstruct Binary Search Tree With Postorder Traversal (Lai)

Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

**Assumptions**

- The given sequence is not null
- There are no duplicate keys in the binary search tree

**Examples**

postorder traversal = {1, 4, 3, 11, 8, 5}

the corresponding binary search tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use the pre order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, #, #, 3, 4, #, #, #] represents the following binary tree:

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
  public TreeNode reconstruct(int[] post) {
    // Write your solution here

    if (post.length == 1){
      return new TreeNode(post[0]);
    }

    int postLeft = 0;
    int postRight = post.length -1;
    return helper(post, postLeft, postRight);
  }

  private TreeNode helper(int[] post, int postLeft, int postRight){
    if (postLeft > postRight){
      return null;
    }

    TreeNode root = new TreeNode(post[postRight]);
    int target = -1;
    for (int i = postRight; i >= 0; i--){
      if (post[i] < post[postRight]){
        target = i;
        break;
      }
    }

    root.left = helper(post, postLeft, target);
    root.right = helper(post, target+1, postRight-1);
    return root;
  }
}

/*
         i
  {1, 4, 3, 11, 8, 5}
   l               r
   l     t            -> find which is samller than 5 left part
            t+1 r-1    right part

   1.   4    3
   l         r
   t    t+1
   l(r) t+1 (r-1)

   take careful if target == postleft 
   never stop     root.left = helper(post, postLeft, target);
   so target should initial target =-1;

   // TC: O(n^2)
   // SC: O(n)

*/
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
  public TreeNode reconstruct(int[] post) {
    // Write your solution here
    // Assumptions: post is not null
    // there is no duplicate in the binary search tree
    // traverse and construct the binary search tree
    // from the postOrder right to left
    int[] index = new int[] {post.length - 1};
    // index {6}
    return helper(post, index, Integer.MIN_VALUE);
  }

  private TreeNode helper(int[] postorder, int[] index, int min){
    // Since it is a binary search tree,
    // the "min" is actually the root,
    // and we are using the root value to determine the boundary
    // of left/right subtree
    if (index[0] < 0 || postorder[index[0]] <= min){
      return null;
    }

    TreeNode root = new TreeNode(postorder[index[0]--]);
    
    root.right = helper(postorder, index, root.key);
    root.left = helper(postorder, index, min);
    return root;
  }
}

// TC: O(n)
// SC: O(n)
```

