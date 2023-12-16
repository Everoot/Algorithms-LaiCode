# 141 Binary Tree Path Sum To Target III (Lai)

Given a binary tree in which each node contains an integer number. Determine if there exists a path **(the path can only be from one node to itself or to any of its descendants),** the sum of the numbers on the path is the given target number.

**Examples**

   5

 /   \

2    11

   /   \

  6   14

 /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

**How is the binary tree represented?**

We use the level order traversal sequence with a special symbol "#" denoting the null node.

**For Example:**

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

  1

 /  \

 2   3

   /

  4



**Solutions:**

**Method 1: Brute Force**

O(n)的时间枚举所有的path

Pre-order to iterate over the whole tree, and for each current node X, we do a for loop in {X ... root}

Time = O(n*height) = O(n^2)

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
public class Solution {
  public boolean exist(TreeNode root, int target) {
    // Write your solution here

    if (root == null){
      return false;
    }

    if (root.left == null && root.right == null && root.key == target){
      return true;
    }
		
    // pass down the prefix of the path
    List<TreeNode> path = new ArrayList<TreeNode>();
    return helper(root, path, target);
  }

  private static boolean helper(TreeNode root, List<TreeNode> path, int target){
    // add cur node
    path.add(root);
		
    // check if can find a subpath ended at root node,
    // the sum of the subpath is target.
    int sum = 0;
    for (int i = path.size()-1; i >= 0; i--){
      int cur = path.get(i).key;
      sum = sum + cur;
      if (sum == target){
        return true;
      }
    }

    if (root.left != null && helper(root.left, path, target)){
      return true;
    }

    if (root.right != null && helper(root.right, path, target)){
      return true;
    }

    // remove cur node 
    path.remove(path.size() -1);
    return false;
  }
}
// TC: O(n^2) = O(n^height) 
// SC: O(n)

```





**Method 2: prefixSum**

[xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx J]

[xxxxxxxxxxxxxxxxxxxx I]

​											[i+1								j]

怎么快速的知道刚才没见过 prefixSum - target  => Look Up

​		Set\<Integer> prefixSum 

直上直下

​		prefixSum.contains(curPrefixSum - target)

Time = O(n) Space = O(height) = O(n)

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
public class Solution {
  public boolean exist(TreeNode root, int target) {
    // Write your solution here
    // Think about the related problem, how do you find it there is 
    // a subarray sum to target value?
    // If there is an O(n) solution to the above problem, we can extend
    // it to each of the root->leaf paths of the binary tree.
    
    if (root == null){
      return false;
    }
    
    if (root.left == null && root.right == null && root.key == target){
      return true;
    }
    
    Set<Integer> prefixSums = new HashSet<Integer>();
    prefixSums.add(0); // one node = target, find itself just one 
    int sum = 0;
    return helper(root, prefixSums, sum, target);
  }

  private static boolean helper(TreeNode root, Set<Integer> prefixSums, int sum, int target){
    // add cur root
    sum = sum + root.key;

    // prevSum - x = target -> x = prevSum - target = x; x see before
    // check whether see the same situation before
    if (prefixSums.contains(sum - target)){
      return true;
    }

    // Set can not duplicate so to avoid you remove again
    // you have to use a boolean to check whether add successfully
    // when this level finish whether should remove the add one instead of 
    // remove the previous one.
    boolean needRemove = prefixSums.add(sum);
    if (root.left != null && helper(root.left, prefixSums, sum, target)){
      return true;
    }

    if (root.right != null && helper(root.right, prefixSums, sum, target)){
      return true;
    }
    
    if (needRemove == true){
      prefixSums.remove(sum);
    }
    
    return false;
  }
}

// TC: O(n)
// SC: O(n)
```

