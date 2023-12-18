# 145 Binary Tree Postorder Traversal

Given the `root` of a binary tree, return *the postorder traversal of its nodes' values*.

 

**Example 1:**

![img](./145 Binary Tree Postorder Traversal/pre1.jpg)

```
Input: root = [1,null,2,3]
Output: [3,2,1]
```

**Example 2:**

```
Input: root = []
Output: []
```

**Example 3:**

```
Input: root = [1]
Output: [1]
```



**Solution:**

recursion:

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        if (root.left == null && root.right == null){
            result.add(root.val);
            return result;
        }

        helper(root, result);
        return result;
        
    }

    private void helper(TreeNode root, List<Integer> result){
        if (root == null){
            return;
        }
        
        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);

        return;
    }
}

// TC: O(n)
// SC: O(n)
```



iteration:

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
    // check the relation between the current node and the previous node 
    // to determine which direction should go next.
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;
        stack.offerLast(cur);
        // to record the previous node on the way of DFS so that
        // we can determine the direction.

        TreeNode pre = null;

        while(!stack.isEmpty()){
            // if we are going down, either left/right direction
            if (pre == null || cur == pre.left || cur == pre.right){
                // if we can still go down, try go left first
                if (cur.left != null){
                    pre = cur;
                    cur = cur.left;
                    stack.offerLast(cur);
                }else if (cur.right != null){
                    // cur.left == null && cur.right != null
                    pre = cur;
                    cur = cur.right;
                    stack.offerLast(cur);
                }else{
                    // if we can not go either way, meaing cur is a leaf node
                    // cur.left == null && cur.right == null
                    pre = cur;
                    result.add(stack.pollLast().val);
                    cur = stack.peekLast();
                }
            }else if (cur.left == pre) {
                // from left subtree
                if (cur.right != null){
                    pre = cur;
                    cur = cur.right;
                    stack.offerLast(cur);
                }else{
                    pre = cur;
                    result.add(stack.pollLast().val);
                    cur = stack.peekLast();
                }
            }else{
                // from right subtree
                pre = cur;
                result.add(stack.pollLast().val);
                cur = stack.peekLast();
            }
        }
        return result;
        
    }
}


// TC: O(n)
// SC: O(h)

/*
                    postorder

                    left, right, root


                     5
                    /  \
                   2     8
                  / \ 
                  1  4 

                  1 4 2 8 5



            stack[ 5  2                
             pre = 4
             cur = 2

             result = 1 4

             cur = cur.left 
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
    // Method1: post-order is the reverse order of pre-order with traversing
    // right subtree before traversing left subtree.
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        Deque<TreeNode> preOrder = new LinkedList<TreeNode>();
        preOrder.offerFirst(root);
        while(!preOrder.isEmpty()){
            TreeNode cur = preOrder.pollFirst();
            // conduct the result for the special pre-order traversal
            result.add(cur.val);

            // in pre-order the right subtree will be traversed before the 
            // left subtree so pushing left child first
            if (cur.left != null){
                preOrder.offerFirst(cur.left);
            }

            if (cur.right != null){
                preOrder.offerFirst(cur.right);
            }
        }
        Collections.reverse(result);
        return result;

    }
}

/*

preOrder:  2]

cur 1

r: 1 2 

*/
```

