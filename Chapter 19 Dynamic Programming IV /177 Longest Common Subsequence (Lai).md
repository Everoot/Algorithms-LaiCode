# 177 Longest Common Subsequence (Lai)

Find the length of longest common subsequence of two given strings.

**Assumptions**

- The two given strings are not null

**Examples**

- S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.



**Solution:**

```java
public class Solution {
  public int longest(String source, String target) {
    // Write your solution here
    int[][] longest = new int[source.length() + 1][target.length() +1];
    for (int i = 1; i <= source.length(); i++){
      for (int j = 1; j <= target.length(); j++){
        if (source.charAt(i-1) == target.charAt(j -1)){
          longest[i][j] = longest[i - 1][j -1] +1;
        } else {
          longest[i][j] = Math.max(longest[i][j-1], longest[i-1][j]);
        }
      }
    }
    return longest[source.length()][target.length()];
  }
}
// TC: O(n^2)
// SC: O(n^2)
```

