# 86 Longest Ascending SubArray

Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

**Assumptions**

- The given array is not null

**Examples**

- {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
- {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.



**Solution**

```java
public class Solution {
  public int longest(int[] array) {
    // Write your solution here

    if (array == null || array.length == 0){
      return 0;
    }
    
    int[] dp = new int[array.length];

    // base case
    dp[0] = 1;
    int result = dp[0];

    // induction rule
    for (int i = 1; i < array.length; i++){
      if (array[i-1] < array[i]){
        dp[i] = dp[i-1] + 1;
      }else{
        dp[i] = 1;
      }
      result = Math.max(dp[i], result);
    }
    return result;
  }
}

// TC: O(n)
// SC: O(n)

/*
dp[i] repsents  the length of the longest subarray in which the numbers are in ascending order continue end at i
     {7, 2, 3, 1, 5, 8, 9, 6},
                           i 
dp.   1  1. 2  1  2. 3  4  1

result = max 

// base case
i = 0;
dp[0] = 1;


// induction rule
if (array[i-1] < array[i]){
  dp[i] = dp[i-1]+1
}else{
  dp[i] = 1;
}

*/
```

