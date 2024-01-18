# 103 Longest Consecutive 1s (Lai)

Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.

**Assumptions**

- The given array is not null

**Examples**

- {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.



**Solution**

```java
public class Solution {
  public int longest(int[] array) {
    // Write your solution here
    int max = 0;
    int cur = 0;

    for (int i = 0; i < array.length; i++){
      if (i == 0){
        if (array[i] == 1){
          cur = 1;
          max = 1;
        }else{
          continue;
        }
      }else{
        // i != 0
        if (array[i] == 1){
          cur = cur + 1;
        }else {
          cur = 0;
        }
      }
      max = Math.max(cur, max);
    }

    return max;
  }
}

// TC: O(n)
// SC: O(1)
```

