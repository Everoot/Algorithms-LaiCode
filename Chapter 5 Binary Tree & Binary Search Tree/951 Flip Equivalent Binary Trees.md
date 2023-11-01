# 951 Flip Equivalent Binary Trees

For a binary tree **T**, we can define a **flip operation** as follows: choose any node, and swap the left and right child subtrees.

A binary tree **X** is *flip equivalent* to a binary tree **Y** if and only if we can make **X** equal to **Y** after some number of flip operations.

Given the roots of two binary trees `root1` and `root2`, return `true` if the two trees are flip equivalent or `false` otherwise.

 

**Example 1:**

<img src="./951 Flip Equivalent Binary Trees.assets/tree_ex.png" alt="Flipped Trees Diagram" style="zoom:33%;" />

```
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
```

**Example 2:**

```
Input: root1 = [], root2 = []
Output: true
```

**Example 3:**

```
Input: root1 = [], root2 = [1]
Output: false
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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
       // base case 
       if (root1 == null && root2 == null){
           return true;
       } 

       if (root1 != null && root2 == null){
           return false;
       }

       if (root1 == null && root2 != null){
           return false;
       }

       // !=null
       if (root1.val != root2.val){
           return false;
       }

       return (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)) || (flipEquiv(root1.right, root2.right) && flipEquiv(root1.left, root2.left));
    }
}
```



**Complexity Analysis**

- Time Complexity: O(min(N1,N2)), where N1,N2 are the lengths of `root1` and `root2`.
- Space Complexity: O(min(H1,H2)), where H1,H2 are the heights of the trees of `root1` and `root2`.





**举一反三**

==**Q3: Let's assume if we tweak the Ichild with child of an arbitrary node in a binary tree, then the "structure" of the tree are not changed. Then how can we determine whether two binary trees' structures are identical. (Twist Identical?)**==

​                     8a                                             8b

​			   	/      \                                         /         \

​                4           5 								5              4

​                  \         													/

​                     7														7



Case1.              8a              AND         8b            OR     Case2.          8a             AND         8b

​						/     \                            /    \                    					/     \                            /    \

​					 4a      5a                      4b     5b                                 4a    5a                       5b    4b

​					  |			                       |   									     |                							|

​					  7               				    7											7											7

可以和原来一样也可以不一样

**Base Case:**

```java
if (left == null && right == null){
  			return true;               // base case1
} else if (left == null || right == null){
  			return false;							  // base case 2+3
} else if (left.value != right.value){	
  			return false;								// base case 4
}
```

**Subproblem** & Recursion rule

​							8a 								VS              			8b

Case 1: Identical 

​				twistldentical(8a's left, 8b's left) && twistLdentical(8a'right, 8b's right) 

||

Case 2: Symmetric

​				twistldentical(8a's left, 8b's right) && twistldentical(8a' right, 8b's left)





有四个子问题

1. twistldentical(8a's left, 8b's left)
2. twistLdentical(8a'right, 8b's right) 
3. twistldentical(8a's left, 8b's right)
4. twistldentical(8a' right, 8b's left)

​																recursion Tree(root1, root2)

​											/                        /                                   \     						\

​         	   (root1.L, root2.L) 		(root1.R, root2.R)		 (root1.L, root2.R)		 (root1.R, root2.R)

假设是balance: 有 logn层

branch:每层有4个点

Binary Tree of H levels, time complexity = O($2^H$)   H= logn      2^logn=n     

Given a Quadral Tree if we have n levels Time = O(4^n) => O(4^level)

Time: O($4^{log_{2}n} = 2^{2{log_{2}n} }= 2^{log_{2}n^2} = n^2$)



这道题: recursion Tree的高度是不是取决于input tree高度

如果input tree 是balance这道题recursion Tree高度=$log_2n$



assume is balance -> we have $log_2n$ as height

We have an array tree with $log_2n$ height => O($4^{log_2n}$)

​														|

​												 input tree 高度



Insight, what if the tree is not balanced? (such at linkedList) 其实这个要比上面的case好 -> best case

​               					1													1

​								/   \ 												  / \

​            				  4	null										null	4	 

​							  |															 |	

 							 5     														5

​																recursion Tree(root1, root2)

​											/                        /                                   \     						\

​         	   ~~(root1.L, root2.L) 		(root1.R, root2.R)~~		 (root1.L, root2.R)		 ~~(root1.R, root2.R)~~

​                   4            null                   null         4                      4           4                      null          null

```java
package Class5BinaryTreeAndBinarySearchTree.Assignments;
/*
50. Tweaked Identical Binary Trees
Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed.
A tweak is defined as a swap of the children of one node in the tree.
Examples
        5
      /    \
    3        8
  /   \
1      4
and
        5
      /    \
    8        3
           /   \
          1     4
the two binary trees are tweaked identical.
How is the binary tree represented?
We use the level order traversal sequence with a special symbol "#" denoting the null node.
For Example:
The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
    1
  /   \
 2     3
      /
    4
 */
public class Ex7TweakedIdenticalBinaryTrees {
    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args){
        TreeNode root5 = new TreeNode(5);
        TreeNode root3 = new TreeNode(3);
        TreeNode root1 = new TreeNode(1);
        TreeNode root4 = new TreeNode(4);
        TreeNode root8 = new TreeNode(8);
        root5.left = root3;
        root5.right = root8;
        root3.left = root1;
        root3.right = root4;

        TreeNode r5 = new TreeNode(5);
        TreeNode r3 = new TreeNode(3);
        TreeNode r1 = new TreeNode(1);
        TreeNode r4 = new TreeNode(4);
        TreeNode r8 = new TreeNode(8);
        r5.right = r3;
        r5.left = r8;
        r3.left = r1;
        r3.right = r4;
        System.out.println(isTweak(root5,r5));


    }

    public static boolean isTweak(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null){
            return true;                  // base case1 两个点都是null
        } else if (root1 == null || root2 == null){
            return false;                // base case2 两个点有一个是null
        } else if (root1.value != root2.value){ // 两个点不是null 但值不同的时候
            return false;
        }

        // Subproblem
        // case1: identical ||  case2: Symmetric
        if ((isTweak(root1.left, root2.left) && isTweak(root1.right, root2.right)) ||
        (isTweak(root1.left, root2.right) && isTweak(root1.right, root2.left))){
            return true;
        } else{
            return false;
        }
    }

    // Time: O(n^2)
    // Space: O(height)
}
```

```java
true

Process finished with exit code 0
```

