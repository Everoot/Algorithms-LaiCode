# 450 Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return *the **root node reference** (possibly updated) of the BST*.

Basically, the deletion can be divided into two stages:

1. Search for a node to remove.
2. If the node is found, delete the node.

 

**Example 1:**

![img](./450 Delete Node in a BST.assets/del_node_1-20230523011315485.jpg)

```
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
```

<img src="./450 Delete Node in a BST.assets/Screenshot 2023-05-23 at 01.13.44.png" alt="Screenshot 2023-05-23 at 01.13.44" style="zoom:50%;" />

**Example 2:**

```
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
```

**Example 3:**

```
Input: root = [], key = 0
Output: []
```

```
Input: root = [], key = 0
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
    public TreeNode deleteNode(TreeNode root, int key) {
        // case base 
        if (root == null){
            return root;
        }

        if (root.val == key){
            // case 1 
            if (root.left == null && root.right == null){
                return null;
            }

            // case 2
            if (root.left != null && root.right == null){
                return root.left;
            }

            // case 3
            if (root.left == null && root.right != null){
                return root.right;
            }

            // case 4
            // root.left != null && root.right !=null
            // case 4.1 
            if (root.right.left == null){
                root.right.left = root.left;
                return root.right;
            }

            //if (root.right.left != null){
            TreeNode newRoot = findAndDeleteSmallest(root.right);
            newRoot.left = root.left;
            newRoot.right = root.right;
            return newRoot;
        }else {// root.val != key
            if (root.val < key){
                root.right = deleteNode(root.right, key);
            }

            if (root.val > key){
                root.left = deleteNode(root.left, key);
            }

        }

        return root;
        
    }

    private static TreeNode findAndDeleteSmallest(TreeNode root){
        TreeNode cur = root;
        TreeNode prev = null;
        while(cur.left != null){
            prev = cur;
            cur = cur.left;
        }

        TreeNode smallest = cur;
        prev.left = cur.right;
        return smallest;
    }
}


/*
             root == null
             return

    if          root.val == key 
                // case1 :
                            5                      5
                           / \     key =3     ->   \ 
                         3    6                    6

                root.left == null && root.right == null

                // case2:
                            5                    5
                           / \                  / \ 
                          3   6          -?    2   6
                         /
                        2 
                root.left != null && root.right == null
                return root.left;

                // case 3
                            5                    5
                           / \                  / \ 
                          3   6          -?    4   6
                           \
                            4

                root.left == null && root.right != null
                retur root.right;

                // case 4
                            5                    5
                           / \                  / \ 
                          3   6          -?    4   6
                        /   \                 / 
                       1     4               1
       case4.1            root.right.left == null && root.right.right == null
                          
                          root.right.left = root.left;
                          return root.right;

       case4.2               8                    8
                           / \                  / \ 
                          3   9          -?    4   9
                        /   \                 / \ 
                       1     4               1   5
                              \
                               5

                   root.right.left == null && root.right.right != null
                      root.right.left = root.left
                      return root.right;

       case4.3       root.right.left != null && root.right.right != null
                     root.right.left != null && root.right.right == null
       
                            8                    8
                           / \                  / \ 
                          3   9          -?    4   9
                        /   \                 / \ 
                       1     7               1   7
                            /                   /
                           5 (p)                5 (p)
                          / \                 /  \
                         4(s) 6              4.5   6
                          \   
                           4.5
                                      
                    step 1: find the smallest
                    step 2: detele from subtree and 
                            if (s.right have something if should be connect to parents)

            


    if          root.val != key

                if (root.val < key){
                    root.right = deleteNode(root.right, key);
                }

                if (root.val > key){
                    root.left = deleteNode(root.left, key);
                }



                return root;
*/
```



**Complexity Analysis**

- Time complexity : O(log⁡N)\mathcal{O}(\log N)O(log*N*). During the algorithm
  execution we go down the tree
  all the time - on the left or on the right,
  first to search the node to delete (O(H1)\mathcal{O}(H_1)O(*H*1​) time
  complexity as already
  [discussed](https://leetcode.com/articles/insert-into-a-bst/))
  and then to actually delete it. H1H_1*H*1​ is a tree height from the root to the node
  to delete.
  Delete process takes O(H2)\mathcal{O}(H_2)O(*H*2​) time, where H2H_2*H*2​ is a tree height
  from the root to delete to the leafs.
  That in total results in O(H1+H2)=O(H)\mathcal{O}(H_1 + H_2) = \mathcal{O}(H)O(*H*1​+*H*2​)=O(*H*) time
  complexity, where HH*H* is a tree height, equal to log⁡N\log Nlog*N* in the case of the balanced tree.
- Space complexity : O(H)\mathcal{O}(H)O(*H*) to keep the recursion stack, where HH*H* is a tree
  height. H=log⁡NH = \log N*H*=log*N* for the balanced tree.





##### Delete in BST (Ex12)

​						3  		 										3

​					/     \												/     \		

​               2          ==8==                 ->                        2         10 

​						 /     \								 	  			 /     \

​					 6		 10						     				6		 12

​									\														

​									 12

delete(root, 8)

step ones: find the node to be deleted - trivial?

step two: delete it ...  - not trivial ? How many possible situations?

case1: - the node to be deleted has no children

​					3											3

​				  /   \                   ->                  /

​				2	  8  								2

必须要有断开的动作(dereference带来的heap effect)



case2: - the node to be deleted has no left child.

​					 3								3

​					/ \							/    \

​				2     8                      2        10

​						 \								 \

​						10							  12

​							\

​							12

```java
3.right = root.right
```





case3: - the node to be deleted has no right child.

​					 3								3

​					/ \							/    \

​				2     ==8==                      2        6					

​						/								/

​					6								 5

​					/

​                  5

独子时直接提上来, 必须要有挂树的动作(dereference 带来的heap effect)



case4: - the node to be deleted has both left and right child. We need to move some nodes from the left/right subtree to replace it.



Which node to replace 8?

* **either from left sub, largest of the left subtree.**
* ==**or from right sub, smallest of the right subtree**==

case 4,1: - node.right does not have left child, meaning itself is the smallest node in this case, we just move node.right up.

​						3													3

​					 /    \ 												/	\

​				  2       ==8==                   ->					   2      10

​						  / \	 												/	\

​						6  10         										6	 12	

​						/	   \												/

​					 5		  12		        							5

无论选左子树最大还是右子树最小都对, 但laicode里只能选右子树最小的.



当然最好的肯定是挑个高度最长的进行拼接, 红黑树之类的,整个棵树缩短.



长兄为父



root-> 8

root.right -> 10

root.right.left -> null

root.right.left = root.left;

左右孩子都有, 右节点没有左孩子(右节点自己就是右子树最小)

把右节点拎上去, 去之前记得接管原先节点的左子树



case 4.2 - node.right has left child, we need to find the smallest node, and move it up.

​						3													3

​					 /    \ 												/	\

​				  2       ==8==                   ->					   2       9

​						  / \	 												/	\

​						6  10         										6	 12	

​						/	   \												/		/   \

​					 5		  12		        							5      11   14

​								 /    \												  / 

​						 11(p)	 14									     10

​							  / 													/	\

​							**9**

​								\

​								 10

​								/	\

12 -> left -> left -> left

9.left == null

最general的情况: 右子树最左节点就是smallest, 到把smallest拎上去之前, 右孩子交给它爷爷接管, 把smallest拎上去后还得接管被删节点的左右子树

右子树, 一直往左走



一般爸爸要去城里打工了, 会把孩子留给爷爷-留守儿童, 所以我们需要一路维护一个prev.

```java
package Class5BinaryTreeAndBinarySearchTree.Assignments;
/*
53. Delete In Binary Search Tree
Delete the target key K in the given binary search tree if the binary search tree contains K.
Return the root of the binary search tree.

Find your own way to delete the node from the binary search tree,
after deletion the binary search tree's property should be maintained.

Assumptions
There are no duplicate keys in the binary search tree
The smallest larger node is first candidate after deletion
 */
public class Ex12DeleteInBinarySearchTree {
    public static class TreeNode{
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key){
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
        delete(root, 3);
    }
    // 参考答案
    public static TreeNode delete(TreeNode root, int target){
        if (root == null){
            return null;
        }
        // find target node
        if (root.key > target){
            root.left = delete(root.left, target); // 要挂树
            return root;                         // 这个和insert不一样 insert那个条件互斥，可以最后写一个return root也可以在if里面
        } else if (root.key < target){           // 而这个必须在if里面写 return root 不然它找到后执行完就去执行下面一大坨了
            root.right = delete(root.right, target);    // 或者把下面一大坨是base case 可以用一个else
            return root;
        }
        // guarantee root != null && root.key == target
        // 走到这步说明，我即不是null 我又没有大于 又没有小于 即只有等于 意味着我找到了
        // 找到了 要准备开始删了
        // case1(叶子节点) & 2(只有右孩子)
        //       3          3              3
        //      / \         \              /
        //   null null       5            2
        //                    \          /
        //                     8        1
        if (root.left == null){     //  case1 (叶子节点)  case2 (只有右孩子)
            return root.right;
        } else if (root.right == null){   // case3 (只有左孩子)
            return root.left;
        }

        // guarantee root.left != null && root.right != null
        // case 4.1 (左右子树都有，右节点没有左孩子)
        //        3 (r)               8
        //       / \                 / \
        //      2   8      ->       2   9
        //     /    \               /
        //    1       9             1
        if (root.right.left == null){          // 4.1 不能和 4.2 合并 因为cur那边会变成null的话 就NPE了
            root.right.left = root.left;
            return root.right;
        }
        // case 4.2
        // 1. find and delete the smallest node in root.right
        TreeNode smallest = deleteSmallest(root.right);
        // 2. connect the smallest node with root.left and root.right.
        smallest.left = root.left;
        smallest.right = root.right;
        // 3. return the smallest node.
        return smallest;
    }

    private static TreeNode deleteSmallest(TreeNode cur) {
        TreeNode prev = cur;
        cur = cur.left;
        while (cur.left != null){
            prev = cur;
            cur = cur.left;
        }
        // cur is the smallest one, and prev is its parent.
        prev.left = cur.right; // (右孩子交给爷爷管)
        return cur;
    }

    //1. findMin() 2.delete(root,min) 3.curr.value = min
    // excel表格上的答案会更清晰点
    public static TreeNode deleteTree2(TreeNode root, int key) {
    // Write your solution here
    if (root == null){
      return root;
    }
    if (root.key > key){
      root.left = deleteTree2(root.left, key);
      return root;
    } else if (root.key < key){
      root.right = deleteTree2(root.right, key);
      return root;
    } else {// (root.key == key)
      //case1 左右子节点为空  // case2 左子树为空
      if (root.left == null){
        return root.right;
      } else if (root.right == null){   // case 3 右子树为空
        return root.left;
      } else if (root.right.left == null){ // case 4 左右都有子树  // case 4.1 右子树没有左孩子
        root.right.left = root.left;
        return root.right;
      } else {//if (root.right.left != null) // case 4.2 右子树有左孩子
        TreeNode smallest = findDeleteSmallest2(root.right);
        smallest.left = root.left;
        smallest.right = root.right;
        return smallest;
      }
    }
  }

  public static TreeNode findDeleteSmallest2(TreeNode cur){
    TreeNode prev = cur;
    cur = cur.left;
    while(cur.left != null){
      prev = cur;
      cur = cur.left;
    }
    prev.left = cur.right;
    return cur;
  }
}

```

Time: O(n) find(upper part of height) + findSmallest (lower part of height)

Space: O(h) on call stack

