---
tags:
    - Hash Table
    - String
    - Sliding Window
---

#  438 Find All Anagrams in a String

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
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }

        int slow = 0;
        int match = 0;

        for (int fast = 0; fast < s.length(); fast++){
            char tmp = s.charAt(fast); // b      ; a ; a
            Integer count = map.get(tmp); // null; 2 ; 1
            if (count != null){   //   
                if (count == 1){ 
                    match++;  // 1
                }
                map.put(tmp, count - 1); // <a, 0>
            }

            if (fast >= p.length()){      //2 //   fast = 2  < 2
                char slowCh = s.charAt(slow); // b
                count = map.get(slowCh); //  
                if (count != null){
                    if (count == 0){
                        match--;
                    }
                    map.put(slowCh, count+1);
                }
                slow++; // 1
            }
            
            if (match == map.size()){ // 0 1; 0 != 1  ; 1= 1 
                result.add(slow); 
            }

        }
        return result;
    }
}


/*


        s = "baa", p = "aa"
               f
             s

map: 
<a,0>


*/
// TC: O(n)
// SC: O(n)
```

