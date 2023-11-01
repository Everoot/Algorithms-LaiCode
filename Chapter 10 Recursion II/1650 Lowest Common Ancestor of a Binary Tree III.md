# 1650 Lowest Common Ancestor of a Binary Tree III

Given two nodes of a binary tree `p` and `q`, return *their lowest common ancestor (LCA)*.

Each node will have a reference to its parent node. The definition for `Node` is below:

```
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
```

According to the **[definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor)**: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow **a node to be a descendant of itself**)."

 

**Example 1:**

![img](./1650 Lowest Common Ancestor of a Binary Tree III.assets/binarytree-20230725182711325.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**

![img](./1650 Lowest Common Ancestor of a Binary Tree III.assets/binarytree-20230725182711325.png)

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
```

**Example 3:**

```
Input: root = [1,2], p = 1, q = 2
Output: 1
```





```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        // base case 
        if (p == q){
            return p;
        }

        if (p.parent == q.parent){
            return p.parent;
        }

        Node runner1 = p;
        Node runner2 = q;
        while(runner1 != runner2){
            if (runner1 == null){
                runner1 = q;
            }else{
                runner1 = runner1.parent;
            }

            if (runner2 == null){
                runner2 = p;
            }else{
                runner2 = runner2.parent;
            }
        }

        return runner1;
        
    }
}

// TC: O(n)
// SC: O(1)

/*

r1. = p
r2 = q

                        3
                   /        \
                  5 (p)      1
                /  \        / \
                6  2       0  8
                   /\
                  7 4(q)


                        3
                   /        \
                  5(r1)       1
                /  \        / \
                6  2       0  8
                   /\
                  7 4(r2)

        q             p
        4  -  2  -  5   3
                 r1                           r1 =q
                 r2

1 ------o---    1 + 2:  ------o--- --o---
2     --o---    2 + 1:  --o-- -------o--- 

*/
```

