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
       int[] dp = new int[n+1]; 
       dp[0] = 0;
       dp[1] = 0;
       dp[2] = 1;
       for (int i = 3; i <= n; i++){
           for (int j = 1; j < i; j++){
               dp[i] = Math.max(dp[i], j * Math.max(dp[i-j], i - j));
           }
       }

       return dp[n];
    }
}

/*

        0  1   2  3  4  5  6  7  8  9  10  
         _   _   _  _  _  _  _  _  _  _  

                  i
               j 
       
      
       index 0   1   2   3     4    5    6    7   8    9   10
         M   -   0.  1   2     4    6    8   12  16    17  18
                    1x1  1x2  1x3  1x4  1x5  1x6 1x7  1x8  1x9
                         2x1  2x2  2x3  2x4  2x5 2x6  2x7  2x8
                              1x3  3x2  3x3  3x4 3x5  3x6  3x7
                                   4x1  4x2  4x3 4x4  4x5  4x6
                                        5x1  5x2 5x3  5x4  5x5
                                             6x1 6x2  6x3  6x4
                                                 7x1  7x2  7x3
                                                      8x1  8x2
                                                           9x1

                                        i = 6
                                        i - j = 6-1=5 
                                        dp[5], 5

                                        j= 2
                                        6-2 = 4
                                        dp[4] = 4

                                        _ _ _ _ _ _

                                        _ _ _ _ _| _
                                        dp[5]     j =1

                                        _ _ _ _ | _ _
                                        dp[4]     j = 2

                                        _ _ _ | _ _ _
                                        dp[3]   j = 3 
                                        
                                        _ _ | _ _ _ _ 
                                        dp[2] j = 4

                                        _ | _ _ _ _ _
                                        dp[1] j=5


                        dp[i] = Math.max(dp[i], j * Math.max(dp[i-j], i-j)) 

*/  
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



