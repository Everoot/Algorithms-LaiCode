#  653 Two Sum IV - Input is a BST

Given the `root` of a binary search tree and an integer `k`, return `true` *if there exist two elements in the BST such that their sum is equal to* `k`, *or* `false` *otherwise*.

 

**Example 1:**

![img](./653 Two Sum IV - Input is a BST/sum_tree_1.jpg)

```
Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
```

**Example 2:**

![img](./653 Two Sum IV - Input is a BST/sum_tree_2.jpg)

```
Input: root = [5,3,6,2,4,null,7], k = 28
Output: false
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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<Integer>();
        return helper(root, k, set);
    }

    private boolean helper(TreeNode root, int k, Set<Integer> set){
        if (root == null){
            return false;
        }

        if (set.contains(k - root.val)){
            return true;
        }

        set.add(root.val);
        
        return helper(root.left, k , set) || helper(root.right, k, set);
    }
}

// TC:O(n)

// SC:O(n)
```

