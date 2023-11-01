# 77 All Unique Characters II (Lai)

Determine if the characters of a given string are all unique.

**Assumptions**

- We are using ASCII charset, the value of valid characters are from 0 to 255
- The given string is not null

**Examples**

- all the characters in "abA+\8" are unique
- "abA+\a88" contains duplicate characters

```java
public class Solution {
  public boolean allUnique(String word) {
    // Write your solution here
    boolean[] seen = new boolean[256];
    for (int i = 0; i < word.length(); i++){
      int index = (int) word.charAt(i);
      if (seen[index] == true){
        return false;
      }

      seen[index] = true;
    }

    return true;
  }
}
// TC: O(n)
// SC: O(n)
```

