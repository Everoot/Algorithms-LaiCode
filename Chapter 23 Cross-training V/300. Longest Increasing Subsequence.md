---
tags:
    - Array
    - Binary Search
    - Dynamic Programming
---

# [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

Given an integer array `nums`, return *the length of the longest **strictly increasing subsequence***. 

**Example 1:**

```
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
```

**Example 2:**

```
Input: nums = [0,1,0,3,2,3]
Output: 4
```

**Example 3:**

```
Input: nums = [7,7,7,7,7,7,7]
Output: 1
```

 

**Solution:**

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[] dp = new int[nums.length];
        int result = 1;

        for (int i = 0; i < dp.length; i++){
            dp[i] = 1;
        }
        
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            result = Math.max(dp[i], result);
        }

        return result;

        
    }
}

/*
// TC: O(n^2)
// SC: O(n)
dp[i] represents the length of the longest strictly increasing subsequence end at i

        [10,9,2,5,3,7,101,18]
                    i
                  j
dp[i]    1  1 1 2 2 3 1   1

result = 

// base case 
i = 0; dp[i] = 1

// induction rule 
dp[i + 1] = dp[i]?  -> dp[i ] = dp[i -1] ?...

if (nums[j] < nums[i]){
    dp[i] = Math(dp[j] + 1, dp[i]);
}else{
    continue;
}
result


*/
```

