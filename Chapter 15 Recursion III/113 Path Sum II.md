# 113 Path Sum II

Given the `root` of a binary tree and an integer `targetSum`, return *all **root-to-leaf** paths where the sum of the node values in the path equals* `targetSum`*. Each path should be returned as a list of the node **values**, not node references*.

A **root-to-leaf** path is a path starting from the root and ending at any leaf node. A **leaf** is a node with no children.

 

**Example 1:**

![img](./113 Path Sum II/pathsumii1.jpg)

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
```

**Example 2:**

![img](./113 Path Sum II/pathsum2-20231211231213539.jpg)

```
Input: root = [1,2,3], targetSum = 5
Output: []
```

**Example 3:**

```
Input: root = [1,2], targetSum = 0
Output: []
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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // base case
        if (root == null){
            return result;
        }

        List<Integer> path = new ArrayList<Integer>();

        if (root.left == null && root.right == null && root.val == targetSum){
            path.add(root.val);
            result.add(new ArrayList<Integer>(path));
            return result;
        }

        helper(root, path, targetSum, result);
        return result;
    }

    private static void helper(TreeNode root, List<Integer> path, int targetSum, List<List<Integer>> result){
        if (root == null){
            return;
        }

        path.add(root.val);
        targetSum = targetSum - root.val;

        if (root.left == null && root.right == null && targetSum == 0){
            result.add(new ArrayList<Integer>(path));
        }else{
            helper(root.left, path, targetSum, result);
            helper(root.right, path, targetSum, result);
        }
        path.remove(path.size()-1);
        targetSum = targetSum + root.val;
        return;
        
    }
}


// TC: O(n)
// SC: O(n)
```

