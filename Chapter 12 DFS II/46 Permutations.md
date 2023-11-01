# 46 Permutations

Given an array `nums` of distinct integers, return *all the possible permutations*. You can return the answer in **any order**. 

**Example 1:**

```
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

**Example 2:**

```
Input: nums = [0,1]
Output: [[0,1],[1,0]]
```

**Example 3:**

```
Input: nums = [1]
Output: [[1]]
```



**Solution:**

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0){
            return result;
        }

        int index = 0;
        helper(index, result, nums);
        return result;
    }

    private static void helper(int index, List<List<Integer>> result, int[] nums){
        if (index == nums.length){
            List<Integer> subResult = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++){
                subResult.add(nums[i]);
            }
            result.add(new ArrayList<Integer>(subResult));
        }

        for(int i = index; i < nums.length; i++){
            swap(i, index, nums);
            helper(index+1, result, nums);
            swap(i, index, nums);
        }
        
    }

    private static void swap(int i, int j, int[] nums){
        int rem = nums[i];
        nums[i] = nums[j];
        nums[j] = rem;
    }
}


/*
                                        [1, 2, 3]
                                        /   |    \
                            1|23           2|13   3|21
                            / \
                          12|3 1|32 
                         /     

// TC: O(branch^level) = O(n(n-1)...)^n = O(n!)^n =O(n!)
// SC:O(n)
*/
```





