# 344 Reverse String (iterative)

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
        int left = 0;
        int right = s.length-1;
        while(left < right){
            swap(s, left, right);
            left++;
            right--;
        }

        return;
    }

    private static void swap(char[] s, int left, int right){
        char rem = s[left];
        s[left] = s[right];
        s[right] = rem;
    }
}
/*

TC: O(n)
SC: O(1)
*/
```



