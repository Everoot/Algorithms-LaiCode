# 76  All Unique Characters I (Lai)

Determine if the characters of a given string are all unique.

**Assumptions**

- The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
- The given string is not null.

**Examples**

- the characters used in "abcd" are unique
- the characters used in "aba" are not unique



```java
public class Solution {
  public boolean allUnique(String word) {
    // Write your solution here
    boolean[] seen = new boolean[26];
    for (int i = 0; i < word.length(); i++){
      int index = word.charAt(i) - 'a';
      if (seen[index] == true){
        return false;
      }
      seen[index] = true;
    }

    return true;
  }
}

/*
    [T, F, F, F .... F]
     

    index = word.charAt(i) -'a' 

    = check == true; ==> return false;
     

TC: O(n)
SC: O(n)
*/

```

