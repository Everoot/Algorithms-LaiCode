# 145 Binary Tree Postorder Traversal (iterative)

Given the `root` of a binary tree, return *the postorder traversal of its nodes' values*.

 

**Example 1:**

![img](./145 Binary Tree Postorder Traversal (iterative) .assets/pre1.jpg)

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

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;
        stack.offerLast(cur);
        TreeNode pre = null;

        while (!stack.isEmpty()){
            // go down
            if (pre == null || cur == pre.left || cur == pre.right){
                if (cur.left != null){
                    pre = cur;
                    cur = cur.left;
                    stack.offerLast(cur);
                }else if (cur.right != null){
                    // cur.left == null && cur.right!= nulll
                    pre = cur;
                    cur = cur.right;
                    stack.offerLast(cur);
                
                }else{
                    // cur.left && cur.right == null
                    pre = cur;
                    result.add(stack.pollLast().val);
                    cur = stack.peekLast();
                }

            }else if (cur.left == pre){
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





The problem is, we need to traverse both left and right subtrees first, then we can eliminate the root from the stack. We need a mechanism to know when we finished visiting all subtrees' nodes.

<img src="./145 Binary Tree Postorder Traversal (iterative) .assets/Screenshot 2023-05-26 at 12.29.11.png" alt="Screenshot 2023-05-26 at 12.29.11" style="zoom:50%;" />

pre: 3

cur: 2

stack: [ 5 2 

result: 1 3 2



What we need to know?

* The direction!

  we are visiting down? or returning from left? or returning from right?



The root is the top element in the stack.

**Maintain a previous Node**, to record the previous visiting node on the traversing path, so that we know what the direction we are taking now and what is the direction we are taking next.

* root = stack.top
* if previous is null -> going down (left subtree has priority)
* if previous is current's parent -> going down (left subtree has priority)
* if previous == current.left, -> left subtree finished, going current.right
* if previous == current.right -> right subtree finished, pop current, going up





Previous								Stack               Print -> left and right subtree both finished

null											{5}

5 												{5,2}

2												 {5, 2, 1}							1

1												 {5, 2}

2												 {5, 2, 3}							3

3												 {5, 2}							    2

2												 {5}

5												 {5, 8}								8

8												 {5}									5

5												 {}







**This solution is important because this is the closest imitation of how actually the recursion is done in STACK, and it can be used as a general framework to iteratively do pre-order and in-order traversal as well.**



If you look closely, the recursion is actually a DFS procedure on the binary tree. pre-order, in-order, post-order are following the same DFS procedure, and the only difference is when you want to print the node.



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

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;
        stack.offerLast(cur);
        TreeNode pre = null;

        while (!stack.isEmpty()){
            // go down
            if (pre == null || cur == pre.left || cur == pre.right){
                if (cur.left != null){
                    pre = cur;
                    cur = cur.left;
                    stack.offerLast(cur);
                }else if (cur.right != null){
                    // cur.left == null && cur.right!= nulll
                    pre = cur;
                    cur = cur.right;
                    stack.offerLast(cur);
                
                }else{
                    // cur.left && cur.right == null
                    pre = cur;
                    result.add(stack.pollLast().val);
                    cur = stack.peekLast();
                }

            }else if (cur.left == pre){
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

![Screenshot 2023-05-27 at 03.52.04](./145 Binary Tree Postorder Traversal (iterative) .assets/Screenshot 2023-05-27 at 03.52.04.png)
