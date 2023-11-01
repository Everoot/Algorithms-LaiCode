# 387 First Unique Character in a String

Given a string `s`, *find the first non-repeating character in it and return its index*. If it does not exist, return `-1`.

**Example 1:**

```
Input: s = "leetcode"
Output: 0
```

**Example 2:**

```
Input: s = "loveleetcode"
Output: 2
```

**Example 3:**

```
Input: s = "aabb"
Output: -1
```

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] seen = new int[26];// 26个字母
        for (int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            seen[index]++;

        }

        for (int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            if (seen[index] == 1){
                return i;
            }
        }
        return -1;
    }
}
```

