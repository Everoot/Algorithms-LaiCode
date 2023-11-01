# 47 Permutations II

Given a collection of numbers, `nums`, that might contain duplicates, return *all possible unique permutations **in any order**.*

**Example 1:**

```
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
```

**Example 2:**

```
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**Solution:**

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
       List<List<Integer>> result = new ArrayList<List<Integer>>();
       if (nums == null || nums.length == 0){
           return result;
       }

       int index = 0;
       helper(index, nums, result);
       return result; 
    }

    private static void helper(int index, int[] nums, List<List<Integer>> result){
        if (index == nums.length){
            List<Integer> subResult = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++){
                subResult.add(nums[i]);
            }
            result.add(new ArrayList<Integer>(subResult));
            return;
        }

        Set<Integer> valueSet = new HashSet<Integer>();
        for (int i = index; i < nums.length; i++){
            if (!valueSet.contains(nums[i])){
                valueSet.add(nums[i]);
                swap(nums, index, i);
                helper(index + 1, nums, result);
                swap(nums, index, i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j){
        int rem = nums[i];
        nums[i] = nums[j];
        nums[j] = rem;
    }
}
/*
TC: O(n!)
SC: O(n*n)
*/
```

