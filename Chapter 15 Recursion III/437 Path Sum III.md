# 437 Path Sum III

Given the `root` of a binary tree and an integer `targetSum`, return *the number of paths where the sum of the values along the path equals* `targetSum`.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

 

**Example 1:**

![img](./437 Path Sum III/pathsum3-1-tree-20231212152910203.jpg)

```
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
```



**Example 2:**

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
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
   int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return 0;
        }

        if (root.left == null && root.right == null){
            return 
        }
        ArrayList<Integer> path = new ArrayList<>();        
        helper(root,path, targetSum);
        return count;
    }
    public void helper(TreeNode root, ArrayList<Integer> path, int target){
        if(root == null) {
            return;
        }
        path.add(root.val);
        double sum = 0;
        // [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]
        for(int i = path.size() - 1 ; i >= 0 ; i--){
            sum = sum + path.get(i);
            if(sum == target){
                count++;
            } 
        }
        helper(root.left,path, target);
        helper(root.right,path, target);
        path.remove(path.size()-1);
    }
}

// TC:O(n^2)

// SC:O(n)
```



prefixSum

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
    int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        // base case
        if (root == null){
            return 0;
        }

        if (root.left == null && root.right == null){
            if (root.val == targetSum){
                return 1;
            }else{
                return 0;
            }
        }

        Map<Integer, Integer> path = new HashMap<Integer, Integer>();
        //List<Integer> path = new ArrayList<Integer>();
        int sum = 0;
        path.put(sum, 1);
        helper(root, targetSum, path, sum);
        return count;
        
    }

    private void helper(TreeNode root, int targetSum, Map<Integer, Integer> path, int sum){
        if (root == null){
            return;
        }

        sum = sum + root.val;
        
        count = count + path.getOrDefault(sum - targetSum, 0);

        path.put(sum, path.getOrDefault(sum,0)+1);

        helper(root.left, targetSum, path, sum);
        helper(root.right, targetSum, path, sum);

        path.put(sum, path.getOrDefault(sum,0)-1);

        return;
    }
}

// TC:O(n)
// SC:O(n)
```

