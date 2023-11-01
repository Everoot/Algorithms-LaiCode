# 144 Binary Tree Preorder Traversal (recursive)

Given the `root` of a binary tree, return *the preorder traversal of its nodes' values*.

 

**Example 1:**

![img](./144 Binary Tree Preorder Traversal (iterative) .assets/inorder_1.jpg)

```
Input: root = [1,null,2,3]
Output: [1,2,3]
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

### Pre-Order

​								5

​							/       \

​                           2		8

​						 /   \

​						1	3

5, 2, 1, 3, 8

```java
public void preorder(TreeNode root){
  if (root == null){
    return;
  }
  System.out.println(root.key);
  preorder(root.left);
  preorder(root.right);
}
```

The root is the top element on the stack.

**Once the root is traversed, we can print it directly and we do not need to store it in the stack any more.**

1. We always print root first, then root can be eliminated from the stack.
2. We traverse the left sub first, so the **right sub should be retained in the stack** before the left sub is done.



​						5

​					/       \

​					2		8

​					/  \		\

​					1	3	 9

Stack: [ 8 

Result: 5 2 1 3

Stack(tail -> {5}  <- head):														print:

{5} -> pop 5 and push 5.right = 8, and 5.left = 2					5

{8, 2} -> pop 2 and push 2.right = 3 and 2.left =1				  2

{8, 3, 1} -> pop 1 																		  1

{8,3} -> pop 3																				 3

{8} -> pop 8 and push 8.right = 9												8

{9} -> pop 9																					9





pop栈顶并打印, 右进去, 左进去



为什么不能用queue? 

UPS的快递货也是一样的. 货积压问题.

压不住8





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
    public List<Integer> preorderTraversal(TreeNode root) {
        // base case
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.offerLast(root);

        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            result.add(cur.val);
            if (cur.right != null){
                stack.offerLast(cur.right);
            }

            if (cur.left != null){
                stack.offerLast(cur.left);
            }

        }

        return result;


        
    }
}


/*


    root
    root.left
    root.right
                    5
                   / \
                  4    8
                 / \   \ 
                1   3    9

    //  
    stack [    

    result[   5  4  1 3 8 9]  
 

    cur 
    pop -> offer cur.right, cur.left in stack

    stack == null -> stop

    -> return resutl; 

*/
```



#### Complexity Analysis

Let nn*n* be the number of nodes in the tree.

- Time complexity: O(n)
  - We use a stack to store all nodes to be visited. Each of the `n` nodes is added to and popped from the stack once, which takes O(1)time.
  - All other work done at each node is O(1), so the overall time complexity is O(n).
- Space complexity: O(n) 
  - We use a stack to store all the nodes to be visited. Similar to the previous approach, the stack takes up space equivalent to the depth of the tree. The max depth of the tree could be O(n) in the worst-case scenario when the tree is skewed.

TC: O(n)

SC: O(h)
