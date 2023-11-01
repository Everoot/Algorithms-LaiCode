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
    
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++){
                TreeNode output = queue.poll();
                level.add(output.val);
                if (output.left != null){
                    queue.offer(output.left);
                }

                if (output.right != null){
                    queue.offer(output.right);
                }

            }

            result.add(level);

        }
        return result;

        
    }
}

/*
    3, 9, 20, 15, 7
    3 
    9, 20
    15, 7
    queue:             <-    20   <-
    result:     3 9

*/
```



1. **==Breadth-First Search (BFS-1): Level order==**

   ​										1   expand(1) -> generate(3) and generate(2)

   ​									/       \

   ​								 3		   ==2==

   ​							  /   \          /

   ​						   5      4		7

   ​						  /  \   

​								9     11

类似传销的人, 一人入坑, 一个带一个

比如在这个tree里, 我们所能找到的第一个上限是1,

FIFO queue = {1}

当把 1 expand即放出来 FIFO queue = {},  BFS-1 order: 1

然后它会generate 所有它的下限 FIFO queue = {3, 2}.

由于这个是个first in first out的queue所以下一个放出来的是3, 且同时也generate它的下限

即  FIFO queue = {2, 5, 4}      BFS-1 order: 1, 3

继续上述操作

FIFO queue = {5, 4, 7}     BFS-1 order: 1, 3, 2

FIFO queue = {4, 7, 9, 11} 	BFS-1 order: 1, 3, 2, 5

FIFO queue = {7, 9, 11} 	BFS-1 order: 1, 3, 2, 5

FIFO queue = {9, 11} 	BFS-1 order: 1, 3, 2, 5, 7

FIFO queue = {11} 	BFS-1 order: 1, 3, 2, 5, 7, 9

FIFO queue = { } 	BFS-1 order: 1, 3, 2, 5, 7, 9, 11



Optionally Set: which nodes have been generated so far.



==如下总结非常优秀==

BFS的操作过程 & How to describe a BFS's action during an interview?

* **Data Structure**: Maintain a FIFO queue, put all traversed nodes that haven't been expanded.

  e.g. 3 and then 2 into the queue(FIFO) queue head -> [3, 2] tail

* **Initial state**

  e.g. add root to queue

* **Expand a node s**

  e.g. pop from queue and visit/print its value...

* **Generate neighbor node**: reach out to its neighboring nodes.

* **Termination condition**: do a loop until the queue is empty

* **Optionally deduplicate visited nodes (typically for graph not for tree)**

  e.g. each node is expanded only once

  e.g. each node is generated only once

  deduplicate  vt. 删除重复数据



### **==经典例题1: 分层打印一个binary tree.==**

​										1   expand(1) -> generate(3) and generate(2)

​									/       \

​								 3		   ==2==

​							  /   \          /

​						   5      4		7

​						  /  \   

​						9     11

Output:

1				newline

3	2 		 newline

5	4	7    newline

9	11



round1 

q: [1], size = 1

print 1



round2

q: [3, 2], size = 2 -> q:[5, 4, 7]

print 3, 2 

**We only need to know the number of nodes in this level Before the printing**



round3

q: [5, 4, 7] size = 3 -> q:[9, 11]

print 5, 4, 7 



```java
package Class6HeapAndGraphSearchIBFS;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFSlayers {
    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(9);
        root.left.left.right = new TreeNode(11);
        root.right.left = new TreeNode(7);

        printNodeByLevel(root);

    }

    public static void printNodeByLevel(TreeNode root){
        if (root == null){
            return;
        }
        Queue<TreeNode> q = new ArrayDeque<>(); // Data Structure
        q.offer(root); // initial state
        while (!q.isEmpty()){ // Termination condition\
            int size = q.size();   // size = # of nodes in the cur layer.
            for (int i = 0; i < size; i++){
                TreeNode n = q.poll(); // Expand
                if (n.left != null){
                    q.offer(n.left);   //Generate
                }
                if (n.right != null){
                    q.offer(n.right);  // generate
                }
                System.out.println(n.value + " ");
            }
            System.out.println(); // 打印一个换行符
        }
    }
}
// Time complexity = O(n)
// Space complexity = O(n)       最下面一层要记录的最多有n/2
// 所有node被generate 1
// 所有node被expande 1 所以 tc = O(n)
```

```
1 

3 
2 

5 
4 
7 

9 
11 


Process finished with exit code 0
```



深度优先 recursion 渣到底

宽度优先

这两种是不同的逻辑



Assignments

57. Get Keys In Binary Tree Layer By Layer

DescriptionNotes

Easy

Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

**Examples**

​    5

   /   \

  3     8

 /  \     \

 1   4     11

the result is [ [5], [3, 8], [1, 4, 11] ]

**Corner Cases**

- What if the binary tree is null? Return an empty list of list in this case.

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4

```java
package Class6HeapAndGraphSearchIBFS.Assignments;

import java.util.*;

/*
57. Get Keys In Binary Tree Layer By Layer

Get the list of list of keys in a given binary tree layer by layer.
Each layer is represented by a list of keys and the keys are traversed from left to right.
Examples
        5
      /    \
    3        8
  /   \        \
 1     4        11
the result is [ [5], [3, 8], [1, 4, 11] ]
Corner Cases
What if the binary tree is null? Return an empty list of list in this case.
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
public class Ex2GetKeysInBinaryTreeLayerByLayer {
    public static class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(11);
        System.out.println(Arrays.toString(layerByLayer(root).toArray()));

        TreeNode root1 = null;
        System.out.println(Arrays.toString(layerByLayer(root1).toArray()));
    }

    // List指的是存int类型数据的列表，List<List>指的是存int类型数据的列表的列表
    // 母列表存子列表，其子列表存int类型的数据
    // 参考答案

    public static List<List<Integer>> layerByLayer(TreeNode root){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            // the list storing all the nodes on the current level.
            List<Integer> curLayer = new ArrayList<Integer>();
            // the size = queue.size();
            int size = queue.size();
            // traverse all the nodes on the current level and prepare for the next level.
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                curLayer.add(cur.value);
                if (cur.left != null){
                    queue.add(cur.left);
                }
                if (cur.right != null){
                    queue.add(cur.right);
                }
            }
            list.add(curLayer);
        }
        return list;
    }
    /*
    public static List<List<Integer>> layerByLayer(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();

        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> resultfirst = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode n = q.poll();
                if (n.left != null){
                    q.offer(n.left);
                }
                if (n.right != null){
                    q.offer(n.right);
                }
                resultfirst.add(n.value);
                System.out.print(n.value + " ");
            }
            result.add(resultfirst);
            System.out.println();
        }
        return result;

    }

     */

}
```

```
[[5], [3, 8], [1, 4, 11]]
[]

Process finished with exit code 0
```

