# 106 Construct Binary Tree from Inorder and Postorder Traversal

Given two integer arrays `inorder` and `postorder` where `inorder` is the inorder traversal of a binary tree and `postorder` is the postorder traversal of the same tree, construct and return *the binary tree*.

 

**Example 1:**

![img](./106 Construct Binary Tree from Inorder and Postorder Traversal/tree.jpg)

```
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
```

**Example 2:**

```
Input: inorder = [-1], postorder = [-1]
Output: [-1]
```



**Solution**

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // base case 
        if (inorder == null || inorder.length == 0){
            return null;
        }

        if (inorder.length == 1){
            return new TreeNode(inorder[0]);
        }

        Map<Integer, Integer> inIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++){
            inIndex.put(inorder[i], i);
        }

        int inLeft = 0;
        int inRight= inorder.length -1;
        int postLeft = 0;
        int postRight = postorder.length -1;
        return helper(postorder, inIndex, inLeft, inRight, postLeft, postRight);
    }

    private TreeNode helper(int[] postorder, Map<Integer, Integer> inIndex, int inLeft, int inRight, int postLeft, int postRight){
        if (inLeft > inRight){
            return null;
        }

        TreeNode root = new TreeNode(postorder[postRight]);
        
        int inMid = inIndex.get(postorder[postRight]);

        root.left = helper(postorder, inIndex, inLeft, inMid-1, postLeft, postLeft + (inMid-inLeft) -1);
        root.right = helper(postorder, inIndex, inMid+1, inRight, postLeft+(inMid-inLeft) , postRight-1);
        return root;
    }
}


/*

postorder = [9,15,7,20, 3]
             l          r
left part    l l+(m-l)-1
right part     l+(m-1) r-1

inMid = 
inorder = [9,3,15,20,7], 
           l m       r
left part  l(m-1)
right part     m+1    r
Map<Integer, Integer> 

*/
```

