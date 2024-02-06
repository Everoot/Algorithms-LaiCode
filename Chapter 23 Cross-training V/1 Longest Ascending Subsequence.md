# 1 Longest Ascending Subsequence

Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

**Assumptions**

- A is not null

**Examples**
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence.



**Solution:**

```java
public class Solution {
  public int longest(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0){
      return 0;
    }

    int[] dp = new int[array.length];

    for (int i = 0; i < dp.length; i++){
      dp[i] = 1;
    }
    int result = 1;

    for (int i = 1; i < array.length; i++){
      for (int j = 0; j < i; j++){
        if (array[j] < array[i]){
          dp[i] = Math.max(dp[j]+ 1, dp[i]);
        }
      }
      result = Math.max(dp[i], result);
    }

    return result;
  }
}

// TC: O(n^2)
// SC: O(n)

```

