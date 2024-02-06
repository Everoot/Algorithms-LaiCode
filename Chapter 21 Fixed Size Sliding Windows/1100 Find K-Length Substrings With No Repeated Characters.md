---
tags:
    - Hash Table
    - String
    - Sliding Window
---

# 1100 Find K-Length Substrings With No Repeated Characters

Given a string `s` and an integer `k`, return *the number of substrings in* `s` *of length* `k` *with no repeated characters*. 

**Example 1:**

```
Input: s = "havefunonleetcode", k = 5
Output: 6
Explanation: There are 6 substrings they are: 'havef','avefu','vefun','efuno','etcod','tcode'.
```

**Example 2:**

```
Input: s = "home", k = 5
Output: 0
Explanation: Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
```



**Solution:**

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        Map<Character, Integer> distinct = new HashMap<Character, Integer>();
        int result = 0;

        for (int fast = 0; fast < s.length(); fast++){
            // 1. add fast
            distinct.put(s.charAt(fast), distinct.getOrDefault(s.charAt(fast), 0) + 1);

            // 2. remove slow
            if (fast >= k){
                int count = distinct.get(s.charAt(fast-k));
                if (count == 1){
                    distinct.remove(s.charAt(fast - k));
                }else{
                    distinct.put(s.charAt(fast-k), count - 1);
                }
            }
            // check size k sbstring all distinct
            if (fast >= k - 1 && k == distinct.size()){
                result++;
            }
        }

        return result;
        
    }
}

// TC: O(n)
// SC: O(k)
```

