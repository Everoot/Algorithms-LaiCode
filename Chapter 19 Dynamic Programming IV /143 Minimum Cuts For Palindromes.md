# 143 Minimum Cuts For Palindromes

Given a string, a partitioning of the string is a *palindrome partitioning* if every substring of the partition is a palindrome. Determine the **fewest** cuts needed for a palindrome partitioning of a given string.

**Assumptions**

- The given string is not null

**Examples**

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3.



**Solution:**

```java
// Step 1: DP定义
// dp[i] represent the minimum number of cuts needed for a palindrome partition of a ending at i 
// Step 2: Base Case
// dp[0]
// dp[anyOtherIndex] = Integer.MAX_VALUE
// Step 3: Induction rule 切割问题的子问题: 切点
// Case 1: 一定要切
/*
	[0...........i] -> dp[i]
	[0..j][j+1...i]
左大段: dp[j]    右小段必须保证: 是个回文串

dp[i] = dp[j] + 1 (切点这一刀不要忘记) for all j < i and s[j+1 ... i] is a palindrome

Case 2: 有可能可以不切
	dp[i] = 0 iff a[0...i] is palindrome
*/


public class Solution {
  public int minCuts(String input) {
    // Write your solution here
    if (input == null || input.length() == 0){
      return 0;
    }

    int[] dp = new int[input.length()];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 0; i < input.length(); i++){
      if (isPalindrome(input, 0, i)){
        dp[i] = 0;
      }else{
        for (int j = 0; j < i; j++){
          if (dp[j] != Integer.MAX_VALUE && isPalindrome(input, j + 1, i)){
            dp[i] = Math.min(dp[i], dp[j] + 1);
          }
        }
      }
    }
    return dp[input.length() - 1];
  }

  private boolean isPalindrome(String input, int i, int j){
    while(i < j){
      if (input.charAt(i) != input.charAt(j)){
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}

// TC: O(n^3)
// SC: O(n)
```



Solution2:

```java
/*
如果我能O(1)的时间判断一段区间是不是回文串 那这个算法就真的是O(n^2)
Step 1: DP定义
dp[i][j] represent from i to j (including i, j) s[i...j] 是不是一个回文串 j>=i
Step 2: Base Case
dp[i][i] = true
dp[i][i + 1] = s[i] == s[i+1]? true: false
Step 3: Induction rule
dp[i][j] =>
						[i [... ... ...] j]
						s[i] = s[j]
							dp[i][j] = dp[i+1][j-1]
						s[i] != s[j]
							dp[i][j] = false
怎么填写这个DP:
														[i][j]
								[i+1][j-1]


*/

public class Solution {
  public int minCuts(String input) {
    // Write your solution here
    if (input == null || input.length() == 0){
      return 0;
    }

    // Step 1: dp[i][j] -> O(n^2)  O(1)的时间告诉我们从i到j是不是回文串
    boolean[][] dp = new boolean[input.length()][input.length()];

    for (int i = input.length() - 1; i >= 0; i--){
      // 从下到上
      for (int j = i; j < input.length(); j++){
        // 从左到右 -> j is ending index of i..j 从左到右
        if (i == j){
          dp[i][j] = true;
        }else if (j == i + 1){
          dp[i][j] = input.charAt(i) == input.charAt(j) ? true : false;
        }else{
          if (input.charAt(i) == input.charAt(j)){
            dp[i][j] = dp[i+1][j-1];
          }
        }
      }
    }

    // Step 2
    int[] min = new int[input.length()];
    Arrays.fill(min, Integer.MAX_VALUE);
    min[0] = 0;

    for (int i = 0; i < input.length(); i++){
      if (isPalindrome(input, 0, i)){
        min[i] = 0;
      }else{
        for (int j = 0; j < i; j++){ // -> i is ending index of min[i] 从左到右
          if (min[j] != Integer.MAX_VALUE && dp[j+1][i]){
            min[i] = Math.min(min[i], min[j] + 1);
          }
        }
      }
    }
    return min[input.length() - 1];
  }

  private boolean isPalindrome(String input, int i, int j){
    while(i < j){
      if (input.charAt(i) != input.charAt(j)){
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}
// TC: O(n^2)
// SC: O(n)
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
// Strong hiring
```

