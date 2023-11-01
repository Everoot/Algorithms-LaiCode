# 408 Valid Word Abbreviation

A string can be **abbreviated** by replacing any number of **non-adjacent**, **non-empty** substrings with their lengths. The lengths **should not** have leading zeros.

For example, a string such as `"substitution"` could be abbreviated as (but not limited to):

- `"s10n"` (`"s ubstitutio n"`)
- `"sub4u4"` (`"sub stit u tion"`)
- `"12"` (`"substitution"`)
- `"su3i1u2on"` (`"su bst i t u ti on"`)
- `"substitution"` (no substrings replaced)

The following are **not valid** abbreviations:

- `"s55n"` (`"s ubsti tutio n"`, the replaced substrings are adjacent)
- `"s010n"` (has leading zeros)
- `"s0ubstitution"` (replaces an empty substring)

Given a string `word` and an abbreviation `abbr`, return *whether the string **matches** the given abbreviation*.

A **substring** is a contiguous **non-empty** sequence of characters within a string.

 

**Example 1:**

```
Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
```

**Example 2:**

```
Input: word = "apple", abbr = "a2e"
Output: false
Explanation: The word "apple" cannot be abbreviated as "a2e".
```

 

**Constraints:**

- `1 <= word.length <= 20`
- `word` consists of only lowercase English letters.
- `1 <= abbr.length <= 10`
- `abbr` consists of lowercase English letters and digits.
- All the integers in `abbr` will fit in a 32-bit integer.



```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        // base case
        if (word == null && abbr == null){
            return true;
        }

        int w = 0;
        int a = 0;
        while (w < word.length() && a < abbr.length()){ // O(m+n)
            if (abbr.charAt(a) < '0' || abbr.charAt(a) > '9'){
                if (abbr.charAt(a) != word.charAt(w)){
                    return false;
                }
                w++;
                a++;
            }else if (abbr.charAt(a) > '0' && abbr.charAt(a) <= '9'){
                int count = 0;
                while(a < abbr.length() && abbr.charAt(a) >= '0' && abbr.charAt(a) <= '9'){
                    count = count * 10 + (abbr.charAt(a) - '0');
                    a++;
                }

                w = w + count;

            }else{
                return false;
            }
        }
        
        return w == word.length() && a == abbr.length();
    }
}


// TC: O(n)
// SC: O(1)
/*
    internationalization
                       w 


    i12iz4n
          i

1. if characters 
        if ==
    w++ i++

2 not 0    = >   nubmer       > '0' && <= '9'

3. else
return false 
. not have leading 0

return w == word.length && i == abbr.length
*
```

