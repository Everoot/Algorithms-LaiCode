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

permutation 有什么特点

1. 不能不加

2. 有顺序 output有顺序, abc和cba在结果里算顺序不同的两个解.

   > input 不讲顺序 abc and cba虽然顺序不同, abc 和 cba的all permutation 都是(abc, acb, bac, bca, cab, cba)

如何避免重复: 画Recursion Tree

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // base case
        if (nums == null || nums.length == 0){
            return result;
        }

        int index = 0;
        helper(nums, index, result);
        return result;
        
    }

    private static void helper(int[] nums, int index, List<List<Integer>> result){
        // base case 
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
            if (valueSet.contains(nums[i])){
                continue;
            }else{
                valueSet.add(nums[i]);
                swap(nums, index, i);
                helper(nums, index + 1, result);
                swap(nums, index, i);
            }
        }
    }

    private static void swap(int[] nums, int left, int right){
        int rem = nums[left];
        nums[left] = nums[right];
        nums[right] = rem;
    }
}


/*

for loop 
set check whether 
       1 1 2  
      /   \       \ 
   1|1,2  continue  2|1|2

// TC: O(n!) = O(branch^n)
// SC: O(n + n -1 + n-2 + n-3 ... + 1) = O(n + n(n-1+1)/2) = O(n^2)
*/
```

