# 78 Subsets

Given an integer array `nums` of **unique** elements, return all possible subsets (the power set).

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

 

**Example 1:**

```
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

**Example 2:**

```
Input: nums = [0]
Output: [[],[0]]
```

**Solution:**

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // base case 
        if (nums == null || nums.length == 0){
            return result;
        }

        List<Integer> subresult = new ArrayList<Integer>();
        helper(nums, 0, subresult, result);
        return result;
    }

    private static void helper(int[] nums, int index, List<Integer> subresult, List<List<Integer>> result){
        if (index == nums.length){
            result.add(new ArrayList<Integer>(subresult));
            return;
        }

        subresult.add(nums[index]);
        helper(nums, index+1, subresult, result);

        subresult.remove(subresult.size() - 1);
        helper(nums, index + 1, subresult, result);
    }
}



/*
            1 2 3
1           /     \
           1       []
          / \     / \
2        12   1    2  []
        / \   / \  / \  / \
3      123 12 13 1 23 2 3 []   

level i : represent whether add nums[i]

branch : left represent add nums[i]  right represent not add nums[i]

// TC: O(n * branch^level) = O(n 2^n)
// SC: O(n)
*/
```

