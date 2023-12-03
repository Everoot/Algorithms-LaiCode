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

每一层: 通过Swap的方式把某一个元素固定到这一层该固定的位置上

分支: 把谁swap过来

区间物理意义: [index, input.length-1] 没有被换过的元素

[0, index-1] 已经被换过, 不能用的元素 

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
            // add subresult + return 
            List<Integer> subResult = new ArrayList<Integer>();
            // add current array
            for (int i = 0; i < nums.length; i++){
                subResult.add(nums[i]);
            }
            // add subResult to result
            result.add(new ArrayList<Integer>(subResult));
            return;
        }

        for (int i = index; i < nums.length; i++){
            swap(nums, index, i);
            helper(nums, index+1, result);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] array, int left, int right){
        int rem = array[left];
        array[left] = array[right];
        array[right] = rem;
    }
}

/*

draw the tree
branch: swap with fixed index of array, for loop
level: fix the index of array 
                       [1,2,3]
                       /  |    \
0                  1|,2,3  2|,1,3  3|,1,2
                /  | 
1            1,2|3 1,3|,2  
               /
2            1,2,3|
              |
level = array.length => add result and return   

TC: O(branch^level) = O((n!) ^ n) =O(n!) 
SC: O(n)


*/
```



