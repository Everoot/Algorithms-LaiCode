# 102 Binary Tree Level Order Traversal

Given the `root` of a binary tree, return *the level order traversal of its nodes' values*. (i.e., from left to right, level by level).

 

**Example 1:**

![img](./102 Binary Tree Level Order Traversal.assets/tree1.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

**Example 2:**

```
Input: root = [1]
Output: [[1]]
```

**Example 3:**

```
Input: root = []
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // base case 
        if (root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // intialize
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> subResult = new ArrayList<Integer>();// cur layer
            int size = queue.size(); // level size
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                subResult.add(cur.val);
                
                if (cur.left != null){
                    queue.offer(cur.left);
                }

                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }

            result.add(subResult);

        }

        return result;




    }
}


/*
       3  <-  9 20       <-
     

*/
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
    public List<List<Integer>> levelOrder(TreeNode root) {
       List<List<Integer>> result = new ArrayList<List<Integer>>();

       if (root == null){
           return result;
       }

       Queue<TreeNode> queue = new LinkedList<>();
       queue.offer(root);
       while(!queue.isEmpty()){
           // the list storing all the nodes on the current level.
           List<Integer> curLayer = new ArrayList<Integer>();
           // the size of current level
           int size = queue.size();
           // traverse all the nodes on the current level and
           // prepare for the next level.
           for (int i = 0; i < size; i++){
               TreeNode cur = queue.poll();
               curLayer.add(cur.val);
               if (cur.left != null){
                   queue.offer(cur.left);
               }

               if (cur.right != null){
                   queue.offer(cur.right);
               }
           }

           result.add(curLayer);
       }
       return result;
    }
}

/*
                     3 <-     9 20         <-
            1 

            2 
        cur: 1

*/

// TC: O(n)
// SC: O(n)
```



