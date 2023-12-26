# 450 Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return *the **root node reference** (possibly updated) of the BST*.

Basically, the deletion can be divided into two stages:

1. Search for a node to remove.
2. If the node is found, delete the node.

 

**Example 1:**

![img](./450 Delete Node in a BST/del_node_1.jpg)

```
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
```

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
    public TreeNode deleteNode(TreeNode root, int key) {
        // case base 
        if (root == null){
            return root;
        }
      
        if (root.val == key){
            // case 1 
            if (root.left == null && root.right == null){
                return null;
            }else if (root.left == null){  //case 2
                return root.left;
            }else if (root.right == null){ //case 3
                return root.left;
            }else if (root.right.left == null){
                root.right.left = root.left;
                return root.right;
             // case 4
            // root.left != null && root.right !=null
            // case 4.1 
               /*
                         3                    7
                        /  \                /    \
                       1    7    ->        1       8           
                           / \
                              8 
                */
            }else{
              //if (root.right.left != null){
              TreeNode newRoot = smallest(root.right);
            	newRoot.left = root.left;
            	newRoot.right = root.right;
            	return newRoot;
             /*
                         3                    7 c p               4 
                        /  \                /    \           
                       1    7    ->      (  4 c s       8 ) ->          
                           / \
                         4     8 
                */
            }
        }else {// root.val != key
            if (root.val < key){
                root.right = deleteNode(root.right, key);
            }else if (root.val > key){
                root.left = deleteNode(root.left, key);
            }

        }

        return root;
        
    }

    private static TreeNode smallest(TreeNode root){
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

// TC:O(logn) worst case O(n)
// SC: O(h)
```

