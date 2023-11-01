# 3 Longest Substring Without Repeating Characters

Given a string `s`, find the length of the **longest** 

**substring**

 without repeating characters.



 

**Example 1:**

```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

**Example 2:**

```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**

```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

 

**Constraints:**

- `0 <= s.length <= 5 * 104`
- `s` consists of English letters, digits, symbols and spaces.



```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // base case 
        if (s == null ){
            return 0;
        }

        if (s.length() <= 1){
            return s.length();
        }

        Set<Character> c = new HashSet<Character>();
        int longest = 0;
        int slow = 0;
        int fast = 0;
        while(fast < s.length()){
            if (c.contains(s.charAt(fast))){
                // exist
                c.remove(s.charAt(slow));
                slow++;
            }else{
                // not exist
                c.add(s.charAt(fast));
                fast++;
                longest = Math.max(longest, fast - slow);
            }
        }

        return longest;

    }
}

// TC: O(n)

// SC: O(n)



/*
    set:  c 
    longest = 3
    a   b   c   a   b   c   b   b
    while(f < s.length)
                                s
                                   f

    1. check set 
        1.1 if exit
        remove slow 
        slow++
        1.2 if not exit 
            :   1.2.1: add.set
                1.2.2: fast++;
                1.2.3: Math(longest, new fast - s);


*/
```

