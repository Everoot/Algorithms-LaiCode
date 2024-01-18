# 673 Number of Longest Increasing Subsequence

Given an integer array `nums`, return *the number of longest increasing subsequences.*

**Notice** that the sequence has to be **strictly** increasing.

**Example 1:**

```
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
```

**Example 2:**

```
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
```

**Solution:**

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        if (nums.length == 1){
            return 1;
        }

        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            dp[i] = 1;
            count[i] = 1;
        }

        int max = 0;

        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = 0;
                    }
                    
                    if (dp[j] + 1 == dp[i]){
                        count[i] = count[j] + count[i];
                    }
                }

                max = Math.max(max, dp[i]);
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++){
            if (dp[i] == max){
                result = count[i] + result;
            }
        }
        
        return result;
    }
}


// TC: O(n^2)
// SC: O(n)
```

