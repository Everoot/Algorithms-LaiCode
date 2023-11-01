# 127 Lowest Common Ancestor II (Lai)

Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

**Assumptions**

- There is **parent** pointer for the nodes in the binary tree
- The given two nodes are not guaranteed to be in the binary tree

**Examples**

​    5

​    /  \

   9   12

  /  \    \

 2   3    14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)

 

**Solution:**

```java
/**
 * public class TreeNodeP {
 *   public int key;
 *   public TreeNodeP left;
 *   public TreeNodeP right;
 *   public TreeNodeP parent;
 *   public TreeNodeP(int key, TreeNodeP parent) {
 *     this.key = key;
 *     this.parent = parent;
 *   }
 * }
 */
public class Solution {
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
    // Write your solution here.
    int l1 = length(one);
    int l2 = length(two);
      // This is a small trick that can guarantee when calling mergeNode(),
  // the first list is the shorter list, the second list is the longer one.
    if (l1 <= l2){
      return mergeNode(two, one, l2-l1);
    }else{
      return mergeNode(one, two, l1-l2);
    }
    
  }

  private static TreeNodeP mergeNode(TreeNodeP longer, TreeNodeP shorter, int diff){
    while (diff > 0){
      longer = longer.parent;
      diff--;
    }

    while (longer != shorter){
      longer = longer.parent;
      shorter = shorter.parent;
    }
    return longer;
  }

// get the length of the list from the node to the root of the tree
// along the path using parent pointers.
  private static int length(TreeNodeP node){
    int length = 0;
    while(node != null){
      length++;
      node = node.parent;
    }
    return length;
  }
}

/*

// TC: O(n)
// SC: O(1)

*/
```

