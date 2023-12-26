# 230 Kth Smallest Element in a BST

Given the `root` of a binary search tree, and an integer `k`, return *the* `kth` *smallest value (**1-indexed**) of all the values of the nodes in the tree*.

 

**Example 1:**

![img](./230 Kth Smallest Element in a BST/kthtree1.jpg)

```
Input: root = [3,1,4,null,2], k = 1
Output: 1
```

**Example 2:**

![img](./230 Kth Smallest Element in a BST/kthtree2.jpg)

```
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
```

 

**Solution**:

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
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); // max heap 初始化为最大堆
        helper(root, k, pq);
        return pq.poll(); 
    }

    private void helper(TreeNode root, int k, PriorityQueue<Integer> pq){
        if (root == null){
            return;
        }
        
      if (pq.size() < k){ // If size is not k yet then add any element
            pq.add(root.val);
        }else if (root.val < pq.peek()){
            pq.poll();  // pop the top
            pq.add(root.val); // add cur value to pq
        }

        helper(root.left, k, pq);
        helper(root.right, k, pq);
        return;
    }
}

//Time complexity: O(n) + O(log(n)) = O(n)
//Space complexity: O(n)
```

