---
tags:
    - Array
    - Hash Table
    - String
    - Sorting
---

# 49 Group Anagrams

Given an array of strings `strs`, group **the anagrams** together. You can return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

**Example 1:**

```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Example 2:**

```
Input: strs = [""]
Output: [[""]]
```

**Example 3:**

```
Input: strs = ["a"]
Output: [["a"]]
```

 

**Solution:**

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();

        if (strs == null || strs.length == 0){
            return result;
        }

        Map<String, List<String>> map = new HashMap<String, List<String>>(); 
        for (String s : strs){
            // Convert the string to a char array and sort it
            char[] charArray = s.toCharArray(); // eat
            Arrays.sort(charArray); // aet // O(klogk)

            // Convert back to a string to use as a key in the map
            String sorted = new String(charArray);// aet

            // If the sorted string is not already a key in the map,
            // add it with a new list
            if (!map.containsKey(sorted)){ // 
                map.put(sorted, new ArrayList<>()); // aet, 
            }
            // Add the original string to the list associated with the sorted key
            map.get(sorted).add(s);
        }

        result.addAll(map.values());

        return result;
    }
}
// TC: O(n klogk)     k string length
// SC: O(nk)
```

