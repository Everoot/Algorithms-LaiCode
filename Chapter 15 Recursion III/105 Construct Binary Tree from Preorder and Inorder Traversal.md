# 105 Construct Binary Tree from Preorder and Inorder Traversal

Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return *the binary tree*.

 

**Example 1:**

![img](./105 Construct Binary Tree from Preorder and Inorder Traversal/tree.jpg)

```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
```

**Example 2:**

```
Input: preorder = [-1], inorder = [-1]
Output: [-1]
```



**Solution:** 

想要独一无二地还原，必须要有in-order

Given PreOrder      root left right

​			abc 		 ->         preOrderArray left right     preOrder[left] -> root

Given PostOrder 	left right root  (root最后去的)

​			cba                       preOrderArray left right    postOrder[right] -> root



=>

​	       	a

​		   /           \

​                b                c

​										 left   right

preOrder [a, b, c]          [0, preOrder.length-1]   ->   preOrder[0] = preOrder[left]

postOrder [b, c, a]		[0, postOrder.length-1] -> postOrder[postOrder.length -1] = postOrder[right]



preorder = [==3==,9,20,15,7] 

​		      l                r



root= preorder[left]

​		  0 1  2   3   4

inorder = [9,==3==,15,20,7]

​                   l                r



hashmap: <9,0> <3,1> <15,2>, <20, 3>, <7,4>

-> indexofMid = 1

root.left = 1-1, 

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
    // Mehthod 1: Utilizing the inOrder sequence to determine
    // the size of left/right subtrees.
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // base case
        if (preorder == null || preorder.length == 0){
            return null;
        }

        if (preorder.length == 1){
            return new TreeNode(preorder[0]);
        }
        // Assumptions: pre, in are not null, there is no duplicates
        // in the binary tree, the length of pre and in are guaranteed
        // to be the same
        Map<Integer, Integer> inIndex = indexMap(inorder);
        return helper(preorder, inIndex, 0, inorder.length - 1, 0, preorder.length -1);
    }

    private Map<Integer, Integer> indexMap(int[] in){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++){
            map.put(in[i], i);
        }
        return map;
    }

    private TreeNode helper(int[] pre, Map<Integer, Integer> inIndex, int inLeft, int inRight, int preLeft, int preRight){
        if (inLeft > inRight){
            return null;
        }

        TreeNode root = new TreeNode(pre[preLeft]);
        // get the position of the root in inOrder sequence, so that we will know
        // the size of left/right subtrees.

        int inMid = inIndex.get(root.val);

        root.left = helper(pre, inIndex, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
        root.right = helper(pre, inIndex, inMid + 1, inRight, preLeft + inMid - inLeft + 1, preRight);
        return root;
    }
}

// TC: O(n)
// SC: O(n)
```



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
  // Method 2: Another Linear Solution with traversing and constructing
  // the binary tree using preOrder and inOrder at the same time
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      // Assumptions: pre, in are not null, there is no duplicates
      // in the binary tree, the length of pre and in are guaranteed
      // to be the same.
        int[] preIndex = new int[]{0};
        int[] inIndex = new int[]{0};
        return helper(preorder, inorder, preIndex, inIndex, Integer.MAX_VALUE); 
    }


    private TreeNode helper(int[] pre, int[] in, int[] preIndex, int[] inIndex, int target){
        if (inIndex[0] >= in.length || in[inIndex[0]] == target){
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex[0]]);

        preIndex[0]++;
        root.left = helper(pre, in, preIndex, inIndex, root.val);

        inIndex[0]++;
        root.right = helper(pre, in, preIndex, inIndex, target);
        return root;
    }
}
```



