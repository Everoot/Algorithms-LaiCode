# 1910 Remove All Occurrences of a Substring

Given two strings `s` and `part`, perform the following operation on `s` until **all** occurrences of the substring `part` are removed:

- Find the **leftmost** occurrence of the substring `part` and **remove** it from `s`.

Return `s` *after removing all occurrences of* `part`.

A **substring** is a contiguous sequence of characters in a string.

 

**Example 1:**

```
Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".
```

**Example 2:**

```
Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".
```

 ```java
 class Solution {
     public String removeOccurrences(String s, String part) {
         StringBuilder sb = new StringBuilder(s);
 
         int i = 0;
         while (i < sb.length()){
             int j = 0;
             int temp = i; // save i, we will need it to set the window back
             while(i < sb.length() && j < part.length() && sb.charAt(i) == part.charAt(j)){
                 i++;
                 j++;
                 // check if chars of window match
             }
 
             if (j == part.length()){
                 // if all match, then j must equal to part.length
                 sb.delete(temp, temp + j); //delete the substring
                 i = Integer.max(0, temp - j + 1);// set the window back
             }else{
                 i = temp + 1; // substring did not match, advance the window by1
             }
         }
 
         return sb.toString();
     }
 }
 ```

**Explanation** We use a sliding window. When the start character of the window matches the start character of `part`we check if rest of the characters in the window match. If they match we delete that part and set the window back. Note that you have to be careful while setting the window back because after deleting, the newly formed string can again form the matching substring. So you need to set the window back by the length of the part (or to the beginning of the string s whichever is higher). We continue doing this until we have reached end of the string.



```
public StringBuilder delete(int start,
                            int end)
```

Removes the characters in a substring of this sequence. The substring begins at the specified `start` and extends to the character at index `end - 1` or to the end of the sequence if no such character exists. If `start` is equal to `end`, no changes are made.