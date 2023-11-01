# 272 Closest Binary Search Tree Value II

Given the `root` of a binary search tree, a `target` value, and an integer `k`, return *the* `k` *values in the BST that are closest to the* `target`. You may return the answer in **any order**.

You are **guaranteed** to have only one unique set of `k` values in the BST that are closest to the `target`.

 

**Example 1:**

![img](./272 Closest Binary Search Tree Value II.assets/closest1-1-tree-20230903154934339.jpg)

```
Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]
```

**Example 2:**

```
Input: root = [1], target = 0.000000, k = 1
Output: [1]
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // list created to store our result
        List<Integer> result = new ArrayList<>();
        // solving with dfs
        helper(result, root, target, k);
        // return result
        return result;
    }

    private static void helper(List<Integer> result, TreeNode root, double target, int k){
        // base case 
        // if there is not root, don't return anything
        if (root == null) {
            return;
        }

        // lets start with dfs on the left side
        helper(result, root.left, target, k);

        // if our list has size smaller than k, we add one into it
        if (result.size() < k){
            result.add(root.val);
        }else {
            // if the list if full, result.size = k, wer remove the one with largest abs value
            if (Math.abs(target - root.val) < Math.abs(target - result.get(0))){
                // remove first one 
                result.remove(0);
                // add a new one to compare
                result.add(root.val);
            }
        }
        
        // after we did left side, we will do the right side and repeat all steps
        helper(result, root.right, target, k);
    }
}
// TC: O(n)
// SC: O(n)
```

