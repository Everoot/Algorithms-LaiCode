# 215 Reconstruct Binary Tree With Levelorder And Inorder (Lai)

Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

**Assumptions**

- The given sequences are not null and they have the same length
- There are no duplicate keys in the binary tree

**Examples**

levelorder traversal = {5, 3, 8, 1, 4, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

​    5

   /   \

  3     8

 /  \     \

1    4     11

**How is the binary tree represented?**

**We use  level order traversal sequence with a special symbol "#" denoting the null node.**

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



**Solution:**

Step 1:确定Root：     Level[LLeft]

主要是第二步难

Step 2: 确定子树的Level Order

​				     左子树											右子树

in:			4, 8, 10, 12, 14										22

level    	 8, 4, 12, 10, 14							     	  2

When we traverse the level order, how do we know if levelOrder[i] is in leftsubtree or rightSubtree.

inorder是有顺序的，比如我拿到一个8，先去inOrder里看是比20这个index大还是小，可以知道它是root的左右子树。

SubStep 1: get出levelOrder[i]在inOrder index = 1

SubStep 2: root在inOrder的index是5说明8在20的左边

​					InOrder Index < Inorder root index                 LeftSubtree

​					InOrder Index < Inorder root Index				 RightSubtree

难点: 如何分解难! 分析 和 从哪儿入手

```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
// level = {5,3,8,1,4,11}
// inoder ={1,3,4,5,8,11}
// Map: <1, 0> <3,1> <4,2> <5,3> <8,4> <11,5>
public class Solution {
  public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
    // Write your solution here
    // Assumptions: level, in are not null
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < inOrder.length; i++){
      inMap.put(inOrder[i], i);
    }
    //  Map: <1, 0> <3,1> <4,2> <5,3> <8,4> <11,5>
    
    List<Integer> lList = new ArrayList<Integer>();
    for (int num : levelOrder){
      lList.add(num);
    }
    // lList 5, 3, 8, 1, 4, 11
    return helper(lList, inMap);
  }

  private static TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap){
    if(level.isEmpty()){
      return null;
    }
    // level {5, 3, 8, 1, 4, 11}
    // in.   {1, 3 , 4, 5, 8, 11}
    // 
    TreeNode root = new TreeNode(level.remove(0)); //5
    // 3, 8 , 1, 4, 11
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    for (int num : level){
      if (inMap.get(num) < inMap.get(root.key)){
        // <3,1>= 1      <5,3> = 3 
        left.add(num); // 3, 1, 4
      } else { // 8   11
        right.add(num);
      }
    }
    root.left = helper(left, inMap);
    root.right = helper (right, inMap);
    return root;
  }
}
// TC:O(n^2)
// SC:O(n^2)
```



