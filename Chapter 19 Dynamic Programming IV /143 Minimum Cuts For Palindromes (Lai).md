# 143 Minimum Cuts For Palindromes (Lai)

Given a string, a partitioning of the string is a *palindrome partitioning* if every substring of the partition is a palindrome. Determine the **fewest** cuts needed for a palindrome partitioning of a given string.

**Assumptions**

- The given string is not null

**Examples**

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3.



**Solution:**

```java
public class Solution {
  public int minCuts(String input) {
    // Write your solution here
    if (input == null || input.length() == 0){
      return 0;
    }

    int n = input.length();
    boolean[][] isPalindromes = new boolean[n][n];
    int[] dp = new int[n];

    for (int j = 0; j < n; j++){
      int min = j;
      for (int i = 0; i <= j; i++){
        if (input.charAt(i) == input.charAt(j) && (j - i < 2 || isPalindromes[i+1][j-1] == true)){
          isPalindromes[i][j] = true;
          if (i == 0){
            min = 0;
          }else{
            min = Math.min(min, dp[i-1]+1);
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



```java
public class Solution {
  public int minCuts(String input) {
    // Write your solution here
    if (input == null || input.length() == 0){
      return 0;
    }

    boolean[][] dp = new boolean[input.length() + 1][input.length() + 1];
    int[] min = new int[input.length()+1];

    for (int end = 1; end <= input.length(); end++){
      min[end] = end -1;
      for (int start = end; start > 0; start--){
        if (input.charAt(start - 1) == input.charAt(end - 1)){
          dp[start][end] = (start >= end - 1) || dp[start + 1][end - 1];
        }
        if (dp[start][end]){
          if (start == 1){
            min[end] = 0;
          }else{
            min[end] = Math.min(min[end], min[start - 1] +1);
          }
        }
      }
    }
    return min[input.length()];
  }
}

```

