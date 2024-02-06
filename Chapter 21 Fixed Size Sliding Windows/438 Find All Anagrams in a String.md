---
tags:
    - Hash Table
    - String
    - Sliding Window
---

# 438 Find All Anagrams in a String

Given two strings `s` and `p`, return *an array of all the start indices of* `p`*'s anagrams in* `s`. You may return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**

```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```

**Example 2:**

```
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

**Solution:**

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();

        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()){
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < p.length(); i++){
            char ch = p.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int slow = 0;
        int match = 0; // match should == map.size() then update result

        for (int fast = 0; fast < s.length(); fast++){
            // step 1 put fast
            char tmp = s.charAt(fast);
            Integer count = map.get(tmp);
            if (count != null){
                map.put(tmp, count - 1);
                if (count == 1){
                    match++;
                }
            }


            // step 2 remove slow
            if (fast >= p.length()){
                tmp = s.charAt(slow);
                count = map.get(tmp);
                if (count != null){
                    map.put(tmp, count + 1);
                    if (count == 0){
                        match--;
                    }
                }
                slow++;
            }




            // step 3 update result;
            if (match == map.size()){
                result.add(slow);
            }
        }


        return result;

        
    }
}

// TC: O(n)
// SC: O(n)


/*
         c b a e b a b a c d             a  b c
         0 1 2 3 4 5 6 7 8 9    
         -           -
           s
                 f

map
<a, 0>
<b, 0>
<c, 1>     

m = 2
*/
```

