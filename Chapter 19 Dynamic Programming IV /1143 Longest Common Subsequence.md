# 1143 Longest Common Subsequence

Given two strings `text1` and `text2`, return *the length of their longest **common subsequence**.* If there is no **common subsequence**, return `0`.

A **subsequence** of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

- For example, `"ace"` is a subsequence of `"abcde"`.

A **common subsequence** of two strings is a subsequence that is common to both strings.

 

**Example 1:**

```
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
```

**Example 2:**

```
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
```

**Example 3:**

```
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
```



**Solution:**

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] longest = new int[text1.length()+1][text2.length() +1];
        for (int i = 1; i <= text1.length(); i++){
            for (int j = 1; j <= text2.length(); j++){
                if (text1.charAt(i - 1) == text2.charAt(j-1)){
                    longest[i][j] = longest[i-1][j-1] + 1;
                } else {
                    longest[i][j] = Math.max(longest[i - 1][j], longest[i][j-1]);
                }
            }
        }

        return longest[text1.length()][text2.length()];
        
    }
}

// TC: O(n^2)
// SC: O(n^2)
/*	
					i
        - a b c
      - 0 0 0	0
      d 0 0	0	0				j  
      e 0 0 0 0
      f 0 0 0 0
      a 0 1 0 0
      f 0 1 1 1
      c 0 1 1 2
M[i-1][j] is from
1 + M[i - 1][j - 1] > M[i-1][j-1]
Math.max(M[i-2][j], M[i-1][j-1]) >= M[i-1][j-1]

res  = dp[n][m] -> 一直在继承, 从来没有东山再起
*/
```



M\[i][j] represents the length of the longest subsequences between a[0... i -1] (first i letters of a) and b[0 ... j -1] (first j letters of b)

M\[i][j -1]

M\[i - 1][j]

**Base case**

M\[0][0] = 0

M\[i][0] = 0

M\[0][j] = 0



**Induction rule**

**Case 1: M\[i][j] = 1 + M\[i -1][j -1]					if 		a[i - 1] == b[j-1]**

**Case 2: M\[i][j] = max(M\[i -1][j], M\[i][j -1]) 	else a[i-1] != b[j-1]**

TC: O(n\*m) * O(1) =O(n*m)

SC: O(n * m) => optimize O(n)               

