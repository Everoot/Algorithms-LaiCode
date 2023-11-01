# 344 Reverse String (recursive)

Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) with `O(1)` extra memory.

 

**Example 1:**

```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**

```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```



```java
class Solution {
    public void reverseString(char[] s) {
        // base case
        if (s == null || s.length <= 1){
            return;
        }

        int left = 0;
        int right = s.length - 1;
        helper(s, left, right);
        return;
    }

    private static void helper(char[] s, int left, int right){
        if (left >= right){
            return;
        }

        swap(s, left, right);

        helper(s, left + 1, right - 1);
    }

    private static void swap(char[] s, int left, int right){
        char rem = s[left];
        s[left] = s[right];
        s[right] = rem;
    }
}

/*

// TC: O(n)
// SC: O(n)

 l >= r 

        h e l l o
        l       r
         (l+1, r-1)

*/
```

