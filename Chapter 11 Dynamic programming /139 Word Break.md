# 139 Word Break

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

**Note** that the same word in the dictionary may be reused multiple times in the segmentation.

 

**Example 1:**

```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```

**Example 2:**

```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```

**Example 3:**

```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```



```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
       // Assume input is not ""
       // M[i] represents the substring[0, i);
       // M[0] represents "" , M[1] represent s[0]; M[2] represent s[0-2]
       boolean[] M = new boolean[s.length() + 1];
       // That's why we need +1 here
       M[0] = true;
       // 0 represent ""
       for (int i = 1; i <= s.length(); i++){
           // i letter // linear scan 回头看
           for (int j = 0; j < i; j++){
               // first j letter as 左大段 // 这个for loop 在切
               if (M[j] && wordDict.contains(s.substring(j, i))){
                // cut left    && cut right
                // substring begins at index i and extends to index i-1
                   M[i] = true;
                   break;
               }

           }
       }
       return M[s.length()];

    }
}
// time =  O(n^3) because of the string.substring API and the hashset.contains API.
// Space = O(n) or O(n^3) if you cannot assume GC happens immediatel

// Off by 1
// M[i] 里的i 代表的含义不是index, 代表的是长度
// M[i] 长度为i的string 能不能被切分开每一段都在dict里
```

