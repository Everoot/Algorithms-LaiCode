# 87 Max Product Of Cutting Rope (Lai)

Given a rope with positive integer-length *n*, how to cut the rope into *m* integer-length parts with length *p*[0], *p*[1], ...,*p*[*m*-1], in order to get the maximal product of *p*[0]**p*[1]* ... **p*[*m*-1]? *m* **is determined by you** and must be greater than 0 **(at least one cut must be made**). Return the max product you can have.

**Assumptions**

- n >= 2

**Examples**

- n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).



**Solution:**

```java
public class Solution {
  public int maxProduct(int length) {
    // Write your solution here

    if (length <= 1){
      return 0;
    }

    int[] dp = new int[length + 1];

    // 0 1 2 3 4 
    dp[0] = 0;
    dp[1] = 1;

    /*
    _ _ _ 
        i
      j 
    */
    // 左大段 + 右小段
    for (int i = 2; i < length + 1; i++){
      for (int j = 1; j < i; j++){
        int left = j;
        int right = i - j;
        int cur1 = left * right;
        int cur2 = dp[j] * right;
        int cur = Math.max(cur1, cur2);
        dp[i] = Math.max(cur, dp[i]);
      }
    }

    return dp[length];

  }
}

// TC: O(n^2)
// SC: O(n)

```

