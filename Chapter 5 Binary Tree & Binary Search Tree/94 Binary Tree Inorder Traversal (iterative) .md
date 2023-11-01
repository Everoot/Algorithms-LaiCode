# 94 Binary Tree Inorder Traversal (recursive)

Given the `root` of a binary tree, return *the inorder traversal of its nodes' values*.

 

**Example 1:**

![img](./94 Binary Tree Inorder Traversal (iterative) .assets/inorder_1-20230523005712238.jpg)

```
Input: root = [1,null,2,3]
Output: [1,3,2]
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // base case
        if (root == null){
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode helper = root;
        while(helper != null || !stack.isEmpty()){
            if (helper != null){
                stack.offerLast(helper);
                helper = helper.left;
            }else{
                helper = stack.pollLast();
                result.add(helper.val);
                helper = helper.right;
            }
        }


        return result;
    }
}
// TC: O(n)
// SC: O(n)



/*

    root.left,
    root
    root.right

                    5
                   /  \ 
                 3      8
                /  \     \  
                1   4     9 
               / 
              null 

   in order: 1 3 4  5 8 9

   stack[ 5 3 1 

   helper :  4
 
   check wether helper.left -> yes  put 5 -> stack, helper = 5.left = 3

   -> no , result add helper.val;   stack.polllast + add resutlt;  3.right;

   result: 1 



*/
```



​					5

​				 /	\

​				2		8

​			   / \

​			  1	3



1, 2, 3, 5, 8

```java
public void inorder(TreeNode root){
  if (root == null){
    return;
  }
  inorder(root.left);
  System.out.println(root.key);
  inorder(root.right);
}
```



The problem is, we can not throw away the root in the stack before we traversed all the nodes in the left subtree. How can we know we have already traversed all the nodes in the left sub?



The root is the top element in the stack, use a helper node to store the next "visiting" node and subtree.

1. When helper node is not null, we should traverse the subtree, so we push helper and we go left.
2. When helper is null, which means the left subtree of the root is finished, the root is the top element in the stack. We can print the top. and let helper = top.right. (traverse the left subtree first, then top, then right subtree)
3. do 1 and 2 until the helper is null and there are no nodes left in the stack.

​					5

​				 /	 \

​			  2		 8

​			/  \

​	   1      3



Stack:[ 

helper (next "visiting" node): 8

Result: 1 2 3 5



这种方法是技巧性很强的, 所以需要特意学习下, 没学过一般很难写出.



==helper != null 有路可走, 继续压栈往左走==

==== null, 无路可走(栈顶是左子树都被打印完毕的根) 弹栈, 打印, 往右走==

这个需要记忆该方法



Helper: (initialized to root)										Stack:											Print:

​				5																		{}

5->5 is not null, helper = 5.left									{5}

2->2 is not null, helper = 2.left									{5, 2}

1-> 1 is not null, helper = 1.left								   {5, 2,1}

null, helper = 1.right														{5, 2}												1

null, helper = 2. right														{5}												   2

3->3	is not null, helper = 3.left									{5, 3}

null, helper = 3.right														{5}													3

null, helper = 5.right													    {}														5

8->8 is not null, helper = 8.left										{8}

null, helper = 8.right														 {}													8

null																						{}

​	

要理解技巧



