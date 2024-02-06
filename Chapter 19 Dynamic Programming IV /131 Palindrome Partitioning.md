---
tags:
  String
  Dynamic Programming 
  Backtracking
---

#131 Palindrome Partitioning

Given a string `s`, partition `s` such that every substring of the partition is a  **palindrome**. Return *all possible palindrome partitioning of* `s`.

**Example 1:**

```
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
```

**Example 2:**

```
Input: s = "a"
Output: [["a"]] 
```

**Solution:**

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> subResult = new ArrayList<String>();
        int start = 0;
        helper(s, 0, subResult, result);
        return result;
    }

    private void helper(String s, int start, List<String> subResult, List<List<String>> result){
        if (start >= s.length()){
            result.add(new ArrayList<>(subResult));
            return;
        }

        for (int end = start; end < s.length(); end++){
            if (isPalindrome(s, start, end)){
                subResult.add(s.substring(start, end + 1));
                helper(s, end + 1, subResult, result);
                subResult.remove(subResult.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right){
        while (left < right){ // O(n)
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}
//TC: O(n*2^n)
//The primary time-consuming part is the backtracking process. In the worst case, we have to explore every possible partition of the string. For a string of length n, there are 2^(n-1) partitions (since at each position in the string, we can choose to either make a cut or not, except at the end). 

//SC: O(n) // length of string
```



![time_complexity](./131 Palindrome Partitioning/time_complexity.png)



```java
class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int right = 0; right < len; right++){
            for (int left = 0; left <= right; left++){
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])){
                    dp[left][right] = true;
                }
            }
        }

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> subResult = new ArrayList<String>();

        int start = 0;
        helper(s, start, subResult, result, dp);
        return result;
        
    }

    private void helper(String s, int start, List<String> subResult, List<List<String>> result, boolean[][] dp){
        if (start >= s.length()){
            result.add(new ArrayList<String>(subResult));
            return;
        }

        for (int end = start; end < s.length(); end++){
            if (dp[start][end]){
                subResult.add(s.substring(start, end+1));
                helper(s, end+1, subResult, result, dp);
                subResult.remove(subResult.size() -1);
            }
        }
    }
}

// TC: O(n * 2^n)
// SC: O(n^2)
```

