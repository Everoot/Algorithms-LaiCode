# 90 Subsets II

Given an integer array `nums` that may contain duplicates, return all possible subsets (the power set).

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

 

**Example 1:**

```
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
```

**Example 2:**

```
Input: nums = [0]
Output: [[],[0]]
```

 

**Solution**

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0){
            return result;
        }

        Arrays.sort(nums);

        int index = 0;
        List<Integer> subResult = new ArrayList<Integer>();
        helper(nums, index, subResult, result);
        return result;
    }

    private static void helper(int[] nums, int index, List<Integer> subResult, List<List<Integer>> result){
        // base case
        if (index == nums.length){
            result.add(new ArrayList<Integer>(subResult));
            return;
        }

        // add
        subResult.add(nums[index]);
        helper(nums, index + 1, subResult, result);

        subResult.remove(subResult.size() - 1);
        while(index < nums.length -1  && nums[index] == nums[index + 1]){
            index++;
        }

        helper(nums, index + 1, subResult, result);
    } 
}


/*

1. sort the nums first


                1  2  2
                /         \
               1           -
             /    \         /   \
            1,2    1       2      -
           /  \    /  \    / \    / \
        1,2,2 1,2 [1,2] 1 2,2 2   [2]  -


skip the duplicate nums until not duplicate


// O(2^n)
// O(n)


*/
```

