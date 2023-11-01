# 270 Closest Binary Search Tree Value

Given the `root` of a binary search tree and a `target` value, return *the value in the BST that is closest to the* `target`. If there are multiple answers, print the smallest.

 

**Example 1:**

![img](./270 Closest Binary Search Tree Value.assets/closest1-1-tree.jpg)

```
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
```

**Example 2:**

```
Input: root = [1], target = 4.428571
Output: 1
```



**Solution:**

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
    public int closestValue(TreeNode root, double target) {
       // base case 
       // Assumption root != null

       double result = root.val;
       while(root != null){
         if (root.val == target){
           result = root.val;
           return (int) result;
         }else{
           if (Math.abs(result - target) > Math.abs(root.val - target)){
              result = root.val;
            }

           if (Math.abs(result - target) == Math.abs(root.val - target)){
              result = Math.min(root.val, result);
          }

           if (root.val < target){
            root = root.right;
          }else{
            root = root.left;
          }
        }
 
      }
       
      return (int) result;
    }
}

// TC:O(n) - O(logn)
// SC:O(1)
```

