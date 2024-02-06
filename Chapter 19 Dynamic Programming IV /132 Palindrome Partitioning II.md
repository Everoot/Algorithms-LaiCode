# 132 Palindrome Partitioning II

Given a string `s`, partition `s` such that every substring of the partition is a palindrome.

Return *the **minimum** cuts needed for a palindrome partitioning of* `s`. 

**Example 1:**

```
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
```

**Example 2:**

```
Input: s = "a"
Output: 0
```

**Example 3:**

```
Input: s = "ab"
Output: 1
```

 

**Solution:**

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        // from isPalindrome[i][j] the string from i to j include i, j. if it is 
        // then it is true 

        int[] dp = new int[n];
        // s [0,....i] dp[i] min cut number

        for (int j = 0; j < n; j++){
            int min = j; // Maximum cuts needs is j 
            // (cutting each character individually)
            for (int i = 0; i <= j; i++){
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalindrome[i+1][j-1])){
                    isPalindrome[i][j] = true;
                    if (i == 0){
                        // i == 0 means s[0..j] is palindrome, so no need 
                        // cut
                        min = 0;
                    } else {
                        min = Math.min(min, dp[i-1] + 1);
                    }
                }
            }
            dp[j] = min;
        }

        return dp[n-1];

    }
}

// TC: O(n^2)
// SC: O(n^2)
```

