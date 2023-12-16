# 213 Reconstruct Binary Tree With Preorder And Inorder (Lai)

Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

**Assumptions**

- The given sequences are not null and they have the same length
- There are no duplicate keys in the binary tree

**Examples**

preorder traversal = {5, 3, 1, 4, 8, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use the pre order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



**Solution**

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
  public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
    // Write your solution here
    // base case 
    if (inOrder.length == 1){
      return new TreeNode(inOrder[0]);
    }

    Map<Integer, Integer> inIndex = new HashMap<Integer, Integer>();
    for (int i = 0; i < inOrder.length; i++){
      inIndex.put(inOrder[i], i);
    }

    int inLeft = 0;
    int inRight = inOrder.length - 1;

    int preLeft = 0;
    int preRight = preOrder.length - 1;

    return helper(preOrder, inIndex, inLeft, inRight, preLeft, preRight);
  }

  private TreeNode helper(int[] preOrder, Map<Integer, Integer> inIndex, int inLeft, int inRight, int preLeft, int preRight){
    if (inLeft > inRight){
      return null;
    }

    TreeNode root = new TreeNode(preOrder[preLeft]);
    
    int inMid = inIndex.get(preOrder[preLeft]);

    root.left = helper(preOrder, inIndex, inLeft, inMid-1, preLeft +1, preLeft+1 + (inMid-1) - inLeft);
    root.right = helper(preOrder, inIndex, inMid+1, inRight, preLeft+1 + (inMid-1) - inLeft +1, preRight);
    return root;
  }
}


/*
preorder traversal = {5, 3, 1, 4, 8, 11}
                      l               r
         root.left      [l+1   l+1 + (m-1)-inl]     
         root.right               [l+1+(m-1)-inl +1, r]
                     0  1  2  3 
inorder traversal = {1, 3, 4, 5, 8, 11}
                     l        m      r
      root.left     [l     m-1]  
      root.right                [m+1  r]
Map


// TC: O(n)
// SC: O(n)



*/

```

