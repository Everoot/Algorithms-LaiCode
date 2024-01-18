# 343 Integer Break

Given an integer `n`, break it into the sum of `k` **positive integers**, where `k >= 2`, and maximize the product of those integers.

Return *the maximum product you can get*.

 

**Example 1:**

```
Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
```

**Example 2:**

```
Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
```



**Solution:**

```java
class Solution {
    public int integerBreak(int n) {
        if (n <= 1){
            return 0;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i < n+1; i++){
            for (int j = 1; j < i + 1; j++){
                int left = j;
                int right = i-j;
                int cur1 = left * right;
                int cur2 = dp[j] * right;
                int cur = Math.max(cur1, cur2);
                dp[i] = Math.max(dp[i], cur);
            }
        }

        return dp[n];

    }

}

/*
  n = 2
    
        0 1 2 3 4 5 6 7 8 9 10  
dp[i]   x x 1 2
              i
          j
          _| _ _   3 ->    3=1 +2     _ _ |_ 3 = 2 +1 

          _ | _ _  _  1   


*/
```



```java
class Solution {
    public int integerBreak(int n) {
        // base case 
        if (n <= 1){
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++){
            for (int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i], j * Math.max(dp[i - j], (i-j)));
            }
        }

        return dp[n];
        
    }
}

// TC: O(n^2)
// SC: O(n)

/*

i  0 1 2
m  x x 1   
     _ _
        
     _ | _
     1 + 1 = 2
     1 * 1 = 1

i   0 1 2 3 4 5 6 7 8 9 10
m   x x 1 2 4 6 9.......         return m[n]
      _ _ _ _ _ _ _ _ _ _
     
      _ | _  1+1 =2                _ _ _ _ _ _ _ _


      _ |_ _     1+ 2=3                _ _ _ _ _ _ _
                 1 * 2=2
      _ _ | _    2+1 =3                _ _ _ _ _ _ _
                2*1 =2


    _ |_ _ _    1+ 3=4                _ _ _ _ _ _
                 1 * 3=3
     _ _ | _ _   2+2 = 4                _ _ _ _ _ _
                2 * 2 =4
    _ _  _| _   3+1 = 4                _ _ _ _ _ _
                3 * 1 =3 
                    j



    5     1 + 4 = 5  1 * 4 = 4
          2 + 3 = 5  2 * 3 = 6
          3 + 2 = 5  3 * 2 = 6
          4 + 1 = 5  4 * 1 = 4 

   6.     1 + 5     5
          2 + 4     8 
          3 + 3     9
          4 +2     8 
          5 + 1   6

j < i 
dp[i] = Math.max(dp[i], j * Math.max(dp[i -j], (i-j)))
*/
```

