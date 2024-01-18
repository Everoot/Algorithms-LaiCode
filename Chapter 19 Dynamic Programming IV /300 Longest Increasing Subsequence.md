# 300 Longest Increasing Subsequence

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
        //base case 
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[] m = new int[nums.length];
        int max = 1;
      	// base case
        for (int i = 0; i < nums.length; i++){
            m[i] = 1;
        }

        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    m[i] = Math.max(m[j] + 1, m[i]);
                }
            }
            max = Math.max(max, m[i]);
        }

        return max;
    }
}


/*

// TC: O(n^2)
// SC: O(n)

max = 
index.   0 1 2 3 4 5  6   7
        10,9,2,5,3,7,101,18
m        1 1 1 2 2 3  1  1
 
                     i
                   j
      
dp[i]: represent the from start to i, the longest 
base case: 
dp[0] = 1
induction rule:
dp[i] = dp[i-1] + 1, if a[i-1] < a[i]

    if (nums[j] < nums[i]){
        m[i] = Math.max(m[j]+1, m[i])
    }

return max;

*/
```



