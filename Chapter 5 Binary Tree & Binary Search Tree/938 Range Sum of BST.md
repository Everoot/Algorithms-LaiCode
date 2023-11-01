# 938 Range Sum of BST

Given the `root` node of a binary search tree and two integers `low` and `high`, return *the sum of values of all nodes with a value in the **inclusive** range* `[low, high]`.

 

**Example 1:**

![img](./938 Range Sum of BST.assets/bst1.jpg)

```
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
```

**Example 2:**

![img](./938 Range Sum of BST.assets/bst2.jpg)

```
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null){
            return 0;
        }

        int sum = 0;

        if (root.val >= low && root.val <= high){
            sum = sum + root.val;
        }

        if (low < root.val){
            sum = sum + rangeSumBST(root.left, low, high);
        }

        if (root.val < high){
            sum = sum + rangeSumBST(root.right, low, high);
        }

        return sum;
    }
}
```





**经典例题2: Print BST keys in the given range (Google 现役)**

Given two value k1 and k2 (where k1 < k2) and root pointer to a Binary Search Tree. Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<= x <= k2 and x is a key of given BST. Print all the keys in an increasing order.

​                                           10 == root

​            								/     \

​										5         15

​         							/   \       /    \

​         						 2     7     12   20         <- all leaf node's level == 3

​           					/   \

​						   null  null

让你打印出6-14的数值

​				   k1 k2

所以应该打印出7, 10, 12



Method 1: brute force

  	  Inorder 遍历 -> isBST 判据1

O(n)

O(height)



Method2:

```java
void printBSTrange(TreeNode root, int k1, int k2){
  if (root == null){
    return;
  }
  if (root.val > max){
    printBSTrange(root.left,k1,k2);
  }
  if (root.val < min){
    printBSTrange(root.right,k1,k2);
  }
}
```

这个答案, 没有保证sorted 所以还需要改进



follow inOrder Traversal一定是升序

Time:  O(n)

1->2 ->3 -> ....

Space: O(height)

```java
package Class5BinaryTreeAndBinarySearchTree.Assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
55. Get Keys In Binary Search Tree In Given Range

Get the list of keys in a given binary search tree in a given range[min, max] in ascending order,
both min and max are inclusive.

Examples
        5
      /    \
    3        8
  /   \        \
 1     4        11
get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

Corner Cases
What if there are no keys in the given range? Return an empty list in this case.
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
public class Ex9GetKeysInBinarySearchTreeInGivenRange {
    public static class TreeNode{
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(11);
        System.out.println(getRange(root, 2,5));
    }
    // 参考答案
    public static List<Integer> getRange(TreeNode root, int min, int max){
        List<Integer> result = new ArrayList<>();
        helper(root, min, max, result);
        return result;
    }

    public static List<Integer> helper(TreeNode root, int min, int max, List<Integer> result){
        if (root == null){
            return null;
        }
        // 1. determine if left subtree should be traversed, only when root.key > min
        //    we should traverse the left subtree.
        if (root.key >= min){
            helper(root.left, min, max, result);
        }
        // 2. determine if root should be traversed.
        if (root.key >= min && root.key <= max){
            result.add(root.key);
        }
        // 3. determine if right subtree should be traversed, only when root.key < max,
        // we should traverse the right subtree.
        if (root.key < max){
            helper(root.right, min, max, result);
        }

        return result;
    }
    /*
    public static List<Integer> helper(TreeNode root, int min, int max, List<Integer> result){
        if (root == null ){
            return null;
        }
        helper(root.left, min, max, result); // 题目要求从小到大的顺序
        if ((root.key >= min) && (root.key <= max)) {
            result.add(root.key);
        }
        helper(root.right, min, max, result);
        return result;
    }

     */

}

```

减枝: pruning 

减枝:pruning

减枝不一定能优化

worst case: [-INF, +INF]

明知道那条路不对 就不走了



