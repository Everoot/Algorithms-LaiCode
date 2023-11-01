# 77 Combinations

Given two integers `n` and `k`, return *all possible combinations of* `k` *numbers chosen from the range* `[1, n]`.

You may return the answer in **any order**.

 

**Example 1:**

```
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
```

**Example 2:**

```
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
```

 

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {  
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // base case
        if (n == 0 || k == 0){
            return result;
        }

        int[] nums = new int[n];
        for (int i = 1; i <= n; i++){
            nums[i-1] = i; 
        }

        List<Integer> subResult =  new ArrayList<Integer>();

        helper(nums, 0, subResult, result, k);
        return result;
        
    }

    private static void helper(int[] nums, int index, List<Integer> subResult, List<List<Integer>> result, int k){
        // check
        if (subResult.size() == k){
            result.add(new ArrayList<Integer>(subResult));
            return;
        }

        // base case
        if (index == nums.length){
            return;
        }

        subResult.add(nums[index]);
        helper(nums, index + 1, subResult, result, k);

        subResult.remove(subResult.size() - 1);
        helper(nums, index + 1, subResult, result, k);
    }
}


/*
            n = 4     k = 2
          1 2 3 4
        /      \
0      1             - 
      / \           / \
1    12   -         2   -
         /\       / \    /\
2       3  -     23 2   3  -
       / \  /\      /\  / \   /\
3     34  3 4 -    24 2 34 -  4 -
          /
4       index = n; return 
 
*/
// O((n!)/(k−1)!(n−k)!)
//	SC: O(k)
```

