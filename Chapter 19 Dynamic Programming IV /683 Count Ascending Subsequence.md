# 683 Count Ascending Subsequence

Given an array A[0]...A[n-1] of integers, count the number of ascending subsequences.

In case that the result is larger than 32-bit integer, return the result in 10^9+7 modulo.

**Assumptions**

- A is not null

**Examples**
Input: A = {1,2,3}
Output: 7
Explanation: [1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]



**Solution:**

```java
public class Solution {
  public int numIncreasingSubsequences(int[] a) {
    // Write your solution here
    if (a == null || a.length == 0){
      return 0;
    }

    int[] dp = new int[a.length];
    for (int i = 0; i < a.length; i++){
      int count = 0;
      for (int j = 0; j < i; j++){
        if (a[j] < a[i]){
          count = count + dp[j];
        }
      }
      dp[i] = count + 1;   // 1 means itself
    }

    int result = 0;
    for (int i = 0; i < a.length; i++){
      result = dp[i] + result;
    }
    return result;
  }
}
// TC: O(n^2)
// SC: O(n)

/*
dp[i] = total # of ascending subsequences that ends at index i

dp[i] = sum{dp[j]} + 1
for all 0<-j <i and input[j]< input[i]

*/
```

